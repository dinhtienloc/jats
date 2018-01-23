package vn.locdt.jats.module.database.question;

import vn.locdt.jats.config.Configuration;
import vn.locdt.jats.config.ConfigurationData;
import vn.locdt.jats.config.DatabaseConfiguration;
import vn.locdt.jats.constants.Constants;
import vn.locdt.jats.question.QuestionCLI;
import vn.locdt.jquestion.JQuestion;

/**
 * Created by locdt on 1/21/2018.
 */
public class ConnectionQuestion extends QuestionCLI {

    public ConnectionQuestion() {super();}

    @Override
    protected RunStatus preQuestion() {
        if (ConfigurationData.getDatabaseConfiguration() == null) {
            System.out.println("This is the first time you run Jats.");

            askForDatabaseType();
            askForDatabaseUrl();
            askForDatabaseUsername();
            askForDatabasePassword();

            return RunStatus.CONTINUE;
        }
        else {
            System.out.println("Restored last run configuration...");
            DatabaseConfiguration dbConfig = ConfigurationData.getDatabaseConfiguration();
            dbConfig.loadDriver(dbConfig.getDbType());
            dbConfig.createConnection();
            return RunStatus.FINISHED;
        }
    }

    @Override
    protected RunStatus postQuestion() {
        return RunStatus.FINISHED;
    }

    @Override
    protected RunStatus run() {
        ConfigurationData.getDatabaseConfiguration().createConnection();
        return RunStatus.CONTINUE;
    }

    private void askForDatabaseType() {
        String type = JQuestion.select("What is your database type?", "dbType", Constants.getDatabaseTypes()).getValue();
        ConfigurationData.getDatabaseConfiguration().loadDriver(type);
    }

    private void askForDatabaseUrl() {
        String dbUrl = JQuestion.input("Database url:", "dbUrl").getValue();
        ConfigurationData.getDatabaseConfiguration().setDbUrl(dbUrl);
    }

    private void askForDatabaseUsername() {
        String dbUser = JQuestion.input("Database username:", "dbUser").getValue();
        ConfigurationData.getDatabaseConfiguration().setDbUser(dbUser);
    }

    private void askForDatabasePassword() {
        String dbPass = JQuestion.input("Database password:", "dbPass").getValue();
        ConfigurationData.getDatabaseConfiguration().setDbPass(dbPass);
    }
}
