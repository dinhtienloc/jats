package shell;

import org.springframework.shell.Bootstrap;
import org.springframework.shell.core.JLineShellComponent;
import org.springframework.shell.event.AbstractShellStatusPublisher;
import vn.locdt.jats.module.shell.listener.AppShellStatusListener;

import java.io.IOException;
import java.util.logging.LogManager;

public class InitCommandTest {
	public static void main(String[] args) throws IOException {
		LogManager.getLogManager().reset();
		Bootstrap bootstrap = new Bootstrap(args);
		JLineShellComponent shell = bootstrap.getJLineShellComponent();
		shell.addShellStatusListener(new AppShellStatusListener());
		bootstrap.run();
	}
}
