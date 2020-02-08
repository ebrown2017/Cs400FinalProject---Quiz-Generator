package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * This class holds all the questions that need to be stored in a HashMap and also handles taking in
 * questions from a JSON file and also writing questions to a JSON file
 */
public class QuestionDatabase implements QuestionDatabaseADT {
    HashMap<String, ArrayList<Question>> topics;
    private int numQuestions = 0;


    /*
     * Constructor method instantiates the HashMap to hold the questions
     */
    public QuestionDatabase() {
        topics = new HashMap<String, ArrayList<Question>>();
    }

    /*
     * Returns a count of the number of questions
     */
    public int getNumQuestions() {
        return numQuestions;
    }

    /*
     * This method adds a question to the database while also adding the questions topic if it does
     * not already exist (non-Javadoc)
     * 
     * @see application.QuestionDatabaseADT#addQuestion(application.Question)
     */
    @Override
    public void addQuestion(Question question) {
        if (topics.containsKey(question.getTopic())) { // if the topic exists add the question there
            topics.get(question.getTopic()).add(question);
        } else {
            ArrayList<Question> topicQuestionsToAdd = new ArrayList<Question>(); // add a topic if
                                                                                 // it doesn't exist
                                                                                 // yet
            topicQuestionsToAdd.add(question); // add the question to the new topic
            topics.put(question.getTopic(), topicQuestionsToAdd);
        }
        numQuestions++; // increment the number of questions
    }

    @SuppressWarnings("unchecked")
    @Override
    public void saveQuestionsToJSON(File newJSONfile) {

        ArrayList<Choice> choices; // holds the choices
        JSONArray choiceArray; // holds the choices in a JSON object
        JSONObject curChoice; // holds a choice in a JSON object
        JSONObject questionObject; // holds a question in a JSON object
        Question curQuestion; // holds a question
        JSONObject questionArrayObject = new JSONObject(); // holds all the questions
        JSONArray questionArray = new JSONArray(); // holds the array of questions

        List<Question> allQuestions = getAllQuestions(); // get all the questions
        for (int i = 0; i < allQuestions.size(); i++) {
            curQuestion = allQuestions.get(i);
            questionObject = new JSONObject();
            // retrieve all the values needed
            questionObject.put("meta-data", curQuestion.getMetaData());
            questionObject.put("questionText", curQuestion.getQuestion());
            questionObject.put("topic", curQuestion.getTopic());
            questionObject.put("image", curQuestion.getImage());
            choices = curQuestion.getChoices();
            choiceArray = new JSONArray();
            // loop for the choices
            for (int j = 0; j < choices.size(); j++) {
                curChoice = new JSONObject();
                if (choices.get(i).getIsCorrect()) {
                    curChoice.put("isCorrect", "T");
                } else {
                    curChoice.put("isCorrect", "F");
                }
                curChoice.put("choice", choices.get(i).getChoice());
                choiceArray.add(curChoice);
            }
            // add to the array
            questionObject.put("choiceArray", choiceArray);
            questionArray.add(questionObject);
        }
        // finish the last JSON object
        questionArrayObject.put("questionArray", questionArray);

        // write the JSON object to a file
        try (FileWriter file = new FileWriter(newJSONfile)) {
            file.write(questionArrayObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * This method returns all the questions in a list
     */
    public List<Question> getAllQuestions() {
        List<Question> list = new ArrayList<Question>();
        for (ArrayList<Question> questionList : topics.values()) {
            for (Question question : questionList) {
                list.add(question);
            }
        }
        return list;
    }

    /*
     * This method returns the questions from a given topic (non-Javadoc)
     * 
     * @see application.QuestionDatabaseADT#getQuestions(java.lang.String)
     */
    @Override
    public List<Question> getQuestions(String topic) {
        List<Question> list = new ArrayList<Question>();
        ArrayList<Question> questions = topics.get(topic);
        for (int i = 0; i < questions.size(); i++) {
            list.add(questions.get(i));
        }
        return list;
    }

    /*
     * This method loads the questions from a JSON file directly into the database by topic
     * (non-Javadoc)
     * 
     * @see application.QuestionDatabaseADT#loadQuestionsFromJSON(java.io.File)
     */
    @Override
    public void loadQuestionsFromJSON(File JSONFile)
                    throws FileNotFoundException, IOException, ParseException {
        ArrayList<Choice> addChoices; // temp variable for the choices to a question
        Question addQuestion; // temp variable for the question to add
        String t = "T"; // check true/false value of JSON string
        boolean isTrue = false; // temp variable to store choice boolean value
        Object JSONParser = new JSONParser().parse(new FileReader(JSONFile));
        JSONObject jo = (JSONObject) JSONParser;
        JSONArray questions = (JSONArray) jo.get("questionArray"); // parse all questions
        for (int i = 0; i < questions.size(); i++) {
            JSONObject questionObject = (JSONObject) questions.get(i);
            addChoices = new ArrayList<Choice>();
            String metadata = (String) questionObject.get("meta-data"); // get the meta data
            String questionText = (String) questionObject.get("questionText"); // get the question
                                                                               // itself
            String topic = (String) questionObject.get("topic"); // get the topic
            String image = (String) questionObject.get("image"); // get the image
            JSONArray choices = (JSONArray) questionObject.get("choiceArray"); // get the list of
                                                                               // choices
            for (int j = 0; j < choices.size(); j++) {
                isTrue = false; // reset to false
                JSONObject choiceObject = (JSONObject) choices.get(j);
                String tempIsTrue = (String) choiceObject.get("isCorrect");
                if (t.equals(tempIsTrue.toUpperCase())) { // determine if choice is true or false
                    isTrue = true;
                }
                addChoices.add(new Choice(isTrue, (String) choiceObject.get("choice"))); // create
                                                                                         // the
                                                                                         // choices
                                                                                         // array
            }
            addQuestion = new Question(metadata, questionText, topic, image, addChoices);
            if (topics.containsKey(topic)) { // if the topic exists add the question there
                topics.get(topic).add(addQuestion);
            } else {
                ArrayList<Question> topicQuestionsToAdd = new ArrayList<Question>(); // add a topic
                                                                                     // if it
                                                                                     // doesn't
                                                                                     // exist yet
                topicQuestionsToAdd.add(addQuestion); // add the question to the new topic
                topics.put(topic, topicQuestionsToAdd);
            }
            numQuestions++; // increment the number of questions
        }
    }

    /*
     * This method returns an observable list of topics to be used for displaying the available topics in a combo
     * box in the GUI (non-Javadoc)
     * 
     * @see application.QuestionDatabaseADT#getTopics()
     */
    @Override
    public ObservableList<String> getTopics() {
        List<String> list = new ArrayList<String>();
        for (String a : topics.keySet()) {
            list.add(a);
        }
        ObservableList<String> oList = FXCollections.observableArrayList(list);
        return oList;
    }
}