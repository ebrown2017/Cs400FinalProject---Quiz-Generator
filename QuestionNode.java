package application;
import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

//Title:            QuestionNode.java
//Semester:         Spring 2019
//
//Authors:           Eric Brown, Henry Adkison, Parker Breene, Joseph Cambio, Dante Pizzini
//Email:            esbrown6@wisc.edu, hadkison@wisc.edu, pbreene@wisc.edu, jcambio@wisc.edu, pizzini@wisc.edu
//Lecturer's Name:  Andrew Kuemmel
//Lab Section:      LEC 004
//


/**
 * Creates Question Node
 * Node contains a VBox and a toggle group for choices
 */
public class QuestionNode implements NodeWrapperADT {
  VBox node;
  ToggleGroup choices;
  
  /**
   * Constructor
   */
  public QuestionNode(Question newQuestion) {

  }
  @Override
  public Node node() {
    return null;
  }
  
  /**
   * Returns node in the form of a VBox
   */
  public VBox getNode() {
    return this.node;
  }
  
  /**
   * Returns the choices for answers in the form of a toggle group
   */
  public ToggleGroup getChoices() {
    return this.choices;
  }

}