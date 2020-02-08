package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.json.simple.parser.ParseException;
import javafx.collections.ObservableList;

/*
 * This interface is for holding the required methods that are to be used by the QuestionDatabase Class
 */
public interface QuestionDatabaseADT {
    
    public void addQuestion(Question question); // add a question to the database
    
    public void saveQuestionsToJSON(File file); // save questions to a JSON file
    
    public List<Question> getQuestions(String string); // get the questions of a specific topic
    
    public void loadQuestionsFromJSON(File file) throws FileNotFoundException, IOException, ParseException; // load questions from a specific topic
    
    public ObservableList<String> getTopics(); // get a list of the topics
}