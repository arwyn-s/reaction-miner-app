

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.concurrent.Task;
import javafx.application.Platform;
import javafx.scene.control.ProgressIndicator;

public class SceneController {
    @FXML
    private TextField sourceField, targetField, orgField, pathField;

    @FXML
    private TextArea terminalArea;

    @FXML
    private Label loadingLabel;

    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    private Button runButton;
    private boolean runState;

    @FXML
    private void submitForm() throws IOException {
        if (!runState) {
            terminalArea.setText(null);
            runButton.setText("RUN");
            runState = !runState;
        } else {
            // terminalArea.setText("source: " + sourceField.getText() + "\n" + "target: " +
            // targetField.getText() + "\n"
            // + "OrgID: " + orgField.getText() + "\n");
            startTerminal();
            runButton.setText("CLEAR");
            runState = !runState;
        }

    }

    private void startTerminal() {
        try {
            // runProcess("pwd");
             //System.out.println("**********");
             //runProcess("java -cp src/main/resources/Helloworld Helloworld");
             System.out.println("**********");
             String reactionMiner = "java -cp src:dependencies/Motif:lib/*:dependencies/jbliss/:dependencies/jbliss/lib/* -Djava.library.path=dependencies/jbliss/lib/ pathwayPrediction.QueryTester";
             reactionMiner = reactionMiner + " -source " + sourceField.getText() + " -target " + targetField.getText() + " -org_id " + orgField.getText() + " -paths " +pathField.getText();
             //String cmd = "echo " + "\"" + reactionMiner + "\"";
             String cmd = reactionMiner;
             runProcess(cmd);
             //System.out.println("***********");
             //runProcess("pwd");
        
             } catch (Exception e) {
             e.printStackTrace();
         }
    }
    //Print reactionminer output to the textArea.
    private void printLines(String cmd, InputStream ins) throws Exception {
        String line;
        BufferedReader in = new BufferedReader(
            new InputStreamReader(ins));
            int linecount = 0;
        while ((line = in.readLine()) != null) {
            //System.out.println(cmd + " " + line);
            // To print only the predicted paths.
            System.out.println("stdout: "+ line);
            if(line.charAt(0)=='[') {
                linecount++;
                final String lines = "\n"+ String.valueOf(linecount)+": "+line.substring(line.indexOf('[') + 1,line.indexOf(']'));
                //UI elements that updates with thread must be wrapped with runLater functions.
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        terminalArea.setText(terminalArea.getText() + lines);
                    }
                });  
            }
 
        }
      }
    
    private void runProcess(final String command) throws Exception {
 
        
        Task<Integer> task = new Task<Integer>() {
            @Override protected Integer call() throws Exception {
                int sleep_ms=10000;
                //Thread.sleep(sleep_ms);
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        runButton.setDisable(true);
                        loadingLabel.setText("Please....wait...");
                        terminalArea.setText("\n");
                    }
                });
                Process pro = Runtime.getRuntime().exec(command);
                printLines("stdout:", pro.getInputStream());
                //printLines("stderr:", pro.getErrorStream());
                pro.waitFor();
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        runButton.setDisable(false);
                        loadingLabel.setText(null);
                    }
                });
                loadingIndicator.setVisible(false);
                System.out.println("completed!!\n");
                return sleep_ms;
            }
        };
        loadingIndicator.setVisible(true);
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
        //System.out.println("LOADING.... Please wait\n");
        
        //System.out.println("complete!!");
        //System.out.println(command + " exitValue() " + pro.exitValue());
      }

}
