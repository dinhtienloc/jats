package vn.locdt.jats.core;

import org.springframework.shell.event.AbstractShellStatusPublisher;
import vn.locdt.jats.module.shell.listener.AppShellStatusListener;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

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

        // Set a suitable priority level on Spring Framework log messages
        Logger sfwLogger = Logger.getLogger("org.springframework");
        sfwLogger.setLevel(Level.OFF);

        // Set a suitable priority level on Roo log messages
        // (see ROO-539 and HandlerUtils.getLogger(Class))
//        Logger rooLogger = Logger.getLogger("org.springframework.shell");
//        rooLogger.setLevel(Level.OFF);

//        Bootstrap.main(args);
        Bootstrap bootstrap = new Bootstrap(args);
        AbstractShellStatusPublisher shellStatusPublisher = bootstrap.getJLineShellComponent();
        shellStatusPublisher.addShellStatusListener(new AppShellStatusListener());
        bootstrap.run();
    }

}
