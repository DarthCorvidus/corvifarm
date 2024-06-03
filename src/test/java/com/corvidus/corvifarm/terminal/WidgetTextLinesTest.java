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

	public void testAddLines() {
		WidgetTextLines lines = new WidgetTextLines(0, 0, 9, 3);
		lines.addLine(0, "Cat");
		lines.addLine(1, "Hippopotamus");
		lines.addLine(2, "Alligator");
		TextImage ti = new BasicTextImage(9, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "Cat");
		tg.putString(0, 1, "Hippopota");
		tg.putString(0, 2, "Alligator");
		Assertions.assertEquals(ti.toString(), lines.getTextImage().toString());
	}

}
