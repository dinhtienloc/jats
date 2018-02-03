package vn.locdt.jats.core.question.database;

import vn.locdt.jats.core.QuestionCLI;
import vn.locdt.jats.core.QuestionStatus;
import vn.locdt.jats.core.constants.Constants;
import vn.locdt.jats.core.setting.DatabaseSetting;
import vn.locdt.jats.core.setting.SettingData;
import vn.locdt.jats.addon.question.JQuestion;

/**
 * Created by locdt on 1/21/2018.
 */
public class ConnectionQuestion extends QuestionCLI {

    public ConnectionQuestion() {super();}

    @Override
    protected QuestionStatus preQuestion() {
        QuestionStatus status = QuestionStatus.CONTINUE;

        if (!SettingData.isHbmConfigurationCreated()) {
            askForDatabaseType();
            askForDatabaseUrl();
            askForDatabaseUsername();
            askForDatabasePassword();
        }
        else {
            DatabaseSetting dbConfig = SettingData.getDatabaseSetting();
            if (!dbConfig.loadDriver(dbConfig.getDbType()))
                status = QuestionStatus.STOP;
            else if (!dbConfig.createConnection())
                status = QuestionStatus.STOP;
        }

        return status;
    }

    @Override
    protected QuestionStatus postQuestion() {
        return QuestionStatus.FINISHED;
    }

    @Override
    protected QuestionStatus run() {
        if (SettingData.getDatabaseSetting().createConnection())
            return QuestionStatus.CONTINUE;
        else
            return QuestionStatus.STOP;
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