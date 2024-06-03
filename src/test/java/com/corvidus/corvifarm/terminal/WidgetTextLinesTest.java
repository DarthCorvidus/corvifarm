package com.corvidus.corvifarm.terminal;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class WidgetTextLinesTest {
	@Test
	public void testConstruct() {
		WidgetTextLines lines = new WidgetTextLines(0, 0, 10, 3);
		TextImage ti = new BasicTextImage(10, 3);
		Assertions.assertEquals(ti.toString(), lines.getTextImage().toString());
	}

	public void testSetLines() {
		WidgetTextLines lines = new WidgetTextLines(0, 0, 9, 3);
		lines.setLine(0, "Cat");
		lines.setLine(1, "Hippopotamus");
		lines.setLine(2, "Alligator");
		TextImage ti = new BasicTextImage(9, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "Cat");
		tg.putString(0, 1, "Hippopota");
		tg.putString(0, 2, "Alligator");
		Assertions.assertEquals(ti.toString(), lines.getTextImage().toString());
	}

	public void testReplaceLine() {
		WidgetTextLines lines = new WidgetTextLines(0, 0, 9, 3);
		lines.setLine(0, "Cat");
		lines.setLine(1, "Hippopotamus");
		lines.setLine(2, "Alligator");
		
		//Don't want to get a "Mousepotamus" here, although it surely would look
		//cute.
		lines.setLine(1, "Mouse");
		
		TextImage ti = new BasicTextImage(9, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "Cat");
		tg.putString(0, 1, "Mouse");
		tg.putString(0, 2, "Alligator");
		Assertions.assertEquals(ti.toString(), lines.getTextImage().toString());
	}

}
