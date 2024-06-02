package com.corvidus.corvifarm.terminal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class WidgetTextLinesTest {
	@Test
	public void testConstruct() {
		WidgetTextLines lines = new WidgetTextLines(0, 0, 10, 3);
		String expect = " ".repeat(10*3);
		Assertions.assertEquals(expect, lines.getString());
	}

	public void testAddLines() {
		WidgetTextLines lines = new WidgetTextLines(0, 0, 9, 3);
		lines.addLine(0, "Cat");
		lines.addLine(1, "Hippopotamus");
		lines.addLine(2, "Alligator");
		String expect = "";
		expect += "Cat      ";
		expect += "Hippopota";
		expect += "Alligator";
		Assertions.assertEquals(expect, lines.getString());
	}
}
