package vn.locdt.jats.question.database;

import vn.locdt.jats.question.QuestionStatus;
import vn.locdt.jats.setting.SettingData;
import vn.locdt.jats.setting.DatabaseSetting;
import vn.locdt.jats.constants.Constants;
import vn.locdt.jats.question.QuestionCLI;
import vn.locdt.jats.util.Utils;
import vn.locdt.jquestion.JQuestion;

import java.sql.SQLException;

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