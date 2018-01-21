package cli;

import config.GlobalConfiguration;
import constants.Constants;
import vn.locdt.JQuestion;
import vn.locdt.answer.Answer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by locdt on 1/21/2018.
 */
public class ConnectionQuestion extends QuestionCLI {
    public ConnectionQuestion(JQuestion jQuestion) {
        super(jQuestion);
        this.nextQuestion = new PojoGeneratorQuestion(jQuestion);
    }

    @Override
    protected void preQuestion() {
        askForDatabaseType();
        askForDatabaseUrl();
        askForDatabaseUsername();
        askForDatabasePassword();
    }

    @Override
    protected void postQuestion() {

    }

    @Override
    protected void run() {
        System.out.print("Check connection...");
        try {
            checkConnection();
            System.out.println("Connect successfully!");
        } catch (Exception e) {
            System.out.println("Connect unsuccessfully!");
        }
    }

    private void askForDatabaseType() {
        String type = jQuestion.select("What is your database type?", "dbType", Constants.getDatabaseTypes()).getValue();
        for (Constants.DBType dbType : Constants.DBType.values()) {
            try {
                if (dbType.getType().equals(type)) {
                    Class.forName(dbType.getDriver());
                    System.out.println(dbType.getType() + " Driver Loaded...");
                    GlobalConfiguration.dbType = type;
                }
            } catch (ClassNotFoundException e) {
                System.out.println(dbType.getType() + " Driver Not Found...");
                System.exit(1);
            }
        }
    }

    private void askForDatabaseUrl() {
        String dbUrl = jQuestion.input("Database url:", "dbUrl").getValue();
        GlobalConfiguration.dbUrl = dbUrl;
    }

    private void askForDatabaseUsername() {
        String dbUser = jQuestion.input("Database username:", "dbUser").getValue();
        GlobalConfiguration.dbUser = dbUser;
    }

    private void askForDatabasePassword() {
        String dbPass = jQuestion.input("Database password:", "dbPass").getValue();
        GlobalConfiguration.dbPass = dbPass;
    }

    private void checkConnection() throws Exception {
        GlobalConfiguration.connection = DriverManager.getConnection(
                        GlobalConfiguration.dbUrl,
                        GlobalConfiguration.dbUser,
                        GlobalConfiguration.dbPass);
    }
}
