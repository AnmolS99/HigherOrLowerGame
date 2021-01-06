# HigherOrLowerGame
Game inspired by the Higher Lower Game. The aim of the game is to choose the word that has the most results on google. The program performs a real-time search on the words, and returns the number of results found (according to google). The program can perform search on any words, and is therefore not limited to the default words chosen in the "TopUSSearches.txt" file.

## How to run the application

This application uses JSoup and JavaFX. To use this application, these libraries need to be added to the project. To do this download "jsoup-1.12.1.jar" and "javafx-sdk-11.0.2" and add the as libraries in the project.

If you are using Eclipse as IDE it is important to do the following:
* Go to arguments (Run -> Run Configurations... -> Arguments)
* Click on VM arguments
* Add the following: --module-path "/your_path/javafx-sdk-11.0.2/lib" --add-modules javafx.controls,javafx.fxml
* Replace "your_path" with the path to the directory containing the folder "javafx-sdk-11.0.2"
* Uncheck the box using -XstartOnFirstThread argument when launching with SWT.
* Click "Apply"
* Click "Run"
