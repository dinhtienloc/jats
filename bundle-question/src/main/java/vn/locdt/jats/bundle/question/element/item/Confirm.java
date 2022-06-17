package vn.locdt.jats.bundle.question.element.item;

/**
 * Created by locdt on 1/10/2018.
 */
public class Confirm extends Item {
    private String yesLabel;
    private String noLabel;
    private boolean confirmed;

    public Confirm(String title, String name, String value) {
        super(title, name, value);
    }

    public Confirm(String title, String name) {
        super(title, name);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void yesLabel(String yes) {
        yesLabel = yes;
    }

    public void noLabel(String no) {
        noLabel = no;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public void updateRenderHeight() {
        setRenderHeight(1);
    }
}
