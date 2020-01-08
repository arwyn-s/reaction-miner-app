#find src -name "*.class" -type f -delete
javac -cp src:dependencies/Motif:lib/*:dependencies/jbliss/:dependencies/jbliss/lib/* src/ruleMining/RPM/ComputeRPM.java
java -cp src:dependencies/Motif:lib/*:dependencies/jbliss/:dependencies/jbliss/lib/* -Djava.library.path=dependencies/jbliss/lib/ ruleMining.RPM.ComputeRPM
javac -cp src:dependencies/Motif:lib/*:dependencies/jbliss/:dependencies/jbliss/lib/* src/ruleMining/RuleMining.java
java -cp src:dependencies/Motif:lib/*:dependencies/jbliss/:dependencies/jbliss/lib/* -Djava.library.path=dependencies/jbliss/lib/ ruleMining.RuleMining 
javac -cp src:dependencies/Motif:lib/*:dependencies/jbliss/:dependencies/jbliss/lib/* src/reactionRuleNetwork/RRN.java
java -cp src:dependencies/Motif:lib/*:dependencies/jbliss/:dependencies/jbliss/lib/* -Djava.library.path=dependencies/jbliss/lib/ reactionRuleNetwork.RRN
javac -cp src:dependencies/Motif:lib/*:dependencies/jbliss/:dependencies/jbliss/lib/* src/pathwayPrediction/QueryTester.java
java -cp src:dependencies/Motif:lib/*:dependencies/jbliss/:dependencies/jbliss/lib/* -Djava.library.path=dependencies/jbliss/lib/ pathwayPrediction.QueryTester -source C00267 -target C00118 -paths 10
