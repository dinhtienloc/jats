package vn.locdt.jats.bundle.question.listener;

import vn.locdt.jats.bundle.question.event.ChooseSelectorEvent;
import vn.locdt.jats.bundle.question.event.ChangeSelectorEvent;

public interface ChoiceListener extends Listener{
    void onChanged(ChangeSelectorEvent e);
    void onChosen(ChooseSelectorEvent e);
}
