package application;
import java.util.ArrayList;

//Title:            Question.java
//Semester:         Spring 2019
//
//Authors:           Eric Brown, Henry Adkison, Parker Breene, Joseph Cambio, Dante Pizzini
//Email:            esbrown6@wisc.edu, hadkison@wisc.edu, pbreene@wisc.edu, jcambio@wisc.edu, pizzini@wisc.edu
//Lecturer's Name:  Andrew Kuemmel
//Lab Section:      LEC 004
//



public class Question {
  private String metadata; // a label used to organize our questions for easy retrieval (maybe)
  private String question; // a string input that represents a new question
  private String topic; // a string input that represents the topic which the new question belongs
  private String image; // an image related to the question, i.e. a dataStructure, algorithm, etc.
  ArrayList<Choice> choices; // an array list which stores the multiple choice answers for the
                             // question being created in this class

  /**
   * The constructor for a new question, takes input either entered manually by a user
   * or read from a JSON file and stores the question, question topic, multiple choice 
   * answers relating to the question, a potential image relating to the question, the 
   * topic to which the question pertains, and the correct answer to the newly constructed
   * question
   * 
   * @param metaInput - identifier for the newly added question
   * @param quesInput - the new question to be added
   * @param topInput - the topic the new question 
   * @param imageInput - a supplemental image related to the question
   * @param choiceInputs - ArrayList of the multiple choice answers related to the new question
   */
  public Question(String metaInput, String quesInput, String topInput, String imageInput,
      ArrayList<Choice> choiceInputs) {
    this.metadata = metaInput;
    this.question = quesInput;
    this.topic = topInput;
    this.image = imageInput;
    this.choices = choiceInputs;
  }

  /**
   * Getter method which returns the question string
   * @return question
   */
  public String getQuestion() {
    return this.question;
  }

  /**
   * Getter method which returns the ArrayList of multiple choice answers
   * @return choices
   */
  public ArrayList<Choice> getChoices() {
    return this.choices;
  }

  /**
   * Getter method which returns the answer to a question
   * @return answer
   */
  public String getAnswer() {
      String correct = null;
      for (int i = 0; i < choices.size(); i++) {
          if (choices.get(i).getIsCorrect()) {
              correct = choices.get(i).getChoice();
          }
      }
      return correct;
  }

  /**
   * Getter method which returns the metadata, our question identifier
   * @return metaData
   */
  public String getMetaData() {
    return this.metadata;
  }

  /**
   * Getter method which return the question topic
   * @return topic
   */
  public String getTopic() {
    return this.topic;
  }

  /**
   * Getter method which returns the image file relating to the question
   * @return image
   */
  public String getImage() {
    return this.image;
  }
}