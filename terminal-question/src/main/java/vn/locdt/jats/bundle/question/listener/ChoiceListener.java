package vn.locdt.jats.core.question.listener;

import vn.locdt.jats.core.question.event.ChooseSelectorEvent;
import vn.locdt.jats.core.question.event.ChangeSelectorEvent;

public interface ChoiceListener extends Listener{
    void onChanged(ChangeSelectorEvent e);
    void onChosen(ChooseSelectorEvent e);
}
