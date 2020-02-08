package application;

//Title:            Choice.java
//Semester:         Spring 2019
//
//Authors:           Eric Brown, Henry Adkison, Parker Breene, Joseph Cambio, Dante Pizzini
//Email:            esbrown6@wisc.edu, hadkison@wisc.edu, pbreene@wisc.edu, jcambio@wisc.edu, pizzini@wisc.edu
//Lecturer's Name:  Andrew Kuemmel
//Lab Section:      LEC 004
//

/**
 * Defines choice as being correct
 * Has String choice which is a potential answer in the quiz
 */
public class Choice {
private Boolean isCorrect;
private String choice;

/**
 * Gets all question from the topic value string
 * @param String - Potential answer of questions
 * @param Boolean - Correct or Incorrect answer to question
 */
public Choice(Boolean isCorrect, String choice) {
  this.isCorrect = isCorrect;
  this.choice = choice;
}

/**
 * Checks to see if choice is correct for the question
 */
public Boolean getIsCorrect() {
  return this.isCorrect;
}

/**
 * Returns the potential answer of the question
 */
public String getChoice() {
  return this.choice;
}
}