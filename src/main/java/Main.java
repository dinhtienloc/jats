import cli.ConnectionQuestion;
import cli.QuestionCLI;
import vn.locdt.JQuestion;

/**
 * Created by locdt on 1/20/2018.
 */
public class Main {
    public static void main(String[] args) {
        JQuestion jQuestion = new JQuestion();
        QuestionCLI q = new ConnectionQuestion(jQuestion);
        q.start();
    }
}
