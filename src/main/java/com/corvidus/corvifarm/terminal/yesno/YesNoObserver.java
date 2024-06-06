package com.corvidus.corvifarm.terminal.yesno;
public interface YesNoObserver {
	public void onYes(WidgetYesNo widgetYesNo);
	public void onNo(WidgetYesNo widgetYesNo);
	public void onInvalid(WidgetYesNo widgetYesNo);
}
