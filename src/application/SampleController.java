package application;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


// The controller class, combines all the logic and changes the application based on user input
public class SampleController {
	
	
	// FXML elements
	@FXML
	Button leftButton;
	@FXML
	Button rightButton;
	@FXML
	Button bottomButton;
	
	@FXML
	Label leftLabel;
	@FXML
	Label rightLabel;
	@FXML
	Label feedbackLabel;
	@FXML
	Label highScoreLabel;
	@FXML
	Label currentScoreLabel;
	
	@FXML
	AnchorPane anchorPane;
	
	// Using static keyword selector
	KeywordSelector ks = new StaticKeywordSelector();
	Random random = new Random();
	
	// The left and right keywords and their results
	String leftKeyword;
	String rightKeyword;
	
	// Current score and highscore
	int currentScore;
	int highScore;
	
	// Amount of Google results for the left and right keywords
	long leftKeywordResults;
	long rightKeywordResults;
	
	// Sets the text in the buttons
	public void setButtonText() {
		leftButton.setText(this.leftKeyword);
		rightButton.setText(this.rightKeyword);
	}
	
	// Sets the results as text below the corresponding buttons
	public void setResultText() {
		String leftFormattedNum = this.formatNumbers(this.leftKeywordResults);
		String rightFormattedNum = this.formatNumbers(this.rightKeywordResults);
		leftLabel.setText("(" + leftFormattedNum + ")");
		rightLabel.setText("(" + rightFormattedNum + ")");
	}
	
	// Clears result text
	public void clearResultText() {
		leftLabel.setText("");
		rightLabel.setText("");
	}
	
	// Clears all effects on anchor pane
	public void clearEffectsAnchorPane() {
		this.anchorPane.setEffect(null);
	}
	
	// Clears all style properties on anchor pane 
	public void clearStylePropertiesAnchorPane() {
		this.anchorPane.styleProperty().set(null);
	}
	
	// Clears feedback text
	public void clearFeedbackLabel() {
		this.feedbackLabel.setText("");
	}
	
	public void disableGuessButtons() {
		this.leftButton.setDisable(true);
		this.rightButton.setDisable(true);
	}
	
	
	public void enableGuessButtons() {
		this.leftButton.setDisable(false);
		this.rightButton.setDisable(false);
	}
	
	public void enableBottomButton() {
		this.bottomButton.setDisable(false);
	}
	
	public void disableBottomButton() {
		this.bottomButton.setDisable(true);
	}
	
	// Increments the current score
	public void incCurrentScore() {
		this.currentScore++;
		this.currentScoreLabel.setText(String.valueOf(this.currentScore));
		this.updateHighScore();
	}
	
	// Resets the current score
	public void resetCurrentScore() {
		this.currentScore = 0;
		this.currentScoreLabel.setText("0");
	}
	
	public void updateHighScore() {
		if (this.currentScore > this.highScore) {
			this.highScore = this.currentScore;
			this.highScoreLabel.setText(String.valueOf(this.highScore));
		}
	}
	
	// Formats the numbers by adding spaces between every third digit
	public String formatNumbers(long number) {
		DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);
        return decimalFormat.format(number);
	}
	
	// Returns True if left search has more or equal results than right search
	public boolean leftSearchHigherOrEqual() {
		return this.leftKeywordResults >= this.rightKeywordResults;
	}
	
	// Returns True if right search has more or equal results than left search
	public boolean rightSearchHigherOrEqual() {
		return this.rightKeywordResults >= this.leftKeywordResults;
	}
	
	// Effects added if guess is correct
	public void correctGuess() {
		this.incCurrentScore();
		this.anchorPane.styleProperty().set("-fx-background-color: #90FF63");
		this.feedbackLabel.setText("Correct!");
	}
	
	// Effects added if guess is wrong
	public void wrongGuess() {
		this.resetCurrentScore();
		this.anchorPane.styleProperty().set("-fx-background-color: #FF6363");
		this.feedbackLabel.setText("Wrong!");
	}
	
	
	// If user chooses left keyword
	@FXML
	public void chooseLeft() {
		
		this.guessMade();
		if (this.leftSearchHigherOrEqual()) {
			this.correctGuess();
		}
		else {
			wrongGuess();
		}
		
	}
	
	// If user chooses right keyword
	@FXML
	public void chooseRight() {
		
		this.guessMade();
		if (this.rightSearchHigherOrEqual()) {
			this.correctGuess();
		}
		else {
			wrongGuess();
		}
	}
	
	// If user starts new game
	@FXML
	public void handleStart() {
		this.enableGuessButtons();
		this.newRound();
	}
	
	// Starting a new round
	public void newRound() {
		
		// Disabling the "Start new round" forcing the user to make a guess
		this.disableBottomButton();
		
		// Clearing all effects and results
		this.clearResultText();
		this.clearEffectsAnchorPane();
		this.clearStylePropertiesAnchorPane();
		this.clearFeedbackLabel();
		
		List<String> keywords = ks.keywordList();
		
		// Choosing two random keywords
		int firstIndex = random.nextInt(keywords.size());
		int secondIndex = random.nextInt(keywords.size());
		
		String firstKeyword = keywords.get(firstIndex);
		String secondKeyword = keywords.get(secondIndex);
		
		this.leftKeyword = firstKeyword;
		this.rightKeyword = secondKeyword;
		
		this.setButtonText();

	}
	
	// Making a guess
	public void guessMade() {
		this.disableGuessButtons();
		this.enableBottomButton();
		GoogleSearch firstGS = new GoogleSearch(this.leftKeyword);
		GoogleSearch secondGS = new GoogleSearch(this.rightKeyword);
		this.leftKeywordResults = firstGS.NumberOfResults;
		this.rightKeywordResults = secondGS.NumberOfResults;
		this.setResultText();
	}
	
}
