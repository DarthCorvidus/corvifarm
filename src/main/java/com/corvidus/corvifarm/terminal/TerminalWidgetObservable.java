package com.corvidus.corvifarm.terminal;
public interface TerminalWidgetObservable {
	public void addInputObserver(WidgetInputObserver inputObserver);
	public void removeInputObserver(WidgetInputObserver inputObserver);
}
