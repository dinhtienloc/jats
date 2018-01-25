package vn.locdt.jats.question.database;

import vn.locdt.jats.setting.SettingData;
import vn.locdt.jats.setting.DatabaseSetting;
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
        if (SettingData.getDatabaseSetting() == null) {
            System.out.println("This is the first time you run Jats.");

            askForDatabaseType();
            askForDatabaseUrl();
            askForDatabaseUsername();
            askForDatabasePassword();

            return RunStatus.CONTINUE;
        }
        else {
            System.out.println("Restored last run configuration...");
            DatabaseSetting dbConfig = SettingData.getDatabaseSetting();
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
        SettingData.getDatabaseSetting().createConnection();
        return RunStatus.CONTINUE;
    }

    private void askForDatabaseType() {
        String type = JQuestion.select("What is your database type?", "dbType", Constants.DBType.getTypes()).getValue();
        SettingData.getDatabaseSetting().loadDriver(type);
    }

    private void askForDatabaseUrl() {
        String dbUrl = JQuestion.input("Database url:", "dbUrl").getValue();
        SettingData.getDatabaseSetting().setDbUrl(dbUrl);
    }

    private void askForDatabaseUsername() {
        String dbUser = JQuestion.input("Database username:", "dbUser").getValue();
        SettingData.getDatabaseSetting().setDbUser(dbUser);
    }

    private void askForDatabasePassword() {
        String dbPass = JQuestion.input("Database password:", "dbPass").getValue();
        SettingData.getDatabaseSetting().setDbPass(dbPass);
    }
}
