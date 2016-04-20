public class Course implements Comparable{
	private String courseCode;
	private String instructor; 
	private int timeStart;
	private int timeEnd;
	private String[] preRequisites;
	private char[] days;
	
	
	public Course (String courseCode, String instructor, int timeStart, int timeEnd,
			char[] days, String[] preRequisites) {
		
		setCourseNumber(courseCode);
		setInstructor(instructor);
		setTimeStart(timeStart);
		setTimeEnd(timeEnd);
		setDays(days);
		setPreRequisites(preRequisites);
	}
	
	public Course(String courseCode, String instructor, int timeStart, int timeEnd,
			char[] days, String preRequisites) {
		setCourseNumber(courseCode);
		setInstructor(instructor);
		setTimeStart(timeStart);
		setTimeEnd(timeEnd);
		setDays(days);
		setPreRequisites(preRequisites);
	}
	
	public Course(String courseNumber, String instructor, int timeStart, int timeEnd,
			String days, String preRequisites) {
		setCourseNumber(courseNumber);
		setInstructor(instructor);
		setTimeStart(timeStart);
		setTimeEnd(timeEnd);
		setDays(days);
		setPreRequisites(preRequisites);
	}
	
	public Course (String courseCode, String instructor, int timeStart, int timeEnd,
			char[] days) {
		this(courseCode, instructor, timeStart, timeEnd, days, new String[0]);
	}
	
	public Course (String courseCode, String instructor, int timeStart, int timeEnd, String days) {
		this (courseCode, instructor, timeStart, timeEnd, days, "");
	}
	
	public Course (String courseCode, String instructor) {
		this(courseCode, instructor, 0, 0, new char[0], new String[0]);
	}
	
	public Course (String courseCode) {
		this (courseCode, "", 0, 0, new char[0], new String[0]);
	}
	
	public Course() {
		this("", "", 0, 0, new char[0], new String[0]);
	}
	
	@Override
	public String toString() {
		return courseCode + "\t\t" +  timeToString()
			+ "\t\t" + daysToString() + "\t\t" + preReqsToString();
	}
	
	public String daysToString() {
		String info = "";
		for (int i = 0; i < days.length; i++) {
			info += days[i];
		}
		
		if (info.equals("Days")) {
			return "     Days";
		}
		else
			return info;
		
	}
	public String timeToString() {	
		int start = timeStart;
		int end = timeEnd;
		
		if (start == 0 && end == 0) {
			return "  Start - End";
		} 
		else {
		if (start >= 1300) {
			start = start - 1200;
		}
		if (end >= 1300) {
			end = end - 1200;
		}
		
		String startTime = Integer.toString(start);
		String endTime = Integer.toString(end);
		
		if (startTime.length() < 4) {
			startTime = "0" + startTime;
		}
		
		if (endTime.length() < 4) {
			endTime = "0" + endTime;
		}
		
		return startTime.substring(0, 2) + ":" + startTime.substring(2) + " - " +
			endTime.substring(0, 2) + ":" + endTime.substring(2);
		}
	}
		

	public String preReqsToString() {
		String string = "";
		for (int i = 0; i < preRequisites.length; i++) {
			string += "{" + preRequisites[i] + "} ";
		}
		return string;
	}
	
	public String getCourseCode() {
		return courseCode;
	}


	public void setCourseNumber(String courseCode) {
		this.courseCode = courseCode;
	}
	
	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public int getTimeStart() {
		return timeStart;
	}


	public void setTimeStart(int timeStart) {
		this.timeStart = timeStart;
	}


	public int getTimeEnd() {
		return timeEnd;
	}


	public void setTimeEnd(int timeEnd) {
		this.timeEnd = timeEnd;
	}


	public String[] getPreRequisites() {
		return preRequisites;
	}


	public void setPreRequisites(String[] preRequisites) {
		this.preRequisites = preRequisites;
	}
	
	public void setPreRequisites(String preRequisites) {
		String[] courses = preRequisites.split("/");
		
		this.preRequisites = courses;
	
	}

	public char[] getDays() {
		return days;
	}

	public void setDays(char[] days) {
		this.days = days;
	}
	
	public void setDays(String days) {
		char[] day = new char[days.length()];
		for (int i = 0; i < days.length(); i++) {
			day[i] = days.charAt(i);
		}
		this.days = day;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
