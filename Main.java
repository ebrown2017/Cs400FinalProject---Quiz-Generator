package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.json.simple.parser.ParseException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

//Title:            Main.java
//Semester:         Spring 2019
//
//Authors:           Eric Brown, Henry Adkison, Parker Breene, Joseph Cambio, Dante Pizzini
//Email:            esbrown6@wisc.edu, hadkison@wisc.edu, pbreene@wisc.edu, jcambio@wisc.edu, pizzini@wisc.edu
//Lecturer's Name:  Andrew Kuemmel
//Lab Section:      LEC 004
//


public class Main extends Application {
  
  private String testQuestion;	//Main Question asked to user
  private String option1;		//Answer option 1
  private String option2;		//Answer option 2
  private String option3;		//Answer option 3
  private String option4;		//Answer option 4
  private String option5;		//Answer option 5

  private int currQuestionNum;	// Index of current question

  private String TopicOpt;	// Options of topics
  
  private int numCorrect;	// Number of questions got correct
  
  private ArrayList<String> topicsArray;	// Array of chosen topics
  
  private QuestionDatabase dataBase;	// default
  
  private QuestionDatabase quiz;	// Specific quiz chosen
  
  private ComboBox<String> chooseTopic;		// ComboBox of topics to choose
  
  private HBox topics;	// Topics that can be chosen
  
  private Label topicLabel;		// Label of topics
  
  private HBox numQ;	// Displays num of questions
  
  private Button importQuestion;	// Imports questions
  
  private Button addQuestion;	// Adds questions
  
  private Button takeQuiz;		// Takes quiz
  
  private VBox homePageBox;		// All home page options

  private Button questionPageBack;		// Back and next button on question page
  private Button questionPageNext;
  private Button answerA;		// Buttons for mult choice answers
  private Button answerB;
  private Button answerC;
  private Button answerD;
  private Button answerE;
  
  private String userChoice;	// String of users choice
  
  private Label mainQuestion;	// The question the user is asked
  
  private double percent;		// Variables and strings used to calculate results
  private String percentString;
  private Text percentCorrect;
  private Text correct;
  private Text incorrect;
  private Label blank1;
  private Label blank2;
  private Label blank3;
  private HBox testResults;
  
  private Text totQ;	// Total Questions
  
  private TextField numQuestions;	// Number of questions user wants out of total
  
  private ImageView image;		// Image view of provided image
  
  private VBox formattedQuestion;	// Questions in a vertical box
  
  private int numIncorrect;		// Number of incorrect questions guessed
  
  private int totalNUMQ;	// Total number of questions
  
  private TextField SaveFile;	// Text to save the file to
  
  private Button saveButton;	// Button to save the file
  
  
  
@Override
  public void start(Stage primaryStage) {
    
    numCorrect = 0;		// Declare num correct to 0
    
    dataBase = new QuestionDatabase();	// Instantiate the dataBase
    
    quiz = new QuestionDatabase();	// Instantiate the quiz
    
    topicsArray = new ArrayList<>();	// New array list of all topics
    
    final String FONT = "Verdand";		// Set default sizes, fonts, and colors used
    final String COLOR_RED = "-fx-background-color: firebrick;";
    final String COLOR_GREEN = "-fx-background-color: forestgreen;";
    final String COLOR_BLUE = "-fx-background-color: cornflowerblue;";
    final String COLOR_GREY = "-fx-base: #d3d3d3;";
    final int HEADER_TEXT_SIZE = 100;
    final int MEDIUM_TEXT_SIZE = 35;
    final int BUTTON_WIDTH = 200;
    final int BUTTON_HEIGHT = 75;

    try {
      BorderPane splashScreenRoot = new BorderPane(); // Create border panes for each scene
      BorderPane homePageRoot = new BorderPane();
      BorderPane questionPageRoot = new BorderPane();
      BorderPane incorrectAnswerRoot = new BorderPane();
      BorderPane correctAnswerRoot = new BorderPane();
      BorderPane newQuestionRoot = new BorderPane();
      BorderPane importFilesRoot = new BorderPane();
      BorderPane testResultsRoot = new BorderPane();

      Scene splashScreen = new Scene(splashScreenRoot, 1500, 1000); 			// Splash Screen
      Scene homePage = new Scene(homePageRoot, 1500, 1000); 					// Home Page
      Scene questionPage = new Scene(questionPageRoot, 1500, 1000); 			// Question Page
      Scene incorrectAnswerPage = new Scene(incorrectAnswerRoot, 1500, 1000); 	// Incorrect Answer
      Scene correctAnswerPage = new Scene(correctAnswerRoot, 1500, 1000); 		// Correct Answer
      Scene newQuestionPage = new Scene(newQuestionRoot, 1500, 1000); 			// Create New Question Page
      Scene importFilesPage = new Scene(importFilesRoot, 1500, 1000); 			// Import File Page
      Scene testResultsPage = new Scene(testResultsRoot, 1500, 1000); 			// Test Results Page

      splashScreenRoot.setStyle(COLOR_BLUE); // Set backgrounds to blue
      homePageRoot.setStyle(COLOR_BLUE);
      questionPageRoot.setStyle(COLOR_BLUE);
      incorrectAnswerRoot.setStyle(COLOR_RED);	// Incorrect background to Red
      correctAnswerRoot.setStyle(COLOR_GREEN);	// Correct background to Green
      newQuestionRoot.setStyle(COLOR_BLUE);
      importFilesRoot.setStyle(COLOR_BLUE);
      testResultsRoot.setStyle(COLOR_BLUE);

      //
      // SCENE 1
      //
      // SPLASH SCREEN
      //
      splashScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(splashScreen);	// Initalize first splash screen
      primaryStage.show();
      // -------------------------------------------SET TOP
      Text infoText = new Text("Quiz Generator");		// Create new label for splash screen
      infoText.setFont(Font.font(FONT, HEADER_TEXT_SIZE));

      splashScreenRoot.setTop(infoText);	// Add top info text to top center
      splashScreenRoot.setAlignment(infoText, Pos.TOP_CENTER);
      // -------------------------------------------SET CENTER
      Label authors = new Label("Authors:");		// Add author information
      Label henryInfo = new Label("Henry Adkison - hadkison@wisc.edu");
      Label parkerInfo = new Label("Parker Breene - pbreene@wisc.edu");
      Label ericInfo = new Label("Eric Brown - esbrown6@wisc.edu");		//<-- he's cool :)
      Label joeInfo = new Label("Joseph Cambio - jcambio@wisc.edu");
      Label danteInfo = new Label("Dante Pizzini - pizzini@wisc.edu");
      VBox splashScreenInfo = new VBox();		// Create VBox to store data

      authors.setFont(Font.font(MEDIUM_TEXT_SIZE));		// Set all the text size
      henryInfo.setFont(Font.font(MEDIUM_TEXT_SIZE));
      parkerInfo.setFont(Font.font(MEDIUM_TEXT_SIZE));
      ericInfo.setFont(Font.font(MEDIUM_TEXT_SIZE));
      joeInfo.setFont(Font.font(MEDIUM_TEXT_SIZE));
      danteInfo.setFont(Font.font(MEDIUM_TEXT_SIZE));
      splashScreenInfo.getChildren().addAll(authors, henryInfo, parkerInfo, ericInfo, joeInfo,
          danteInfo);

      splashScreenRoot.setCenter(splashScreenInfo);		// Put splashScreen info in middle
      splashScreenInfo.setAlignment(Pos.CENTER);
      // -------------------------------------------SET BOTTOM
      VBox contBox = new VBox();	// Create new Vbox to hold Cont button
      Button contButton = new Button("Continue");	// Create cont button
      
      contButton.setStyle(COLOR_GREY);		// Set color, size, and font of button
      contButton.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
      contBox.getChildren().setAll(contButton, new Label(""));
      contBox.setAlignment(Pos.BOTTOM_CENTER);

      splashScreenRoot.setBottom(contBox);		// Set contBox to bottom center
      splashScreenRoot.setAlignment(contBox, Pos.BOTTOM_CENTER);
      // -------------------------------------------SET RIGHT

      // -------------------------------------------SET LEFT

      //
      // SCENE 2
      //
      // HOMEPAGE
      //
      homePage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      // -----------------------------------------------------SET TOP
      Text homePageLabelText = new Text("Home");
      homePageLabelText.setFont(Font.font("Verdand", 100));

      homePageRoot.setTop(homePageLabelText);
      homePageRoot.setAlignment(homePageLabelText, Pos.TOP_CENTER);
      // -----------------------------------------------------SET CENTER
      Label numQuestionLabel = new Label("Number of Questions: ");	      // Create Number of Questions Label
      numQuestions = new TextField();	      // Create Number of Questions TextField
      numQuestions.setStyle(COLOR_GREY);
      numQ = new HBox();

      numQ.setAlignment(Pos.CENTER);
      numQ.getChildren().addAll(numQuestionLabel, numQuestions);

      String topicOptions[] = {};
      topicLabel = new Label("Choose Topic: ");	     // Create Take Quiz Button
      takeQuiz = new Button("Take Quiz");
      takeQuiz.setStyle(COLOR_GREY);
      chooseTopic = new ComboBox(FXCollections.observableArrayList(topicOptions));
           
      topics = new HBox();	// Create HBox for topics

      takeQuiz.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
      topics.getChildren().addAll(topicLabel, chooseTopic);
      topics.setAlignment(Pos.CENTER);

      addQuestion = new Button("Add New Question");	  // Create Add Question Button
      addQuestion.setStyle(COLOR_GREY);
      addQuestion.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);

      importQuestion = new Button("Import Questions");	   // Create Start Quiz Button
      importQuestion.setStyle(COLOR_GREY);
      importQuestion.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);

      SaveFile = new TextField();	     // Create save text field
      
      saveButton = new Button("Save Questions");	   // Create save button 
      saveButton.setStyle(COLOR_GREY);
      saveButton.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
          
      HBox hBoxSave = new HBox();
      hBoxSave.setAlignment(Pos.CENTER);
      hBoxSave.getChildren().addAll(new Label("Save Questions File Name: "), SaveFile);                
      
      homePageBox = new VBox();	    // Create HBox for all options

      homePageBox.getChildren().addAll(topics, new Label(""), numQ, new Label(""), importQuestion,
          new Label(""), addQuestion, new Label(""), takeQuiz, new Label(""),saveButton,new Label(""), hBoxSave );
      homePageBox.setAlignment(Pos.CENTER);

      homePageRoot.setCenter(homePageBox); // Add HBox to Center
      // ----------------------------------------------------SET BOTTOM
      VBox QBox = new VBox();	// Create VBox for the total num questions
      Text numQTotal = new Text("Total Number of Questions: " + dataBase.getNumQuestions());
      
      QBox.getChildren().addAll(numQTotal);		// add to VBox
      
      homePageRoot.setBottom(QBox);		// Add question box to bottom center
      QBox.setAlignment(Pos.BOTTOM_CENTER);
      // ----------------------------------------------------SET RIGHT

      // ----------------------------------------------------SET LEFT

      //
      // SCENE 3 A
      //
      // QUESTION PAGE IF QUESTION HAS IMAGE
      //
      questionPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      
      // -----------------------------------------------------SET TOP
      mainQuestion = new Label(testQuestion);	// Create main question label
      Label blank = new Label(" ");
      formattedQuestion = new VBox();

      formattedQuestion.getChildren().addAll(new Label("Question: " + (currQuestionNum +1) + " out of "
          + quiz.getNumQuestions() + " total Questions."),new Label(" "), mainQuestion);
      mainQuestion.setFont(Font.font(25));
      mainQuestion.setWrapText(true);

      questionPageRoot.setTop(formattedQuestion);	// Set formatted question to top center
      formattedQuestion.setAlignment(Pos.TOP_CENTER);
      // -----------------------------------------------------SET CENTER
      answerA = new Button("A: " + option1);	// Create buttons for each answer, color them grey
      answerA.setStyle(COLOR_GREY);
      answerB = new Button("B: " + option2);
      answerB.setStyle(COLOR_GREY);
      answerC = new Button("C: " + option3);
      answerC.setStyle(COLOR_GREY);
      answerD = new Button("D: " + option4);
      answerD.setStyle(COLOR_GREY);
      answerE = new Button("E: " + option5);
      answerE.setStyle(COLOR_GREY);
      
      VBox multipleChoiceBoxes = new VBox();	// Create VBox to hold all choices

      answerA.setMinSize(BUTTON_WIDTH + 150, BUTTON_HEIGHT + 30);	// Set size of buttons
      answerB.setMinSize(BUTTON_WIDTH + 150, BUTTON_HEIGHT + 30);
      answerC.setMinSize(BUTTON_WIDTH + 150, BUTTON_HEIGHT + 30);
      answerD.setMinSize(BUTTON_WIDTH + 150, BUTTON_HEIGHT + 30);
      answerE.setMinSize(BUTTON_WIDTH + 150, BUTTON_HEIGHT + 30);
      // -----------------------------------------------------SET BOTTOM
      HBox bottomButtons = new HBox();
      questionPageBack = new Button("Quit Quiz");	// Create buttons for back and next
      questionPageNext = new Button("Next Question");
      
      questionPageNext.setStyle(COLOR_GREY);	// Set buttons color and size
      questionPageBack.setStyle(COLOR_GREY);
      questionPageBack.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
      questionPageNext.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
      
      bottomButtons.getChildren().addAll(questionPageBack, new Label(" "), new Label(" "), new Label(" "), 
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), 
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), 
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), 
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), 
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), 
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), 
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), 
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), 
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), 
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), 
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), 
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "),
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), 
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), 
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), 
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), 
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "),
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "),
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "),
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "),
          new Label("                                                                          "),
          new Label("                                                                                 "),
          new Label(" "), new Label(" "), new Label(" "), new Label(" "), new Label(" "), questionPageNext);

      questionPageRoot.setBottom(bottomButtons);	// Set bottom buttons to bottom left
      questionPageRoot.setAlignment(bottomButtons, Pos.BOTTOM_LEFT);
      // -----------------------------------------------------SET RIGHT
      Label ans = new Label("Choose Answer");	// Create label for multiple choice boxes
      ans.setFont(Font.font(MEDIUM_TEXT_SIZE));

      multipleChoiceBoxes.getChildren().addAll( new Label(" "), new Label(" "),new Label(" "),new Label(" "),
          ans,new Label(" "), answerA, 
          new Label(" "),answerB,new Label(" "), answerC, new Label(" "), answerD, new Label(" "), answerE);
      
      questionPageRoot.setRight(multipleChoiceBoxes);	// Set Multiple choice boxes to center right
      questionPageRoot.setAlignment(multipleChoiceBoxes, Pos.CENTER_RIGHT);
      // -----------------------------------------------------SET LEFT
      VBox questionAndBack = new VBox();
      
      questionPageRoot.setLeft(questionAndBack);	// Set VBox to center left
      questionPageRoot.setAlignment(questionAndBack, Pos.CENTER_LEFT);

      //
      // SCENE 4
      //
      // INCORRECT ANSWER
      //
      incorrectAnswerPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

      // -----------------------------------------------------SET TOP
      Label incorrectAnswer = new Label("Incorrect Answer!");	// Create label for incorrect answer
  
      incorrectAnswer.setFont(Font.font(FONT, HEADER_TEXT_SIZE));	// Set font and size

      incorrectAnswerRoot.setTop(incorrectAnswer);		// Set label to top center
      incorrectAnswerRoot.setAlignment(incorrectAnswer, Pos.TOP_CENTER);
      // -----------------------------------------------------SET CENTER

      // -----------------------------------------------------SET BOTTOM
      VBox incorrectAnswerPageBottom = new VBox();
      Button incorrectAnswerPageNext = new Button("Next");	// Create next button
      incorrectAnswerPageNext.setStyle(COLOR_GREY);

      incorrectAnswerPageBottom.getChildren().addAll(incorrectAnswerPageNext, new Label(" ")); // Add button
      incorrectAnswerPageNext.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);

      incorrectAnswerRoot.setBottom(incorrectAnswerPageBottom);		// Set VBox to bottom center
      incorrectAnswerPageBottom.setAlignment(Pos.BOTTOM_CENTER);
      // -----------------------------------------------------SET RIGHT

      // -----------------------------------------------------SET LEFT

      //
      // SCENE 5
      //
      // CORRECT ANSWER
      //
      correctAnswerPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

      // -----------------------------------------------------SET TOP
      Label correctAnswer = new Label("Correct Answer!");		// Create label for correct answer
      correctAnswer.setFont(Font.font(FONT, HEADER_TEXT_SIZE));

      correctAnswerRoot.setTop(correctAnswer);		// Put label in top center
      correctAnswerRoot.setAlignment(correctAnswer, Pos.TOP_CENTER);
      // -----------------------------------------------------SET CENTER

      // -----------------------------------------------------SET BOTTOM
      VBox correctAnswerPageBottom = new VBox();
      Button correctAnswerPageNext = new Button("Next");	// Create next button
      correctAnswerPageNext.setStyle(COLOR_GREY);

      correctAnswerPageBottom.getChildren().addAll(correctAnswerPageNext, blank);	// Add button
      correctAnswerPageNext.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);

      correctAnswerRoot.setBottom(correctAnswerPageBottom);		// Set VBox to bottom center
      correctAnswerPageBottom.setAlignment(Pos.BOTTOM_CENTER);
      // -----------------------------------------------------SET RIGHT

      // -----------------------------------------------------SET LEFT

      //
      // SCENE 6
      //
      // CREATE NEW QUESTION PAGE
      //
      newQuestionPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

      // -----------------------------------------------------SET TOP
      Label topicTextFieldLabel = new Label("Topic: ");		// Create labels for each field
      Label createQuestionLabel = new Label("Question: ");
      Label imageTextFieldLabel = new Label("(Optional) Image: ");
      Label metadataTextFieldLabel = new Label("(Optional) Metadata: ");
      TextField topicField = new TextField();		// Create textfields for each category
      TextField questionField = new TextField();
      TextField imageField = new TextField();
      TextField metadataField = new TextField();
      HBox topicForQuestionHBox = new HBox();	// Create HBoxs for each field
      HBox questionHBox = new HBox();
      HBox imageHBox = new HBox();
      HBox metadataHBox = new HBox();
      VBox topicForQuestionVBox = new VBox();	// Create VBox for all HBoxs

      topicTextFieldLabel.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));	// Set size and font of labels
      topicField.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT - 25);
      createQuestionLabel.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));
      questionField.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT - 25);
      imageTextFieldLabel.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));
      imageField.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT - 25);
      metadataTextFieldLabel.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));
      metadataField.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT - 25);
      
      questionHBox.getChildren().addAll(createQuestionLabel, questionField);	// Add entries to each Box
      topicForQuestionHBox.getChildren().addAll(topicTextFieldLabel, topicField);
      imageHBox.getChildren().addAll(imageTextFieldLabel, imageField);
      metadataHBox.getChildren().addAll(metadataTextFieldLabel, metadataField);
      
      topicForQuestionVBox.getChildren().addAll(new Label(" "), topicForQuestionHBox,
          new Label(" "), questionHBox, new Label(" "), imageHBox, new Label(" "), metadataHBox);

      newQuestionRoot.setTop(topicForQuestionVBox);		// Allign all HBoxs and VBoxs to Center
      topicForQuestionVBox.setAlignment(Pos.CENTER);
      topicForQuestionHBox.setAlignment(Pos.CENTER);
      questionHBox.setAlignment(Pos.CENTER);
      imageHBox.setAlignment(Pos.CENTER);
      metadataHBox.setAlignment(Pos.CENTER);
      // -----------------------------------------------------SET CENTER
      Label correctAnswerA = new Label("Enter the Correct Answer Here: ");	// Create labels for each answer
      Label falseAnswerB = new Label("Enter a False Answer Here: ");
      Label falseAnswerC = new Label("Enter a False Answer Here: ");
      Label falseAnswerD = new Label("Enter a False Answer Here: ");
      Label falseAnswerE = new Label("Enter a False Answer Here: ");
      TextField enteredAnswerA = new TextField();	// Create TextField for each answer
      TextField enteredAnswerB = new TextField();
      TextField enteredAnswerC = new TextField();
      TextField enteredAnswerD = new TextField();
      TextField enteredAnswerE = new TextField();
      HBox inputAnswerA = new HBox();		// Create HBox for each answer
      HBox inputAnswerB = new HBox();
      HBox inputAnswerC = new HBox();
      HBox inputAnswerD = new HBox();
      HBox inputAnswerE = new HBox();
      VBox newQuestionPageCenter = new VBox();		// Create VBox to store all answers

      correctAnswerA.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE - 15));	// Set buttons font and size
      falseAnswerB.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE - 15));
      falseAnswerC.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE - 15));
      falseAnswerD.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE - 15));
      falseAnswerE.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE - 15));
      
      enteredAnswerA.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT - 25);	// Set Buttons size and color
      enteredAnswerA.setStyle(COLOR_GREY);
      enteredAnswerB.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT - 25);
      enteredAnswerB.setStyle(COLOR_GREY);
      enteredAnswerC.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT - 25);
      enteredAnswerC.setStyle(COLOR_GREY);
      enteredAnswerD.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT - 25);
      enteredAnswerD.setStyle(COLOR_GREY);
      enteredAnswerE.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT - 25);
      enteredAnswerE.setStyle(COLOR_GREY);
      
      inputAnswerA.getChildren().addAll(correctAnswerA, enteredAnswerA);	// Add answer results and answers
      inputAnswerB.getChildren().addAll(falseAnswerB, enteredAnswerB);
      inputAnswerC.getChildren().addAll(falseAnswerC, enteredAnswerC);
      inputAnswerD.getChildren().addAll(falseAnswerD, enteredAnswerD);
      inputAnswerE.getChildren().addAll(falseAnswerE, enteredAnswerE);
      
      newQuestionPageCenter.getChildren().addAll(inputAnswerA, new Label(" "), inputAnswerB, new Label(" "),
          inputAnswerC, new Label(" "), inputAnswerD, new Label(" "), inputAnswerE, new Label(" "));

      newQuestionRoot.setCenter(newQuestionPageCenter);	 //Place buttons in center of page
      inputAnswerA.setAlignment(Pos.CENTER);
      inputAnswerB.setAlignment(Pos.CENTER);
      inputAnswerC.setAlignment(Pos.CENTER);
      inputAnswerD.setAlignment(Pos.CENTER);
      inputAnswerE.setAlignment(Pos.CENTER);
      newQuestionPageCenter.setAlignment(Pos.CENTER);
      // -----------------------------------------------------SET BOTTOM

      // -----------------------------------------------------SET RIGHT
      Button newQuestionPageNext = new Button("Create Question");	// Create create question button
      newQuestionPageNext.setStyle(COLOR_GREY);
      newQuestionPageNext.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);

      newQuestionRoot.setRight(newQuestionPageNext);	// Add button to bottom right
      newQuestionRoot.setAlignment(newQuestionPageNext, Pos.BOTTOM_RIGHT);
      // -----------------------------------------------------SET LEFT
      Button newQuestionPageBack = new Button("Back");	// Create back button
      newQuestionPageBack.setStyle(COLOR_GREY);
      newQuestionPageBack.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);

      newQuestionRoot.setLeft(newQuestionPageBack);		// Add button to bottom left
      newQuestionRoot.setAlignment(newQuestionPageBack, Pos.BOTTOM_LEFT);

      //
      // SCENE 7
      //
      // IMPORT FILE PAGE
      //
      importFilesPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      // -----------------------------------------------------SET TOP

      // -----------------------------------------------------SET CENTER
      Label importFileLabel = new Label("Enter Filename: ");	// Create import file button and label
      Button importButton = new Button("Import");
      TextField fileName = new TextField();
      HBox importFileHBox = new HBox();		// Create HBox for storing label and button
      VBox importFileVBox = new VBox();		// Create VBox for storing all import entries
      importFileLabel.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));	// Set size and font
      fileName.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT - 25);
      importButton.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
      importButton.setStyle(COLOR_GREY);
      importFileHBox.getChildren().addAll(importFileLabel, fileName);	// Add label and button to HBox
      importFileVBox.getChildren().addAll(importFileHBox, blank, importButton);		// Add HBox and import button

      importFilesRoot.setCenter(importFileVBox);
      importFileVBox.setAlignment(Pos.CENTER);	// Set all boxes to center
      importFileHBox.setAlignment(Pos.CENTER);
      // -----------------------------------------------------SET BOTTOM
      Button importFilesPageBack = new Button("Back");	// Create back button, set size and color
      importFilesPageBack.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
      importFilesPageBack.setStyle(COLOR_GREY);
      VBox backButtonVBox = new VBox();		// Create VBox for button
      backButtonVBox.getChildren().addAll(importFilesPageBack, new Label(" "));

      importFilesRoot.setBottom(backButtonVBox);	// Display VBox at bottom center of page
      backButtonVBox.setAlignment(Pos.BOTTOM_CENTER);
      // -----------------------------------------------------SET RIGHT

      // -----------------------------------------------------SET LEFT

      //
      // SCENE 8
      //
      // TEST RESULTS PAGE
      //
      testResultsPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      // -----------------------------------------------------SET TOP

      // -----------------------------------------------------SET CENTER	
      percent = (((double) numCorrect / (double) quiz.getNumQuestions()));	// Calculate quiz stats
      percent = Math.round(percent);
      percent = percent * 100;
      percentString = (percent + "%");
      percentCorrect = new Text(percentString);
      correct = new Text(numCorrect + " Correct");
      incorrect = new Text(numIncorrect + " Incorrect");
      Integer num = totalNUMQ;
      totQ = new Text("Total Number of Questions: " + num.toString());	// Display total number of questions
      blank1 = new Label("         ");
      blank2 = new Label("         ");
      blank3 = new Label("         ");
      testResults = new HBox();		// Create HBox to put all test results in

      testResults.getChildren().addAll(percentCorrect, blank2, correct, blank3, incorrect, blank1, totQ);
      percentCorrect.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));	// Set font and size
      correct.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));
      incorrect.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));
      totQ.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));
      blank1.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));
      blank2.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));
      blank3.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));

      testResultsRoot.setCenter(testResults);	// Display test results in middle of page
      testResults.setAlignment(Pos.CENTER);
      // -----------------------------------------------------SET BOTTOM
      Button testResultsPageBack = new Button("Back");	// Create back button, set size and color
      testResultsPageBack.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
      testResultsPageBack.setStyle(COLOR_GREY);
      VBox backButton8VBox = new VBox();	// Create VBox for back button
      backButton8VBox.getChildren().addAll(testResultsPageBack, new Label(" "));

      testResultsRoot.setBottom(backButton8VBox);	// Display VBox in center bottom
      backButton8VBox.setAlignment(Pos.BOTTOM_CENTER);
      // -----------------------------------------------------SET RIGHT

      // -----------------------------------------------------SET LEFT

      //
      //
      // EVENT HANDLERS
      //
      //

      //
      // -------------------------------------------UNIVERSAL
      //
      /*
       * Event Handler method for checking if the user wants to save their questions to a json file when they click to exit the program
       */
      primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() { // CONTINUE BUTTON -> HOME PAGE
        @Override
        public void handle(WindowEvent e) {
          Alert saveClose = new Alert(AlertType.CONFIRMATION, "WOULD YOU LIKE TO SAVE?\nCANCEL: Exit without Saving\n"
          		+ "OK: Exit and Save\nIf Save File Field is empty, saving to \"Default.json\"\nHitting the \"X\" will exit without saving");
          
          Optional<ButtonType> result = saveClose.showAndWait();	// Display alert for option to save before quitting
          if (result.get() == ButtonType.CANCEL) { // Hitting cancel doesn't save and exits
              System.exit(0);
          } else if (result.get() == ButtonType.OK) { // Hitting ok saves and exits
              if (SaveFile.getText() == null || SaveFile.getText().trim().isEmpty()) { // If the save text field is empty use a default name
              dataBase.saveQuestionsToJSON(new File("Default.json"));
              } else {
                  dataBase.saveQuestionsToJSON(new File(SaveFile.getText() + ".json")); // Else use the chosen name
              }
              System.exit(0);	// Quit the program
          }
        }
      });
      //
      // -------------------------------------------SPLASH SCREEN
      //
      contButton.setOnAction(new EventHandler<ActionEvent>() { // CONTINUE BUTTON -> HOME PAGE
        @Override
        public void handle(ActionEvent e) {
          primaryStage.setScene(homePage);	// Set screen to home page
          primaryStage.show();
        }
      });
      //
      // -------------------------------------------HOME SCREEN PAGE
      //
      /*
       * Event Handler for clicking the save questions button that checks to see if the file can be created/accessed
       */
      saveButton.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e) {
            
          Alert Fnf = new Alert(AlertType.ERROR, "FILE DOES NOT EXIST OR CANNOT BE ACCESSED"); // Alert if the file cannot be made/accessed
          Alert quitWSave = new Alert(AlertType.CONFIRMATION, "QUESTIONS HAVE BEEN SAVED"); // Alert to confirm saved questions
          
          try {
          if(SaveFile.getText() == null || SaveFile.getText().trim().isEmpty() == true) // If the text box is empty
            throw new Exception();             
          dataBase.saveQuestionsToJSON(new File(SaveFile.getText()+ ".json")); // Save to the given file      
          quitWSave.showAndWait(); 	// Alert to confirm saving
          }catch (Exception q) {
            Fnf.showAndWait();	 // Alert if the file is incorrect in any way      
          }   
        }    
      });
      // Start Quiz
      takeQuiz.setOnAction(new EventHandler<ActionEvent>() { // TAKE QUIZ BUTTON -> QUESTION PAGE                                                    // PAGE
        @Override
        public void handle(ActionEvent e) {
          Alert textError = new Alert(AlertType.ERROR, "NUMBER OF QUESTIONS HAS TO BE AN INTEGER");
          Alert topicError = new Alert(AlertType.ERROR, "SELECT A TOPIC");
          int TxtInput = 0;
          TopicOpt = (String) chooseTopic.getValue();

          try {
            TxtInput = Integer.parseInt(numQuestions.getText());	// Get question text
          } catch (NumberFormatException k) {
            TxtInput = -69;
          }
          if (TxtInput == 0 || TxtInput == -69) {
            textError.showAndWait();	// If text is null, display alert
            primaryStage.setScene(homePage);	// Set screen to home page
            primaryStage.show();
          } 
          else if(TopicOpt == null) {
            topicError.showAndWait();	// If topic is null, display alert
          }
          else { 
            System.out.println(topicsArray);
            for(int i = 0; i < topicsArray.size(); i++) {	// For loop to add questions to quiz
              List<Question> list = dataBase.getQuestions(topicsArray.get(i));
              for(int j = 0; j < list.size(); j++) {
                if(quiz.getAllQuestions().size() == Integer.parseInt((numQuestions.getText()))) {
                  break;
                }
                quiz.addQuestion(list.get(j));	// Add question to quiz
              }
            }
            
            totalNUMQ = quiz.getNumQuestions();	// Set total num Questions in quiz
            topicsArray.clear();	// Clear topics array
                      	
            Label img = new Label("Image");		// Create label for inputting image
            img.setFont(Font.font(MEDIUM_TEXT_SIZE));

            testQuestion = quiz.getAllQuestions().get(currQuestionNum).getQuestion();	// Get answers form question
            option1 = quiz.getAllQuestions().get(currQuestionNum).choices.get(0).getChoice();
            option2 = quiz.getAllQuestions().get(currQuestionNum).choices.get(1).getChoice();
            option3 = quiz.getAllQuestions().get(currQuestionNum).choices.get(2).getChoice();
            option4 = quiz.getAllQuestions().get(currQuestionNum).choices.get(3).getChoice();
            option5 = quiz.getAllQuestions().get(currQuestionNum).choices.get(4).getChoice();
           
            if(quiz.getAllQuestions().get(currQuestionNum).getImage().equals("none")) {            
              questionAndBack.getChildren().setAll(new Label("NO IMAGE"));	// If no image, don't display
            }
            else { 
              try {
                image = new ImageView( new Image(quiz.getAllQuestions().get(currQuestionNum).getImage()));
                
                questionAndBack.getChildren().setAll( new Label(""), new Label(""),new Label(""),
                    new Label(""),img, new Label(" "), image);	// If image, display in question
              }
              catch(Exception ex) {
                questionAndBack.getChildren().setAll(new Label ("URL NOT FOUND OR NOT VALID"));
              }
            }
            answerA.setText(option1);	// Set text for answers
            answerB.setText(option2);
            answerC.setText(option3);
            answerD.setText(option4);
            answerE.setText(option5);
            mainQuestion.setText(testQuestion);
            
            formattedQuestion.getChildren().setAll(new Label("Question: " + (currQuestionNum + 1) + " out of "
                + quiz.getNumQuestions() + " total Questions."),new Label(" "), mainQuestion);
            mainQuestion.setFont(Font.font(25));
            mainQuestion.setWrapText(true);

            primaryStage.setScene(questionPage);	// Set screen to question page
            primaryStage.show();
          }
        }
      });
      
      //---------------EVENT HANDLERS FOR ANSWER CHOICES----------------------
      
      answerA.setOnAction(new EventHandler<ActionEvent>() { // Selects answer A
        @Override
        public void handle(ActionEvent e) {
          userChoice = answerA.getText();
        }
      });
      answerB.setOnAction(new EventHandler<ActionEvent>() { // Selects answer B
        @Override
        public void handle(ActionEvent e) {
          userChoice = answerB.getText();
        }
      });
      answerC.setOnAction(new EventHandler<ActionEvent>() { // Selects answer C
        @Override
        public void handle(ActionEvent e) {
          userChoice = answerC.getText();
        }
      });
      answerD.setOnAction(new EventHandler<ActionEvent>() { // Selects answer D
        @Override
        public void handle(ActionEvent e) {
          userChoice = answerD.getText();
        }
      });
      answerE.setOnAction(new EventHandler<ActionEvent>() { // Selects answer E
        @Override
        public void handle(ActionEvent e) {
          userChoice = answerE.getText();
        }
      });
      //--------------EVENT HANDLER FOR CHOOSING TOPIC-------------------------
      chooseTopic.setOnAction(new EventHandler<ActionEvent>() { //
        @Override
        public void handle(ActionEvent e) {
          
          String chosen = (String) chooseTopic.getValue();	// Get chosen topic(s)
          topicsArray.remove(null);
          if(!topicsArray.contains(chosen)) {	// Add if not already in
            topicsArray.add(chosen);
          }
          else {
            topicsArray.remove(chosen);
          }
        }
      });
      //---------------EVENT HANDLER FOR ADDING/IMPORTING QUESIONS------------
      addQuestion.setOnAction(new EventHandler<ActionEvent>() { // ADD QUESTION BUTTON -> ADD
                                                                // QUESTION PAGE
        @Override
        public void handle(ActionEvent e) {
          
          chooseTopic.setItems(null);	//Clear and set topics
          chooseTopic.setItems(dataBase.getTopics());
          topicsArray.clear();			//Clear topics and numQuestions when topic chosen
          numQuestions.clear();
          
          primaryStage.setScene(newQuestionPage);	//Set screen to Question Page
          primaryStage.show();
        }
      });

      importQuestion.setOnAction(new EventHandler<ActionEvent>() { // IMPORT QUESTIONS BUTTON ->
                                                                   // IMPORT QUESTIONS PAGE
        @Override
        public void handle(ActionEvent e) {
          primaryStage.setScene(importFilesPage);	//Set screen to Import Questions Page
          primaryStage.show();
        }
      });
      //
      // -------------------------------------------QUESTION PAGE
      //
      questionPageBack.setOnAction(new EventHandler<ActionEvent>() { // BACK BUTTON -> HOME PAGE
        @Override
        public void handle(ActionEvent e) {
          chooseTopic.setItems(null);	// Set clear quiz
          chooseTopic.setItems(dataBase.getTopics());
          topicsArray.clear();
          numQuestions.clear();
          
          quiz = new QuestionDatabase();	// Create new quiz of type question database
          new QuestionDatabase();
          currQuestionNum = 0;
          numCorrect = 0;
    
          primaryStage.setScene(homePage);	// Set screen to Question Page
          primaryStage.show();      
        }
      });

      questionPageNext.setOnAction(new EventHandler<ActionEvent>() { // NEXT BUTTON -> INCORRECT/CORRECT ANSWER
        @Override
        public void handle(ActionEvent e) {
          if(userChoice == null) {
            Alert textError = new Alert(AlertType.ERROR, "MUST SELECT AN ANSWER");
            textError.showAndWait();	// If no answer chosen, display alert
            return;
          }
          
          if(quiz.getAllQuestions().get(currQuestionNum).getAnswer().equals(userChoice)) {
            primaryStage.setScene(correctAnswerPage);	// Set screen to correct page
            primaryStage.show();
            userChoice = null;
          }
          else {
          primaryStage.setScene(incorrectAnswerPage);	// Set screen to incorrect page
          primaryStage.show();
          userChoice = null;
          }
        }
      });
      //
      // -------------------------------------------INCORRECT PAGE
      //
      incorrectAnswerPageNext.setOnAction(new EventHandler<ActionEvent>() { // NEXT BUTTON -> TEST RESULTS PAGE
        @Override
        public void handle(ActionEvent e) {
          numIncorrect++;	// Inc number of incorrect questions
          System.out.println(numIncorrect);
          currQuestionNum++;	// Inc current question index
         
          if(quiz.getAllQuestions().size() > currQuestionNum) {		// Fill all options with answers
          testQuestion = quiz.getAllQuestions().get(currQuestionNum).getQuestion();
          option1 = quiz.getAllQuestions().get(currQuestionNum).choices.get(0).getChoice(); 
          option2 = quiz.getAllQuestions().get(currQuestionNum).choices.get(1).getChoice();
          option3 = quiz.getAllQuestions().get(currQuestionNum).choices.get(2).getChoice();
          option4 = quiz.getAllQuestions().get(currQuestionNum).choices.get(3).getChoice();
          option5 = quiz.getAllQuestions().get(currQuestionNum).choices.get(4).getChoice();
         
          if(quiz.getAllQuestions().get(currQuestionNum).getImage().equals("none")) {            
            questionAndBack.getChildren().setAll(new Label("NO IMAGE"));	// If no image, set to No Image
          }
          else { 
            image = new ImageView( new Image(quiz.getAllQuestions().get(currQuestionNum).getImage()));
            questionAndBack.getChildren().setAll( new Label(" "), image);	// If image, display
          }
       
          answerA.setText(option1);		// Set answers in choices
          answerB.setText(option2);
          answerC.setText(option3);
          answerD.setText(option4);
          answerE.setText(option5);
          mainQuestion.setText(testQuestion);
          
          formattedQuestion.getChildren().setAll(new Label("Question: " + (currQuestionNum + 1) + " out of "
              + quiz.getNumQuestions() + " total Questions."),new Label(" "), mainQuestion);
          mainQuestion.setFont(Font.font(25));	// Display main question
          mainQuestion.setWrapText(true);
    
            primaryStage.setScene(questionPage);	// Set screen to question page
            primaryStage.show();
          }
          else {
             primaryStage.setScene(testResultsPage);	// Set screen to results page
             primaryStage.show();
          }
          
        percent = (((double) numCorrect / (double) quiz.getNumQuestions()));	// Calculate results
        percent = percent * 100;
        percentString = (percent + "%");
        percentCorrect = new Text(percentString);	// Create text for results
        correct = new Text(numCorrect + " Correct");
        incorrect = new Text(numIncorrect + " Incorrect");
        Integer num = totalNUMQ;
        totQ = new Text("Total Number of Questions: " + num.toString());
        blank1 = new Label("         ");
        blank2 = new Label("         ");
        blank3 = new Label("         ");
        testResults = new HBox();	// Create HBox to store results

        testResults.getChildren().addAll(percentCorrect, blank2, correct, blank3, incorrect,
            blank1,totQ);
        percentCorrect.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));	// Set fonts and sizes
        correct.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));
        incorrect.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));
        totQ.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));
        blank1.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));
        blank2.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));
        blank3.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));

        testResultsRoot.setCenter(testResults);	// Display test results in Center
        testResults.setAlignment(Pos.CENTER);
        }
      });
      //
      // -------------------------------------------CORRECT PAGE
      //
       correctAnswerPageNext.setOnAction(new EventHandler<ActionEvent>() { //NEXT BUTTON -> QUESTION PAGE
         @Override
         public void handle(ActionEvent e) {
         currQuestionNum++;	// Inc index of cur question on
         
         if(quiz.getAllQuestions().size() > currQuestionNum) {	
         testQuestion = quiz.getAllQuestions().get(currQuestionNum).getQuestion();
         option1 = quiz.getAllQuestions().get(currQuestionNum).choices.get(0).getChoice();
         option2 = quiz.getAllQuestions().get(currQuestionNum).choices.get(1).getChoice();
         option3 = quiz.getAllQuestions().get(currQuestionNum).choices.get(2).getChoice();
         option4 = quiz.getAllQuestions().get(currQuestionNum).choices.get(3).getChoice();
         option5 = quiz.getAllQuestions().get(currQuestionNum).choices.get(4).getChoice();
        
         if(quiz.getAllQuestions().get(currQuestionNum).getImage().equals("none")) { // If no image           
           questionAndBack.getChildren().setAll(new Label("NO IMAGE"));
         }
         else { 
           try {
             ImageView image = new ImageView( new Image(quiz.getAllQuestions().get(currQuestionNum).getImage()));
             questionAndBack.getChildren().setAll( new Label(" "), image);	// If image exists
           }
           catch(Exception ex) {
             questionAndBack.getChildren().setAll(new Label ("URL NOT FOUND OR NOT VALID"));
           }
         }
         
         answerA.setText(option1);	// Set Answer Boxes
         answerB.setText(option2);
         answerC.setText(option3);
         answerD.setText(option4);
         answerE.setText(option5);
         mainQuestion.setText(testQuestion);
         
         formattedQuestion.getChildren().setAll(new Label("Question: " + (currQuestionNum + 1) + " out of "
             + quiz.getNumQuestions() + " total Questions."),new Label(" "), mainQuestion);
         mainQuestion.setFont(Font.font(25));
         mainQuestion.setWrapText(true);
         
           primaryStage.setScene(questionPage);	// Set screen to question page
           primaryStage.show();
         }
         else {
            primaryStage.setScene(testResultsPage);	// Set screen to results page
            primaryStage.show();
         }
         
         numCorrect++;
         System.out.println(numCorrect);
         
         percent = (((double) numCorrect / (double) quiz.getNumQuestions()));	// Calculate Scores
         percent = percent * 100;
         percentString = (percent + "%");
         percentCorrect = new Text(percentString);	// Create text for quiz stats
         correct = new Text(numCorrect + " Correct");
         incorrect = new Text(numIncorrect + " Incorrect");
         Integer num = totalNUMQ;
         totQ = new Text("Total Number of Questions: " + num.toString());
         blank1 = new Label("         ");
         blank2 = new Label("         ");
         blank3 = new Label("         ");
         testResults = new HBox();	// Put all results in HBox

         testResults.getChildren().addAll(percentCorrect, blank2, correct, blank3, incorrect,
             blank1,totQ);
         percentCorrect.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));	// Set fonts and size
         correct.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));
         incorrect.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));
         totQ.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));
         blank1.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));
         blank2.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));
         blank3.setFont(Font.font(FONT, MEDIUM_TEXT_SIZE));

         testResultsRoot.setCenter(testResults);	// Display results in middle of page
         testResults.setAlignment(Pos.CENTER);
         }
       });
      //
      // -------------------------------------------CREATE NEW QUESTION PAGE
      //
      newQuestionPageBack.setOnAction(new EventHandler<ActionEvent>() { // BACK BUTTON -> HOME PAGE
        @Override
        public void handle(ActionEvent e) {
          primaryStage.setScene(homePage);	// Set screen to homePage
          primaryStage.show();
        }
      });

      newQuestionPageNext.setOnAction(new EventHandler<ActionEvent>() { // NEXT BUTTON -> HOME PAGE
        @Override        
        public void handle(ActionEvent e) {
          String correctAnswer;	// Create strings for answer choices
          String falseAnswer1;
          String falseAnswer2;
          String falseAnswer3;
          String falseAnswer4;
          String topic;		// Create strings for topic, questionText, image, and metadata
          String questionText;
          String image;
          String metadata;
          ArrayList<Choice> choices = new ArrayList<Choice>();	// Create array list for all choices
          
          correctAnswer = (String) enteredAnswerA.getText();	// Fill choices with answers
          falseAnswer1 = (String) enteredAnswerB.getText();
          falseAnswer2 = (String) enteredAnswerC.getText();
          falseAnswer3 = (String) enteredAnswerD.getText();
          falseAnswer4 = (String) enteredAnswerE.getText();
          topic = (String) topicField.getText();	// Fill question data with corresponding data
          questionText = (String) questionField.getText();
          image = (String) imageField.getText();
          metadata = (String) metadataField.getText();                    

          Alert badTextField = new Alert(AlertType.ERROR, "CANNOT LEAVE NON-OPTIONAL TEXT FIELDS EMPTY");

          if (correctAnswer == null || falseAnswer1 == null || falseAnswer2 == null || falseAnswer3 == null ||
        		  falseAnswer4 == null || topic == null || questionText == null) {
              badTextField.showAndWait();	// If improper text field in question, display Alert
              return;
          }
          if (correctAnswer.length() == 0 || falseAnswer1.length() == 0 || falseAnswer2.length() == 0 ||
        		  falseAnswer3.length() == 0 || falseAnswer4.length() == 0 || topic.length() == 0 || questionText.length() == 0) {
            badTextField.showAndWait();		// If improper text field in question, display Alert
            return;
          }
          
          if (image == null || image.equals("")) {	// If no image text input, set image to null
              image = "none";
          }
          if (metadata == null || metadata.equals("")) {	// If no metadata text input, set metadata to null
              metadata = "unused";
          }
          
          choices.add(new Choice(true, correctAnswer));		// Add choices to answer buttons
          choices.add(new Choice(false, falseAnswer1));
          choices.add(new Choice(false, falseAnswer2));
          choices.add(new Choice(false, falseAnswer3));
          choices.add(new Choice(false, falseAnswer4));
          
          Question newQuestion = new Question(metadata, questionText, topic, image, choices);
          dataBase.addQuestion(newQuestion);	// Create new question with questions data
          
          numQTotal.setText("Total Number of Questions: " + dataBase.getNumQuestions());	// Display total num Questions
                    
          chooseTopic.setItems(dataBase.getTopics().sorted());	// Fill topic box with topics
          
          enteredAnswerA.clear();	// Clear all answers
          enteredAnswerB.clear();
          enteredAnswerC.clear();
          enteredAnswerD.clear();
          enteredAnswerE.clear();
          topicField.clear();	// Clear topic field
          questionField.clear();
          imageField.clear();	// Clear image field
          metadataField.clear();
          
          primaryStage.setScene(homePage);	// Set screen to Home Page
          primaryStage.show();
        }
      });
      //
      // -------------------------------------------IMPORT FILE PAGE
      //
      importFilesPageBack.setOnAction(new EventHandler<ActionEvent>() { // BACK BUTTON -> HOME PAGE
        @Override
        public void handle(ActionEvent e) {
          primaryStage.setScene(homePage);
          primaryStage.show();
        }
      });

      importButton.setOnAction(new EventHandler<ActionEvent>() { // IMPORT BUTTON -> HOME PAGE
        @Override
        public void handle(ActionEvent e) {
          Alert formatError = new Alert(AlertType.ERROR, "Incorrect Format");  
          Alert FileNotFound = new Alert(AlertType.ERROR, "File Not Found");
          try {
          dataBase.loadQuestionsFromJSON(new File(fileName.getText()));
          numQTotal.setText("Total Number of Questions: " + dataBase.getNumQuestions());
          
          chooseTopic.setItems(dataBase.getTopics());;

          fileName.clear();	// Clear fileName 
          }catch(ParseException c) {
            formatError.showAndWait();	// Alert: Formatting error
            return;
          }
          catch (Exception k) {
            FileNotFound.showAndWait();	// Alert: File not found
            return;
          }
          primaryStage.setScene(homePage);	// Set screen to home page
          primaryStage.show();  
        }
      });
      //
      // -------------------------------------------TEST RESULTS PAGE
      //
      testResultsPageBack.setOnAction(new EventHandler<ActionEvent>() { // BACK BUTTON -> HOME PAGE
        @Override
        public void handle(ActionEvent e) {
          chooseTopic.setItems(null);
          chooseTopic.setItems(dataBase.getTopics());
          quiz = new QuestionDatabase();
          topicsArray.clear();	//Clear topics and reset question stats after exiting
          currQuestionNum = 0;
          numCorrect = 0;
          numIncorrect = 0;
          numQuestions.clear();
          percent = 0;
          
          primaryStage.setScene(homePage);	// Set screen to home page
          primaryStage.show();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {	// Launch program
    launch(args);
  }
}