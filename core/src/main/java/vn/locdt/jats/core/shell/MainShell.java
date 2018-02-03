package vn.locdt.jats.core.shell;

import org.springframework.shell.Bootstrap;
import org.springframework.shell.event.AbstractShellStatusPublisher;
import vn.locdt.jats.core.shell.listener.AppShellStatusListener;

import java.util.logging.LogManager;

/**
 * Created by locdt on 1/25/2018.
 */
public class MainShell {
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
