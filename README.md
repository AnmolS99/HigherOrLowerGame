# HigherOrLowerGame
Game inspired by the Higher Lower Game. The aim of the game is to choose the word that has the most results on google. The program performs a real-time search on the words, and returns the number of results found (according to google). The program can perform search on any words, and is therefore not limited to the default words chosen in the "TopUSSearches.txt" file.

## How to run the application in developer mode

### Step 1
This application uses JSoup and JavaFX. To use this application, these libraries need to be added to the project. To do this download "jsoup-1.12.1.jar" and "javafx-sdk-11.0.2" and add them as libraries in the project.

### Step 2
If you are using Eclipse as IDE it is important to do the following:
* Go to arguments (Run -> Run Configurations... -> Arguments)
* Click on VM arguments
* Add the following: --module-path "/your_path/javafx-sdk-11.0.2/lib" --add-modules javafx.controls,javafx.fxml
* Replace "your_path" with the path to the directory containing the folder "javafx-sdk-11.0.2"
* Uncheck the box using -XstartOnFirstThread argument when launching with SWT.
* Click "Apply"
* Click "Run"

## How to run the application as a JAR-file
* Make sure you have downloaded and unpacked "javafx-sdk-11.0.2"
* Open the terminal
* Go to directory containing the JAR-file
* Write the following on Mac: java --module-path "/your_path/javafx-sdk-11.0.2/lib" --add-modules javafx.controls,javafx.fxml -jar HigherOrLowerGame-X.X.jar
* Write the following on Windows: java --module-path .\javafx-sdk-11.0.2\lib\ --add-modules javafx.controls,javafx.fxml -jar .\HigherOrLowerGame-X.X.jar
* Replace "your_path" with the path to the directory containing the "javafx-sdk-11.0.2" folder.
* Replace X.X with your version of the game
* Run the command

## Potential improvements
One potential improvement is to make the application into an executable file. One way of doing this is to make this application into a Maven project and then make it into an executable file.

Another improvement is to add new lists of words other than those in the "TopUSSearches.txt" file. This can be done by making a class implementing the HigherOrLowerGame/src/application/KeywordSelector.java interface, and change the KeywordSelector instance in HigherOrLowerGame/src/application/SampleController.java . Another way of doing this is by simply adding words into the "TopUSSearches.txt" file.
