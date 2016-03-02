package FileParser;

import java.util.ArrayList;

/*
 * This class acts as our main data member, an object
 * that holds all relevant information for one single class.
 * We mainly deal in ArrayLists of CourseInfo objects.
 */

public class CourseInfo {

	private int crn;
	private String subject;
	private String courseNum;
	private String section;
	private String title;
	private int hours;
	private int curEnroll;
	private int maxEnroll;
	private String startTime;
	private String endTime;
	private String days;
	private String building;
	private int room;
	private String name;
	private boolean conflict;

	
	public CourseInfo(int crn, String subject, String courseNum, String section, String title, int hours, int curEnroll,
			int maxEnroll, String startTime, String endTime, String days, String building, int room,
			String name) {
		this.crn = crn;
		this.subject = subject;
		this.courseNum = courseNum;
		this.section = section;
		this.title = title;
		this.hours = hours;
		this.curEnroll = curEnroll;
		this.maxEnroll = maxEnroll;
		this.startTime = startTime;
		this.endTime = endTime;
		this.days = days;
		this.building = building;
		this.room = room;
		this.name = name;
		this.conflict = false;

	}
		
	public String toString() {
		return "Class [subject= " + this.subject 
                + " , title=" + this.title + " , section=" + this.section + "] " + "Days " + this.days;
	}
	
	public boolean equals(Object o){
		if(o instanceof CourseInfo){
		    CourseInfo toCompare = (CourseInfo) o;
		    if(this.crn == toCompare.crn) {
		    	return true;
		    }
		  }
		  return false;
		}
	

	
	//////// GETTERS AND SETTERS ////////////
	
	
	public int getCRN() {
		return this.crn;
	}
	
	public String getStartTime() {
		return this.startTime;
	}
	
	public String getSubject() {
		return subject;
	}

	public String getCourseNum() {
		return courseNum;
	}

	public String getSection() {
		return section;
	}

	public String getTitle() {
		return title;
	}

	public int getHours() {
		return hours;
	}

	public int getMaxEnroll() {
		return maxEnroll;
	}

	public String getDays() {
		return this.days;
	}
	
	public void setDays(String days) {
		this.days = days;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setConflict(boolean conflict) {
		this.conflict = conflict;
	}
	
	public String getEndTime() {
		return this.endTime;
	}
	
	public boolean getConflict() {
		return this.conflict;
	}
}
