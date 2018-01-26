package vn.locdt.jats.shell.command;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;
import vn.locdt.jats.internal.generate.POJOGenerator;
import vn.locdt.jats.question.factory.DatabaseQuestionFactory;
import vn.locdt.jats.question.QuestionStatus;
import vn.locdt.jats.setting.SettingData;
import vn.locdt.jats.util.Utils;

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

        new POJOGenerator().generate();
        return "Generate successfully";
    }
}
