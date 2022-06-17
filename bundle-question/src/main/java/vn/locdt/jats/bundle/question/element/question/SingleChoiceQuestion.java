package vn.locdt.jats.bundle.question.element.question;

import org.jline.reader.LineReader;
import org.jline.utils.NonBlockingReader;
import vn.locdt.jats.bundle.question.JQuestion;
import vn.locdt.jats.bundle.question.constant.VKConstants;
import vn.locdt.jats.bundle.question.element.item.choice.Selector;
import vn.locdt.jats.bundle.question.element.item.choice.SingleChoice;
import vn.locdt.jats.bundle.question.event.ChangeSelectorEvent;
import vn.locdt.jats.bundle.question.event.ChooseSelectorEvent;
import vn.locdt.jats.bundle.question.event.NonBlockInputEvent;
import vn.locdt.jats.bundle.question.listener.ChoiceListener;
import vn.locdt.jats.bundle.question.listener.NonBlockInputListener;
import vn.locdt.jats.bundle.question.util.ConsoleUtils;
import vn.locdt.jats.bundle.question.util.DetectArrowKey;

import java.io.IOException;
import java.util.List;

public class SingleChoiceQuestion extends Question<SingleChoice, String> implements NonBlockInputListener, ChoiceListener {

    private SingleChoiceQuestion(LineReader reader, String title, String name) {
        super(reader, new SingleChoice(title, name));
    }

    SingleChoiceQuestion(LineReader reader, String title, String name, boolean isPrintedResult) {
        this(reader, title, name);
        this.isPrintedResult = isPrintedResult;
        this.updateRenderHeight();
    }

    public SingleChoiceQuestion(LineReader reader, String title, String name, List<Selector> selectors, boolean isPrintedResult) throws IOException {
        this(reader, title, name, isPrintedResult);
        this.item.setSelectors(selectors);
        this.updateRenderHeight();
    }

    public SingleChoiceQuestion(LineReader reader, String title, String name, String[] selections) {
        this(reader, title, name);
        for (Object select : selections) {
            this.item.addSelector(new Selector(select.toString()));
        }
        this.updateRenderHeight();
    }

    public SingleChoiceQuestion(LineReader reader, String title, String name, List<String> selections) {
        this(reader, title, name);
        for (Object select : selections) {
            this.item.addSelector(new Selector(select.toString()));
        }
        this.updateRenderHeight();
    }

    public SingleChoiceQuestion addSelector(String value) {
        this.item.addSelector(new Selector(value));
        this.updateRenderHeight();
        return this;
    }

    public SingleChoiceQuestion addSelector(String value, boolean isActive) {
        Selector selector = new Selector(value);
        this.item.addSelector(selector);
        if (isActive)
            this.item.setActivedSelector(selector);

        this.updateRenderHeight();
        return this;
    }

    public SingleChoiceQuestion addSelectors(List<Selector> selectors) {
        this.item.addSelectors(selectors);
        this.updateRenderHeight();
        return this;
    }

    public SingleChoiceQuestion addSelectors(String... values) {
        this.item.addSelectors(values);
        this.updateRenderHeight();
        return this;
    }

    @Override
    public String prompt() {
        ConsoleUtils.renderQuestion(this);

        if (this.item.getSelectors().size() == 0) {
            this.setValue("");
        } else {

            // read input
            int input;
            boolean finished;
            NonBlockingReader nonBlockingReader = JQuestion.startCharacterReader(this.lineReader);

            try {
                do {
                    input = nonBlockingReader.read();
                    finished = this.onInput(new NonBlockInputEvent(input));
                } while (!finished);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JQuestion.stopCharacterReader();
        }

        return this.getValue();
    }

    private void changeActiveSelector(VKConstants.ArrowKey arrowKey) {
        int cursor = this.item.indexOfActivedSelector();
        Selector lastSelector = this.item.getActivedSelector();
        Selector nextSelector;

        List<Selector> selectors = this.item.getSelectors();
        switch (arrowKey) {
            case VK_UP:
                if (cursor > 0) cursor--;
                break;
            case VK_DOWN:
                if (cursor < selectors.size() - 1) cursor++;
                break;
        }

        nextSelector = selectors.get(cursor);
        if (nextSelector != lastSelector) {
            this.item.setActivedSelector(nextSelector);
            this.onChanged(new ChangeSelectorEvent(lastSelector, nextSelector));
        }
    }

    protected boolean handleInput(int charCode, NonBlockInputEvent e) {
        if (charCode == VKConstants.VK_ENTER) {
            this.onChosen(new ChooseSelectorEvent(this.item.getActivedSelector()));
            e.stop();
        } else if (charCode == 27 && !DetectArrowKey.detecting) {
            DetectArrowKey.detect();
        } else if (DetectArrowKey.detecting) {
            VKConstants.ArrowKey arrowKey = DetectArrowKey.update(charCode);
            if (arrowKey != null)
                this.changeActiveSelector(arrowKey);
        } else if (e.getAddedChar() == VKConstants.VK_CTRL_D) {
            e.stop();
        } else if (ConsoleUtils.isWindowOS()) {
            this.handleWindowInput(charCode, e);
        }

        return e.isStop();
    }

    private void handleWindowInput(int charCode, NonBlockInputEvent e) {
        VKConstants.ArrowKey arrowKey = null;
        switch (charCode) {
            case VKConstants.WindowOS.VK_DOWN:
                arrowKey = VKConstants.ArrowKey.VK_DOWN;
                break;
            case VKConstants.WindowOS.VK_UP:
                arrowKey = VKConstants.ArrowKey.VK_UP;
                break;
            case VKConstants.WindowOS.VK_LEFT:
                arrowKey = VKConstants.ArrowKey.VK_LEFT;
                break;
            case VKConstants.WindowOS.VK_RIGHT:
                arrowKey = VKConstants.ArrowKey.VK_RIGHT;
                break;
        }

        if (arrowKey != null)
            this.changeActiveSelector(arrowKey);
    }

    @Override
    public String toString() {
        String str = ConsoleUtils.createTitle(this.item.getTitle() + "\n");
        List<Selector> selectors = this.item.getSelectors();
        if (selectors.size() == 0)
            return str;

        if (this.item.getActivedSelector() == null)
            this.item.setActivedSelector(this.item.getSelectors().get(0));

        for (Selector selector : selectors) {
            str += ConsoleUtils.printSelector(selector);
            if (selectors.indexOf(selector) < selectors.size() - 1)
                str += "\n";
        }

        return str;
    }

    @Override
    public boolean onInput(NonBlockInputEvent e) {
        int charCode = e.getAddedChar();
//        System.out.println(charCode);
//        return false;
        return this.handleInput(charCode, e);
    }

    @Override
    public void onChanged(ChangeSelectorEvent e) {
        ConsoleUtils.renderChoiceQuestion(this);
    }

    @Override
    public void onChosen(ChooseSelectorEvent e) {
        this.setValue(e.getSelector().getValue());
        if (this.isPrintedResult()) ConsoleUtils.printResult(this);
    }
}
