package vn.locdt.jats;

import vn.locdt.jats.setting.SettingData;
import vn.locdt.jats.module.database.*;

/**
 * Created by locdt on 1/20/2018.
 */
public class Main {
    public static void main(String[] args) {
        printLogo();
        new SettingData().loadConfiguration();
        DatabaseQuestionFactory dbQuestionFactory = new DatabaseQuestionFactory();
        dbQuestionFactory.start();
    }

    public static void printLogo() {
        System.out.println("▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
        System.out.println("             _   _    ____ _____ ");
        System.out.println("            | | / \\  / ___|_   _|");
        System.out.println("         _  | |/ _ \\ \\___ \\ | |  ");
        System.out.println("        | |_| / ___ \\ ___) || |  ");
        System.out.println("         \\___/_/   \\_|____/ |_|  ");
        System.out.println(" * Welcome to JATS (Java Assistant Tools) *");
        System.out.println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");

    }
}
