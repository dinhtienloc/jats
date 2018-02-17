package vn.locdt.jats.core;

import org.springframework.shell.Bootstrap;
import org.springframework.shell.core.JLineShell;
import org.springframework.shell.event.AbstractShellStatusPublisher;
import vn.locdt.jats.module.shell.listener.AppShellStatusListener;

import java.util.logging.LogManager;

/**
 * Created by locdt on 1/25/2018.
 */
public class Main {
    /**
     * Main entry-point of the application.
     *
     * @param args command line arguments
     * @throws Exception
     */
    public static void main(String... args) throws Exception {
        LogManager.getLogManager().reset();
        Bootstrap bootstrap = new Bootstrap(args);
        AbstractShellStatusPublisher shellStatusPublisher = bootstrap.getJLineShellComponent();
        shellStatusPublisher.addShellStatusListener(new AppShellStatusListener());
        bootstrap.run();
    }
}
