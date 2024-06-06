package com.corvidus.corvifarm.ui;
import com.corvidus.corvifarm.game.Calendar;
import com.corvidus.corvifarm.game.Game;
import com.corvidus.corvifarm.terminal.UserInterface;
import com.corvidus.corvifarm.terminal.yesno.WidgetYesNo;
import com.corvidus.corvifarm.terminal.yesno.YesNoObserver;
public class YesNoQuit  implements YesNoObserver {
	private Calendar calendar;
	private UserInterface userInterface;
	private final WidgetYesNo yesNo;
	public YesNoQuit(Calendar calendar, UserInterface userInterface){
		this.yesNo = new WidgetYesNo(10, 5, 20, 4, "Quit (y/N)");
		this.userInterface = userInterface;
		this.calendar = calendar;
		this.calendar.pause();
		
		this.userInterface.refresh();

		this.calendar = calendar;
		
		this.userInterface = userInterface;
		this.userInterface.setFocus(this.yesNo);
	}
	
	public void run() {
		this.calendar.pause();
		this.yesNo.addYesNoObserver(this);
		this.userInterface.setFocus(this.yesNo);
	}
	
	@Override
	public void onYes(WidgetYesNo widgetYesNo) {
		System.exit(0);
	}

	@Override
	public void onNo(WidgetYesNo widgetYesNo) {
		this.calendar.resume();
		this.userInterface.removeWidget(widgetYesNo);
	}

	@Override
	public void onInvalid(WidgetYesNo widgetYesNo) {
		this.calendar.resume();
		this.userInterface.removeWidget(widgetYesNo);
	}
}
