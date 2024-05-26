package com.corvidus.corvifarm.game;
class Calendar {
	private int seconds = 0;
	//private array $observers = array();
	//private ?WidgetInputObserver $inputObserver = null;
	//private TerminalText $text;
	//private Cooldown $cooldown;
	public Calendar() {
		this.seconds = 0;
	}
	/*
	function setInputObserver(WidgetInputObserver $observer): void {
		$this->inputObserver = $observer;
	}
	
	function addCalendarObserver(CalendarObserver $observer) {
		$this->observers[] = $observer;
	}
	
	function getTime() {
		$minutes = ($this->seconds % 60);
		$hours = floor($this->seconds/60) % 24;
	return sprintf("%02d:%02d", $hours, $minutes);
	}
	
	function getJulian() {
		return floor($this->seconds/(60*24));
	}
	
	function getYear(): int {
		return (floor($this->seconds/(60*24*28*4)))+1;
	}
	
	function getDay(): int {
		return (floor($this->seconds/(60*24)) % 28)+1;
	}
	
	function getSeason(): string {
		$season = floor($this->seconds/(60*24*28)) % 4;
		$seasons = array("Spring", "Summer", "Fall", "Winter");
	return $seasons[$season];
	}
	
	function getDate(): string {
		$minutes = ($this->seconds % 60);
		$hours = floor($this->seconds/60) % 24;
		$days = floor($this->seconds/(60*24)) % 28;
		$season = floor($this->seconds/(60*24*28)) % 4;
		$year = floor($this->seconds/(60*24*28*4));
		$seasons = array("Spring", "Summer", "Fall", "Winter");
		return sprintf("Day %d %02d:%02d (%s, Year %d)", $days+1, $hours, $minutes, $seasons[$season], $year+1);
	}
	*/
	public int getSeconds() {
		return this.seconds;
	}
	/*
	function incr() {
		$this->seconds++;
	}
	
	function sleep() {
		$days = $this->getJulian();
		$seconds = $days * 60*24;
		if($this->seconds<=$seconds + (2*60)) {
			$this->seconds = ($days * 60*24) + (6*60);
		} else {
			$this->seconds = ($days * 60*24) + (30*60);
		}
		foreach($this->observers as $value) {
			$value->onWakeup($this);
			$this->text->setText($this->getDate());
		}
	}

	public function __tsError(\Exception $e, int $step): void {
		
	}

	public function __tsFinish(): void {
		
	}

	public function __tsKill(): void {
		
	}

	public function __tsLoop(): bool {
		if($this->cooldown->ready()) {
			$this->incr();
			foreach($this->observers as $value) {
				$value->onSecond($this);
				$this->text->setText($this->getDate());
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
		return $this->text->getChar($col, $row);
	}
	
	public function getCharData(): string {
		return $this->text->getCharData();
	}

	public function getHeight(): int {
		return $this->text->getHeight();
	}

	public function getPosX(): int {
		return $this->text->getPosX();
	}

	public function getPosY(): int {
		return $this->text->getPosY();
	}

	public function getWidth(): int {
		return $this->text->getWidth();
	}

	public function hasChanged(): bool {
		return $this->text->hasChanged();
	}

	public function input(string $input): void {
		if($this->inputObserver === null) {
			return;
		}
		$this->inputObserver->onInput($this, $input);
	}
	*/
}
