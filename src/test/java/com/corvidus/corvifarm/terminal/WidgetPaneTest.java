package com.corvidus.corvifarm.terminal;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class WidgetPaneTest {
	@Test
	public void testConstruct() {
		WidgetTextLines lines = new WidgetTextLines(0, 0, 40, 12);
		TextImage ti = new BasicTextImage(40, 12);
		Assertions.assertEquals(ti.toString(), lines.getTextImage().toString());
	}
}
