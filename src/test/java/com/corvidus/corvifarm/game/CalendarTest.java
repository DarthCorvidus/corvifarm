package com.corvidus.corvifarm.game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class CalendarTest {
	@Test
	public void testConstruct() {
		Calendar cal = new Calendar();
		Assertions.assertEquals(6*60, cal.getSeconds());
	}

	public void testGetTime() {
		Calendar cal = new Calendar();
		Assertions.assertEquals("06:00", cal.getTime());
	}
	
	public void testGetDay() {
		Calendar cal = new Calendar();
		Assertions.assertEquals(1, cal.getDay());
	}
	
	public void testGetSeason() {
		Calendar cal = new Calendar();
		Assertions.assertEquals("Spring", cal.getSeason());
	}

	public void testGetYear() {
		Calendar cal = new Calendar();
		Assertions.assertSame(1, cal.getYear());
	}
	
	public void testGetDate() {
		Calendar cal = new Calendar();
		Assertions.assertEquals("Day 1 06:00 (Spring, Year 1)", cal.getDate());
	}
	/*
	function testSleep() {
		$cal = new Calendar();
		$cal->sleep();
		$this->assertSame("Day 2 06:00 (Spring, Year 1)", $cal->getDate());
	}
	
	function testCalc01() {
		$cal = new Calendar();
		for($i=0;$i<10;$i++) {
			$cal->sleep();
		}
		$this->assertSame("Day 11 06:00 (Spring, Year 1)", $cal->getDate());
	}

	function testCalc02() {
		$cal = new Calendar();
		for($i=0;$i<30;$i++) {
			$cal->sleep();
		}
		$this->assertSame("Day 3 06:00 (Summer, Year 1)", $cal->getDate());
	}

	function testCalc03() {
		$cal = new Calendar();
		for($i=0;$i<60;$i++) {
			$cal->sleep();
		}
		$this->assertSame("Day 5 06:00 (Fall, Year 1)", $cal->getDate());
	}

	function testCalc04() {
		$cal = new Calendar();
		for($i=0;$i<500;$i++) {
			$cal->sleep();
		}
		$this->assertSame("Day 25 06:00 (Summer, Year 5)", $cal->getDate());
	}
	*/
}
