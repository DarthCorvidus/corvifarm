package com.corvidus.corvifarm.game;
class Calendar {
	private int seconds = 0;
	//private array $observers = array();
	//private ?WidgetInputObserver $inputObserver = null;
	//private TerminalText $text;
	//private Cooldown $cooldown;
	public Calendar() {
		this.seconds = 6*60;
	}
	
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	/*
	function setInputObserver(WidgetInputObserver $observer): void {
		this.inputObserver = $observer;
	}
	
	function addCalendarObserver(CalendarObserver $observer) {
		this.observers[] = $observer;
	}
	*/
	
	public String getTime() {
		int minutes = this.seconds % 60;
		int hours = Math.floorDiv(this.seconds, 60) % 24;
	return String.format("%02d:%02d", hours, minutes);
	}
	
	public int getJulian() {
		return Math.floorDiv(this.seconds, 60*24);
	}
	
	public int getYear() {
		return Math.floorDiv(this.seconds, 60*24*28*4)+1;
	}
	
	public int getDay() {
		return (Math.floorDiv(this.seconds, (60*24)) % 28)+1;
	}

	public String getSeason() {
		int season = Math.floorDiv(this.seconds, (60*24*28)) % 4;
		String[] seasons = {"Spring", "Summer", "Fall", "Winter"};
	return seasons[season];
	}
	
	
	public String getDate() {
		int day = this.getDay();
		String season = this.getSeason();
		int year = this.getYear();
		String time = this.getTime();
		return String.format("Day %d %s (%s, Year %d)", day, time, season, year);
	}
	
	public int getSeconds() {
		return this.seconds;
	}

	public void incr() {
		this.seconds++;
	}
	
	public void sleep() {
		int days = this.getJulian();
		int seconds = days * 60*24;
		if(this.seconds<=seconds + (2*60)) {
			this.seconds = (days * 60*24) + (6*60);
		} else {
			this.seconds = (days * 60*24) + (30*60);
		}
		/*
		foreach(this.observers as $value) {
			$value->onWakeup($this);
			this.text->setText(this.getDate());
		}
		*/
	}

	/*
	public function __tsError(\Exception $e, int $step): void {
		
	}

	public function __tsFinish(): void {
		
	}

	public function __tsKill(): void {
		
	}

	public function __tsLoop(): bool {
		if(this.cooldown->ready()) {
			this.incr();
			foreach(this.observers as $value) {
				$value->onSecond($this);
				this.text->setText(this.getDate());
			}
		}
	return true;
	}

	public function __tsPause(): void {
		
	}

	public function __tsResume(): void {
		
	}

	public function __tsStart(): void {
		
	}

	public function __tsTerminate(): bool {
		return true;
	}

	public function getChar(int $col, int $row): string {
		return this.text->getChar($col, $row);
	}
	
	public function getCharData(): string {
		return this.text->getCharData();
	}

	public function getHeight(): int {
		return this.text->getHeight();
	}

	public function getPosX(): int {
		return this.text->getPosX();
	}

	public function getPosY(): int {
		return this.text->getPosY();
	}

	public function getWidth(): int {
		return this.text->getWidth();
	}

	public function hasChanged(): bool {
		return this.text->hasChanged();
	}

	public function input(string $input): void {
		if(this.inputObserver === null) {
			return;
		}
		this.inputObserver->onInput($this, $input);
	}
	*/
}
