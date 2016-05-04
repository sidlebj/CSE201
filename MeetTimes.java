
public class MeetTimes implements Comparable {
	private int startTime;
	private int endTime;
	private char day;
	
	public MeetTimes (char day, int startTime, int endTime) {
		setDay(day);
		setStartTime(startTime);
		setEndTime(endTime);
	}
	
	public MeetTimes (String day, int startTime, int endTime) {
		setDay(day);
		setStartTime(startTime);
		setEndTime(endTime);
	}
	
	public MeetTimes() {
		
	}
	

	@Override
	public String toString() {
		return day + " " + startTime + "-" + endTime;
	}



	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public char getDay() {
		return day;
	}
	
	public void setDay (String day) {
		this.day = day.charAt(0);
	}
	
	public void setDay (char day) {
		this.day = day;
	}
	
	public boolean isSameTimeAs (MeetTimes mt)  {
		if (this.startTime == mt.getStartTime() && this.endTime == mt.getEndTime()) {
			return true;
		}
		else
			return false;
	}

	
	@Override
	public int compareTo(Object o) {
		if (this.startTime == ((MeetTimes)o).getStartTime() &&
				this.endTime == ((MeetTimes)o).getEndTime()) {
			return 0;
		}
		else 
			return -1;
		
	}
	
	

}
