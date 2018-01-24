package vn.locdt.jats;

import vn.locdt.jats.config.SettingData;
import vn.locdt.jats.exception.MismatchArgumentQuestionFactoryException;
import vn.locdt.jats.module.database.*;

/**
 * Created by locdt on 1/20/2018.
 */
public class Main {
    public static void main(String[] args) {
        printLogo();
        new SettingData().loadConfiguration();

        try {
            DatabaseQuestionFactory dbQuestionFactory = new DatabaseQuestionFactory();
            dbQuestionFactory.start();
        } catch (MismatchArgumentQuestionFactoryException e) {
            e.printStackTrace();
        }
    }

    public static void printLogo() {
        System.out.println("Welcome to Jats (Java Assistant Tools)");
    }
}
