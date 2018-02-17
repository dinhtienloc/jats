package vn.locdt.jats.entity.addon.question;

import vn.locdt.jats.bundle.question.JQuestion;
import vn.locdt.jats.module.shell.constants.Constants;
import vn.locdt.jats.module.shell.question.QuestionCLI;
import vn.locdt.jats.module.shell.question.QuestionStatus;
import vn.locdt.jats.module.shell.setting.DatabaseSetting;
import vn.locdt.jats.module.shell.setting.SettingData;

/**
 * Created by locdt on 1/21/2018.
 */
public class ConnectionQuestion extends QuestionCLI {

    @Override
    protected void run() {
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
        }

        if (SettingData.getDatabaseSetting().createConnection())
            status = QuestionStatus.CONTINUE;
        else
            status = QuestionStatus.STOP;
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
