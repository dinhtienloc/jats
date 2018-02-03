package vn.locdt.jats.core.shell.command;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;
import vn.locdt.jats.core.QuestionStatus;
import vn.locdt.jats.core.question.factory.DatabaseQuestionFactory;
import vn.locdt.jats.core.setting.SettingData;
import vn.locdt.jats.core.util.Utils;

/**
 * Created by locdt on 1/26/2018.
 */

@Component
public class CreatePojoCommand implements CommandMarker {
    @CliCommand(value = { "entity:gen"})
    public String entityGenerate() {
        Utils.printLog("Generating entity...");
        if (!SettingData.isHbmConfigurationCreated()) {
            QuestionStatus status = new DatabaseQuestionFactory().start();
            if (status.equals(QuestionStatus.STOP)) return null;
        }

        return "Generate successfully";
    }
}
