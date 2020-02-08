A-Team 55
Dante Pizzini LEC 004 X-Team 117  
Joe Cambio LEC 004 004 X-Team 121
Henry Adkison  LEC 004 X-Team 114
Parker Breene LEC 004 X-Team 114
Eric Brown LEC 004 X-Team 114

QuizGenerator revision 2:

The QuizGenerator GUI is operational with buttons, labels, and multiple
scenes to direct the user around how to make questions, load them in from a file,
make a quiz, and show a report of their quiz results. All of the options
are currently hard-coded, and will be implemented in a later revision. 

QuizGenerator revision 3:

The QuizGenerator GUI currently allows a user to import new questions from a 
.JSON file as well as add their own unique questions. Upon startup the GUI displays
the first scene which credits each member of A-Team 55 with writing the program. 
From this scene a user can hit continue and the GUI home page is displayed. From 
this page a user has the option to click an import button, add question button, quit
button, save to file button, or take quiz button. There is also a combo box containing 
the current list of topics stored in the program dataBase as well as a text box for a 
user to input the number of questions they would like to be quizzed on. There is also a 
text box located at the bottom of the page for a user to enter a name they wish to 
save the current question data base as. Upon visiting the GUI home page for the first time
there are no questions and no topics, questions must be imported or created by the program user.

If the user hits the import button they are taken to a screen with a text box for the
user to type in the name of the .JSON file they would like to import and button below
this text box which says import and must be clicked after typing a valid .JSON file
name. If the user enters a .JSON file name that does not exist an alert is displayed
saying the .JSON file does not exist. If the user attempts to hit the import without
entering a .JSON file name an alert is thrown saying the text box must have valid
input. There is also a back button for the user to press which will return them to
the home page of the GUI if the user does not wish/have a .JSON file to import.

If the user hits the add new question button on the home screen they are taken 
to another page where the user can enter their question topic, question, optional
image related to the question, optional metadata related to the question, and five
multiple choice answers with one answer being correct and four answers being 
incorrect. The user can then press the create question button located in the lower
right corner of the GUI screen to add their new question to the program database.
If the user does not input text into all boxes except for the image box
and metadata box an alert will be thrown indicating that the topic, question, or 
multiple choice answers cannot be left blank. There is also a back button if the user
decides they do not wish to enter a new question and pressing this back button will
return the user to the homepage.

If the user hits the save button on the GUI home screen the current question data base 
will be saved to a .JSON file. The name of this file will be specified by the user by
entering the desired name in the text box at the bottom of the GUI home screen, if no
name has been specified then the question data base will be saved as default.JSON

After the user has imported questions or added their own questions the total number
of questions stored in the program database is displayed at the bottom of the GUI
home page. The combo box containing all question topics also updates and displays the
current available topics in alphabetical order. A user can click each topic they wish
to be quizzed on and enter the number of questions they would like to be quizzed on.
After selecting topics and entering a number of desired questions to be quizzed on 
the user can hit the take quiz button, the program then builds a quiz and takes the
user to the next screen. If the user presses the take quiz button without selecting
a topic or without inputting a number of quiz questions to be tested on, an alert 
pops up notifying the user they must either select a topic or input a number of 
questions they would like to be quizzed on. If the user enters a number of quiz
questions to be taken that is larger than the current program database of questions
then the user will simply be tested on all questions currently in the question 
database from the chosen topic(s).

After the take quiz button has been pressed the program begins the quiz and takes 
the user to a new page displaying a question. From here the user selects an answer
by clicking the button displaying their desired answer then clicking the next button located 
at the lower right hand side of this GUI page. If the user does not select an answer
and hits the next button an alert pops up notifying the user they must select an 
answer. After the user selected an answer and hits the next button they are then taken
to an incorrect page or correct page depending on if the answer they selected was
correct or incorrect. From either the correct or incorrect page the user must press
a continue button located at the bottom center of the page to continue to the next
question or continue to the results page of the GUI.

After the user has taken the number of quiz questions desired or the maximum number
of quiz questions available they are brought the the results page of the GUI. On 
this page the total number correct, total number incorrect, total number of questions
and percentage of correct questions are displayed. The user can then hit a back 
button located at the bottom center of the results page to return to the GUI home 
screen. Returning to this home screen clears the quiz just taken, but does not clear
the total number of questions or question topics in the program database. 

Upon returning to the GUI homepage after taking a quiz the user can import questions,
add their own questions, save the current question data base or take another quiz as 
described above. At any time that the user attempts to end the program by clicking the
"X" icon located at the top right of the GUI window an alert is displayed asking the 
user if they would like to save the current question database to a .JSON file. The user
can press "okay" in the alert box to save the current question database, the question data 
base will be saved as the name entered in the text box located at the bottom of the GUI 
homepage. If no name is entered in this text box the file will be saved as default.JSON,
a user can save multiple times while running the program and their named save file will 
update accordingly, the program then closes. The user can press "cancel" in the alert box 
if they do not wish to save the current question database, this will close the program.

KNOWN DEFFECIENCIES:
We understand the implementation of our GUI program is not the most efficient as a large 
part of our program is built directly in the main.java start method. Using more methods 
and classes would make the code more readable and easier to follow for others, but given
how we setup milestone 2, our busy schedules, and that we did not realize how much we had
coded in the main start until later in the week, it did not seem feasible to re-organize
our code by the submission deadline. We feel the program we have delivered is still able
to be followed with the comments we have added and meets the project's requirements.