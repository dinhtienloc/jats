package vn.locdt.jats.core;

import org.fusesource.jansi.AnsiConsole;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import vn.locdt.jats.util.common.LogUtils;

@EnableAspectJAutoProxy
@SpringBootApplication(scanBasePackages = "vn.locdt.jats")
public class NewMain {
    public static void main(String[] args) {
	    AnsiConsole.systemInstall();
        SpringApplication.run(NewMain.class, args);
    }
}
