package com.corvidus.corvifarm.game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class CalendarTest {
	@Test
	public void testConstruct() {
		Calendar cal = new Calendar();
		Assertions.assertSame(0, cal.getSeconds());
	}
	/*
	function testGetTime() {
		$cal = new Calendar();
		$this->assertSame("06:00", $cal->getTime());
	}
	
	function testGetDay() {
		$cal = new Calendar();
		$this->assertSame(1, $cal->getDay());
	}

	function testGetSeason() {
		$cal = new Calendar();
		$this->assertSame("Spring", $cal->getSeason());
	}

	function testGetYear() {
		$cal = new Calendar();
		$this->assertSame(1, $cal->getYear());
	}
	
	function testGetDate() {
		$cal = new Calendar();
		$this->assertSame("Day 1 06:00 (Spring, Year 1)", $cal->getDate());
	}
	
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
