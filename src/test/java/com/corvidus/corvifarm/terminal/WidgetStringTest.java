package com.corvidus.corvifarm.terminal;

import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WidgetStringTest {
	public WidgetStringTest() {
	}
	
	@BeforeAll
	public static void setUpClass() {
	}
	
	@AfterAll
	public static void tearDownClass() {
	}
	
	@BeforeEach
	public void setUp() {
	}
	
	@AfterEach
	public void tearDown() {
	}

	@Test
	public void testSetStringShorter() {
		WidgetString instance = new WidgetString(0, 0, 9);
		instance.setString("Cat");
		TextImage ti = new BasicTextImage(9, 1);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "Cat");
		Assertions.assertEquals(ti.toString(), instance.getTextImage().toString());
	}
	
	@Test
	public void testSetStringEqual() {
		WidgetString instance = new WidgetString(0, 0, 9);
		instance.setString("Alligator");
		TextImage ti = new BasicTextImage(9, 1);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "Alligator");
		Assertions.assertEquals(ti.toString(), instance.getTextImage().toString());
	}

	@Test
	public void testSetStringLarger() {
		WidgetString instance = new WidgetString(0, 0, 9);
		instance.setString("Hippopotamus");
		TextImage ti = new BasicTextImage(9, 1);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "Hippopota");
		Assertions.assertEquals(ti.toString(), instance.getTextImage().toString());
	}

	@Test
	public void testSetStringClear() {
		WidgetString instance = new WidgetString(0, 0, 9);
		instance.setString("Hippopotamus");
		instance.setString("Mouse");
		TextImage ti = new BasicTextImage(9, 1);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "Mouse");
		Assertions.assertEquals(ti.toString(), instance.getTextImage().toString());
	}

}
