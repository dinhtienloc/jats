package vn.locdt.jats.shell.command;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;
import vn.locdt.jats.internal.generate.POJOGenerator;
import vn.locdt.jats.question.DatabaseQuestionFactory;
import vn.locdt.jats.setting.SettingData;

/**
 * Created by locdt on 1/26/2018.
 */

@Component
public class CreatePojoCommand implements CommandMarker {
    @CliCommand(value = { "entity:gen"})
    public String entityGenerate() {
        if (!SettingData.isHbmConfigurationCreated()) {
            new DatabaseQuestionFactory().start();
        }

        new POJOGenerator().generate();
        return "Generate successfully";
    }
}
