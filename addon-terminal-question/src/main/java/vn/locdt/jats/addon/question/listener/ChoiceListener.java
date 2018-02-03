package vn.locdt.jats.addon.question.listener;

import vn.locdt.jats.addon.question.event.ChangeSelectorEvent;
import vn.locdt.jats.addon.question.event.ChooseSelectorEvent;

public interface ChoiceListener extends Listener{
    void onChanged(ChangeSelectorEvent e);
    void onChosen(ChooseSelectorEvent e);
}
