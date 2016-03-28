package Chapter14.myquizcard;

/**
 * Created by Sergei on 25.03.2016.
 */
public class QuizCard {
    private String answer;
    private String question;

    QuizCard() {

    }


    public QuizCard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }
}
