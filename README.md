# Reaction Miner

## Pre-requisites.

[JDK(Java Developer Toolkit)](https://www.oracle.com/technetwork/java/javase/downloads/jdk13-downloads-5672538.html) >= 11
`$ java -version`
sample:
```
$ java -version
java version "13.0.1" 2019-10-15
Java(TM) SE Runtime Environment (build 13.0.1+9)
Java HotSpot(TM) 64-Bit Server VM (build 13.0.1+9, mixed mode, sharing)
```
[Download](https://gluonhq.com/products/javafx/) the latest JavaFx Libraries.
Extract and note the path.

## Usage.

To run the UI:

`$ javac --module-path path/to/javafx-sdk-13.0.1/lib --add-modules javafx.controls,javafx.fxml App.java `  
`$ javac --module-path path/to/javafx-sdk-13.0.1/lib --add-modules javafx.controls,javafx.fxml SceneController.java `  
`$ java --module-path path/to/javafx-sdk-13.0.1/lib --add-modules javafx.controls,javafx.fxml App `  

## Developer Notes.

### Version 1.0

##### Features.

* Runs reaction miner software as a runtime executable.
* Four inputs to be given (Source as KEGG ID, Target as KEGG ID, Organism as KEGG ID, No of paths to print).[KEGG](https://www.genome.jp/kegg/). 
* Clicking Run button will start the reaction miner with above parameters.
* UI indicates the background running process (a loading symbol and disabled run button).
* Prints the predicted path into a text area ordered as least edit distance.
* Clear will clear all the text fields and the text area.

##### Bugs.

* Will not handle when no inputs are provied to the textfields.
* UI windows is not resizable (Fixed size).
* If reaction miner has failed to provide any results , text area remains blank.
* Please do not remove compiles classes of the reaction miner software.