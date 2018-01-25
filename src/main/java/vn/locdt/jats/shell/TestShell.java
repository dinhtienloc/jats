package vn.locdt.jats.shell;

import org.springframework.shell.Bootstrap;

import java.util.logging.LogManager;

/**
 * Created by locdt on 1/25/2018.
 */
public class TestShell {
    /**
     * Main entry-point of the application.
     *
     * @param args command line arguments
     * @throws Exception
     */
    public static void main(String... args) throws Exception {
        LogManager.getLogManager().reset();
        Bootstrap.main(args);
        System.out.println("asdasda");
    }
}
