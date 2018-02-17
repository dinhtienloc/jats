package vn.locdt.jats.module.shell.listener;

import org.springframework.shell.event.ShellStatus;
import org.springframework.shell.event.ShellStatusListener;
import org.springframework.stereotype.Component;
import vn.locdt.jats.module.shell.setting.SettingData;

/**
 * Created by locdt on 1/27/2018.
 */
@Component
public class AppShellStatusListener implements ShellStatusListener {
    @Override
    public void onShellStatusChange(ShellStatus oldStatus, ShellStatus newStatus) {
        if (oldStatus.getStatus().equals(ShellStatus.Status.STARTED)
                && newStatus.getStatus().equals(ShellStatus.Status.USER_INPUT)) {
            SettingData.loadConfiguration();
        }
//        System.out.println("Old: " + oldStatus.getStatus().name() + " -> " + newStatus.getStatus().name());
    }
}
