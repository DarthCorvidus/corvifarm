package com.corvidus.corvifarm.game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class CalendarTest {
	@Test
	public void testConstruct() {
		Calendar cal = Calendar.fromScratch();
		Assertions.assertEquals(6*60, cal.getSeconds());
	}

	public void testGetTime() {
		Calendar cal = Calendar.fromScratch();
		Assertions.assertEquals("06:00", cal.getTime());
	}
	
	public void testGetDay() {
		Calendar cal = Calendar.fromScratch();
		Assertions.assertEquals(1, cal.getDay());
	}
	
	public void testGetSeason() {
		Calendar cal = Calendar.fromScratch();
		Assertions.assertEquals("Spring", cal.getSeason());
	}

	public void testGetYear() {
		Calendar cal = Calendar.fromScratch();
		Assertions.assertSame(1, cal.getYear());
	}
	
	public void testGetDate() {
		Calendar cal = Calendar.fromScratch();
		Assertions.assertEquals("Day 1 06:00 (Spring, Year 1)", cal.getDate());
	}

	public void testSleep() {
		Calendar cal = Calendar.fromScratch();
		cal.sleep();
		Assertions.assertEquals("Day 2 06:00 (Spring, Year 1)", cal.getDate());
	}

	public void testCalc01() {
		Calendar cal= Calendar.fromScratch();
		for(int i=0;i<10;i++) {
			cal.sleep();
		}
		Assertions.assertEquals("Day 11 06:00 (Spring, Year 1)", cal.getDate());
	}

	public void testCalc02() {
		Calendar cal= Calendar.fromScratch();
		for(int i=0;i < 30;i++) {
			cal.sleep();
		}
		Assertions.assertEquals("Day 3 06:00 (Summer, Year 1)", cal.getDate());
	}

	public void testCalc03() {
		Calendar cal= Calendar.fromScratch();
		for(int i=0;i < 60;i++) {
			cal.sleep();
		}
		Assertions.assertEquals("Day 5 06:00 (Fall, Year 1)", cal.getDate());
	}

	public void testCalc04() {
		Calendar cal= Calendar.fromScratch();
		for(int i=0;i < 500;i++) {
			cal.sleep();
		}
		Assertions.assertEquals("Day 25 06:00 (Summer, Year 5)", cal.getDate());
	}
}
