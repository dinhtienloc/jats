package vn.locdt.jats.bundle.question.listener;

import vn.locdt.jats.bundle.question.event.ChangeSelectorEvent;
import vn.locdt.jats.bundle.question.event.ChooseSelectorEvent;

public interface ChoiceListener extends Listener {
    void onChanged(ChangeSelectorEvent e);

    void onChosen(ChooseSelectorEvent e);
}
