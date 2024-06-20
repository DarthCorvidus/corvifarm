package com.corvidus.corvifarm.game;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.fail;
public class CalendarTest {
	@Test
	public void testConstruct() {
		Calendar cal = Calendar.fromScratch();
		Assertions.assertEquals(6*60, cal.getSeconds());
	}

	@Test
	public void testGetTime() {
		Calendar cal = Calendar.fromScratch();
		Assertions.assertEquals("06:00", cal.getTime());
	}
	
	@Test
	public void testGetDay() {
		Calendar cal = Calendar.fromScratch();
		Assertions.assertEquals(1, cal.getDay());
	}
	
	@Test
	public void testGetSeason() {
		Calendar cal = Calendar.fromScratch();
		Assertions.assertEquals("Spring", cal.getSeason());
	}

	@Test
	public void testGetYear() {
		Calendar cal = Calendar.fromScratch();
		Assertions.assertSame(1, cal.getYear());
	}
	
	@Test
	public void testGetDate() {
		Calendar cal = Calendar.fromScratch();
		Assertions.assertEquals("Day 1 06:00 (Spring, Year 1)", cal.getDate());
	}

	@Test
	public void testSleep() {
		Calendar cal = Calendar.fromScratch();
		cal.sleep();
		Assertions.assertEquals("Day 2 06:00 (Spring, Year 1)", cal.getDate());
	}

	@Test
	public void testCalc01() {
		Calendar cal= Calendar.fromScratch();
		for(int i=0;i<10;i++) {
			cal.sleep();
		}
		Assertions.assertEquals("Day 11 06:00 (Spring, Year 1)", cal.getDate());
	}

	@Test
	public void testCalc02() {
		Calendar cal= Calendar.fromScratch();
		for(int i=0;i < 30;i++) {
			cal.sleep();
		}
		Assertions.assertEquals("Day 3 06:00 (Summer, Year 1)", cal.getDate());
	}

	@Test
	public void testCalc03() {
		Calendar cal= Calendar.fromScratch();
		for(int i=0;i < 60;i++) {
			cal.sleep();
		}
		Assertions.assertEquals("Day 5 06:00 (Fall, Year 1)", cal.getDate());
	}

	@Test
	public void testCalc04() {
		Calendar cal= Calendar.fromScratch();
		for(int i=0;i < 500;i++) {
			cal.sleep();
		}
		Assertions.assertEquals("Day 25 06:00 (Summer, Year 5)", cal.getDate());
	}
	
	@Test
	public void testToBinary() {
		byte[] expected = new byte[4];
		expected[2] = 13;
		expected[3] = 87;
		Calendar cal = Calendar.fromScratch();
		cal.setSeconds(3415);
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		try {
			cal.toBinary(bo);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		byte[] bytes = bo.toByteArray();
		Assertions.assertArrayEquals(expected, bytes);
	}
	
	@Test
	public void testFromBinary() {
		byte[] expected = new byte[4];
		expected[2] = 13;
		expected[3] = 87;
		ByteArrayInputStream bi = new ByteArrayInputStream(expected);
		try {
			Calendar cal = Calendar.fromBinary(bi);
			Assertions.assertEquals(3415, cal.getSeconds());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
}
