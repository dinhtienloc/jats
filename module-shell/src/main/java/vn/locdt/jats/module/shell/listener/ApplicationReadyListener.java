package vn.locdt.jats.module.shell.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import vn.locdt.jats.module.shell.config.Configuration;
import vn.locdt.jats.util.common.LogUtils;

@Component
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        LogUtils.printSuccessLog("Save configs...");
        Configuration.saveAllConfiguration();
    }
}
