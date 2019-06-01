# GRID PATH TRACER

1. OBJECTIVE

The objective of the program is to trace a path from the first cell to the last cell.

2. PRE-REQUISITES

JDK 8 and Maven 3.6.0 should be installed

3. RUNNING THE PROGRAM

Open terminal and browse to the folder where pom.xml is present and run the 
following maven command

mvn clean package

The above command will first build the program, run all unit tests and then 
package it as a jar under target folder

Browse to the target folder now and then to run the program type
java -jar path-1.0-snapshot.jar

4. ASSUMPTION

Diagonal paths are allowed. This feature can however be turned off. To turn
off this feature, go to application.properties under resources folder and 
change the diagonal property to false