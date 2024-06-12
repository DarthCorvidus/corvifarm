/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.corvidus.corvifarm.terminal;

import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author hm
 */
public class WidgetLogTest {
	
	public WidgetLogTest() {
	}

	/**
	 * Test of addMessage method, of class WidgetLog.
	 */
	@Test
	public void testEmpty() {
		WidgetLog log = new WidgetLog(0, 0, 80, 4);
		TextImage ti = new BasicTextImage(80, 4);
		TextGraphics tg = ti.newTextGraphics();
		assertEquals(log.getTextImage().toString(), ti.toString());
	}
	
	@Test
	public void testAddLineFirst() {
		WidgetLog log = new WidgetLog(0, 0, 80, 4);
		log.addMessage("The cat is on the mat.");
		TextImage ti = new BasicTextImage(80, 4);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 3, "The cat is on the mat.");
		assertEquals(log.getTextImage().toString(), ti.toString());
	}

	@Test
	public void testAddLineSecond() {
		WidgetLog log = new WidgetLog(0, 0, 80, 4);
		log.addMessage("The cat is on the mat.");
		log.addMessage("The mouse wears a hat.");
		TextImage ti = new BasicTextImage(80, 4);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 2, "The cat is on the mat.");
		tg.putString(0, 3, "The mouse wears a hat.");
		assertEquals(log.getTextImage().toString(), ti.toString());
	}
	
	@Test
	public void testAddLineLong() {
		WidgetLog log = new WidgetLog(0, 0, 80, 4);
		log.addMessage("The cat is on the mat.");
		log.addMessage("The mouse wears a hat.");
		log.addMessage("The cat, which was a very happy and well sized cat, was thinking to itself one day, 'today I shall be on the mat'.");
		TextImage ti = new BasicTextImage(80, 4);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "The cat is on the mat.");
		tg.putString(0, 1, "The mouse wears a hat.");
		tg.putString(0, 2, "The cat, which was a very happy and well sized cat, was thinking to itself one");
		tg.putString(0, 3, "day, 'today I shall be on the mat'.");
		assertEquals(log.getTextImage().toString(), ti.toString());
	}
	
}
