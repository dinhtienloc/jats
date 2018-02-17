package vn.locdt.jats.bundle.question.event;

public class InputEvent {
    private String inputValue;

    public InputEvent(String inputValue) {
        this.inputValue = inputValue;
    }

    public String getInputValue() {
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }
}
