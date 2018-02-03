package vn.locdt.jats.addon.question.event;

public class NonBlockInputEvent {
    protected final int addedChar;

    protected boolean stop;

    public NonBlockInputEvent(int addedChar) {
        this.addedChar = addedChar;
    }

    public int getAddedChar() {
        return addedChar;
    }

    public void stop() {
        stop = true;
    }

    public boolean isStop() {
        return stop;
    }
}
