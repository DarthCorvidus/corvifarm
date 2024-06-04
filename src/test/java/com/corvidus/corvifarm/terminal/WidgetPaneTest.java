package com.corvidus.corvifarm.terminal;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class WidgetPaneTest {
	@Test
	public void testConstruct() {
		WidgetPane pane = new WidgetPane(0, 0, 40, 12);
		TextImage ti = new BasicTextImage(40, 12);
		Assertions.assertEquals(ti.toString(), pane.getTextImage().toString());
	}
	
	@Test
	public void testAddWidget() {
		WidgetPane pane = new WidgetPane(0, 0, 40, 12);
		pane.addWidget(new WidgetString(0, 0, 5, "Cat"));
		pane.addWidget(new WidgetString(10, 5, 5, "Mouse"));
		pane.addWidget(new WidgetString(20, 11, 5, "Dog"));
		TextImage ti = new BasicTextImage(40, 12);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "Cat");
		tg.putString(10, 5, "Mouse");
		tg.putString(20, 11, "Dog");
		Assertions.assertEquals(ti.toString(), pane.getTextImage().toString());
	}
	
	public void testChangeWidget() {
		WidgetPane pane = new WidgetPane(0, 0, 40, 12);
		WidgetString ws = new WidgetString(10, 5, 5, "Mouse");
		pane.addWidget(new WidgetString(0, 0, 5, "Cat"));
		pane.addWidget(ws);
		pane.addWidget(new WidgetString(20, 11, 5, "Dog"));
		TextImage ti = new BasicTextImage(40, 12);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "Cat");
		tg.putString(10, 5, "Mouse");
		tg.putString(20, 11, "Dog");
		Assertions.assertEquals(ti.toString(), pane.getTextImage().toString());

		ws.setString("Squid");
		tg.putString(10, 5, "Squid");
		Assertions.assertEquals(ti.toString(), pane.getTextImage().toString());
	}

}
