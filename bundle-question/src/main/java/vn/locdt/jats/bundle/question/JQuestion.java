package vn.locdt.jats.bundle.question;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;
import vn.locdt.jats.bundle.question.answer.Answer;
import vn.locdt.jats.bundle.question.element.question.*;
import vn.locdt.jats.bundle.question.exception.ConsoleNotInitializeException;

import java.io.IOException;
import java.util.*;

public class JQuestion {
    private static NonBlockingReader nonBlockingReader;

//    public static Answer input(String title, String name) {
//        try {
//            return new InputQuestion(title, name).prompt();
//        } catch (IOException | ConsoleNotInitializeException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static Answer input(String title) {
//        try {
//            return new InputQuestion(title).prompt();
//        } catch (IOException | ConsoleNotInitializeException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static Answer input(LineReader reader, String title) {
        try {
            return new InputQuestion(title).lineReader(reader).prompt();
        } catch (IOException | ConsoleNotInitializeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Answer maskInput(String title, String name, Character mask) {
        try {
            return new PasswordQuestion(title, name, mask).prompt();
        } catch (IOException | ConsoleNotInitializeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Answer select(String title, String name, String[] selection) {
        try {
            return new SingleChoiceQuestion(title, name, selection).prompt();
        } catch (IOException | ConsoleNotInitializeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Answer select(String title, String name, List<String> selection) {
        try {
            return new SingleChoiceQuestion(title, name, selection).prompt();
        } catch (IOException | ConsoleNotInitializeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Answer select(LineReader reader, String title, String name, List<String> selection) {
        try {
            return new SingleChoiceQuestion(title, name, selection).lineReader(reader).prompt();
        } catch (IOException | ConsoleNotInitializeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Answer autocompleteInput(LineReader reader, String title) {
        try {
            return new AutocompleteWithInputQuestion(title, null).lineReader(reader).prompt();
        } catch (IOException | ConsoleNotInitializeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static NonBlockingReader startCharacterReader(LineReader reader) {
        Terminal terminal = reader.getTerminal();
        terminal.enterRawMode();
        nonBlockingReader = terminal.reader();
        return nonBlockingReader;
    }

    public static void stopCharacterReader() {
    	nonBlockingReader = null;
    }
}
