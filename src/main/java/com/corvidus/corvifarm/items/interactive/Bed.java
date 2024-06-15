package com.corvidus.corvifarm.items.interactive;

import com.corvidus.corvifarm.game.Game;
import com.corvidus.corvifarm.items.Interactive;
import com.corvidus.corvifarm.items.ItemAbstract;
import com.corvidus.corvifarm.terminal.UserInterface;
import com.corvidus.corvifarm.terminal.yesno.WidgetYesNo;
import com.corvidus.corvifarm.terminal.yesno.YesNoObserver;

public class Bed extends ItemAbstract implements Interactive, YesNoObserver {
	private Game game;
	@Override
	public void interact(Game game) {
		this.game = game;
		WidgetYesNo yesNo = new WidgetYesNo(10, 5, 20, 4, "Sleep? (y/N)");
		yesNo.addYesNoObserver(this);
		this.game.calendar.pause();
		this.game.userInterface.setFocus(yesNo);

		//game.calendar.sleep();
	}

	@Override
	public int getBaseDemand() {
		return 0;
	}

	@Override
	public String getName() {
		return "Bed";
	}

	@Override
	public void onYes(WidgetYesNo widgetYesNo) {
		this.game.calendar.sleep();
		this.game.userInterface.removeWidget(widgetYesNo);
		this.game.calendar.resume();
	}

	@Override
	public void onNo(WidgetYesNo widgetYesNo) {
		this.game.userInterface.removeWidget(widgetYesNo);
		this.game.calendar.resume();
	}

	@Override
	public void onInvalid(WidgetYesNo widgetYesNo) {
		this.game.userInterface.removeWidget(widgetYesNo);
		this.game.calendar.resume();
	}
}
