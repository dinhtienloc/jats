package vn.locdt.jats.module.shell.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import vn.locdt.jats.module.shell.config.Configuration;
import vn.locdt.jats.util.common.LogUtils;

@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        LogUtils.printLog("Load existing config...");
        Configuration.loadAllConfigurations();
    }
}
