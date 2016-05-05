public class Course implements Comparable{
	private String courseCode;
	private int crn;
	private int creditHours;
	private MeetTimes[] courseTimes;
	private MeetTimes finalTime;
	private String[] preRequisites;
	
	
	
	public Course (String courseCode, String instructor, MeetTimes[] courseTimes, MeetTimes finalTime, 
			String[] preRequisites) {
		
		setCourseCode(courseCode);
		setCourseTimes(courseTimes);
		setFinalTime(finalTime);
		setPreRequisites(preRequisites);
	}
	
	public Course (int crn, String courseCode, int creditHours, MeetTimes[] courseTimes, MeetTimes finalTime) {
		setCrn(crn);
		setCourseCode(courseCode);
		setCreditHours(creditHours);
		setCourseTimes(courseTimes);
		setFinalTime(finalTime);
	}
	
	
	public Course(String courseNumber, MeetTimes[]courseTimes, String preRequisites) {
		setCourseCode(courseNumber);
		setCourseTimes(courseTimes);
		setPreRequisites(preRequisites);
	}
	
	public Course (String courseCode, MeetTimes[] courseTimes, MeetTimes finalTime) {
		this(courseCode, "", courseTimes, finalTime, new String[0]); 
	}

	public Course (String courseCode, MeetTimes[] courseTimes) {
		this(courseCode, "", courseTimes, new MeetTimes(), new String[0]); 
	}
	
	public Course (String courseCode) {
		this (courseCode, "", new MeetTimes[0], new MeetTimes(), new String[0]);
	}
	
	public Course() {
		this("", "", new MeetTimes[0], new MeetTimes(), new String[0]);
	}
	
	@Override
	public String toString() {
		return courseCode + " " + courseTimesToString();
	}

	
	public String importantInfo() {
		if (courseTimes == null) {
			return courseCode;
		}
		else
			return crn + "  " + courseCode + "  " + creditHours + "  " + courseTimesToString();
	}
	
	public String courseTimesToString() {
		String ct = "";
		
		if (courseTimes.length == 0)	 {
			ct = "";
		}
		if (courseTimes.length == 1) {
			if (courseTimes[0].getDay() == 'N' || courseTimes[0].getStartTime() == 9999) {
				ct = "";
			}
			else ct = courseTimes[0].toString();
		}
		else if (courseTimes.length == 2) {
			if (courseTimes[0].isSameTimeAs(courseTimes[1])) {
				ct = String.valueOf(courseTimes[0].getDay()) + String.valueOf(courseTimes[1].getDay()) +
						" " + toStandardTime(courseTimes[0].getStartTime()) + "-" + 
						toStandardTime(courseTimes[1].getEndTime());
			}
			else
				ct = String.valueOf(courseTimes[0].getDay()) + " " + toStandardTime(courseTimes[0].getStartTime()) + "-" +
						toStandardTime(courseTimes[0].getEndTime()) + ", " + String.valueOf(courseTimes[1].getDay()) + " " + 
						toStandardTime(courseTimes[1].getStartTime()) + "-" + 
						toStandardTime(courseTimes[1].getEndTime());
		}
		
		else if (courseTimes.length == 3) {
			if ((courseTimes[0].isSameTimeAs(courseTimes[1]) && courseTimes[1].isSameTimeAs(courseTimes[2]))) {
				ct = String.valueOf(courseTimes[0].getDay()) + String.valueOf(courseTimes[1].getDay()) + String.valueOf(courseTimes[2].getDay()) + " " + 
						toStandardTime(courseTimes[0].getStartTime()) + "-" + toStandardTime(courseTimes[0].getEndTime());
			}
			if ((courseTimes[0].isSameTimeAs(courseTimes[1]) && (courseTimes[1].isSameTimeAs(courseTimes[2]) == false))) {
				ct = String.valueOf(courseTimes[0].getDay()) + String.valueOf(courseTimes[1].getDay()) + " " + toStandardTime(courseTimes[0].getStartTime()) + "-" +
						toStandardTime(courseTimes[0].getEndTime()) + ", " + courseTimes[2].getDay() + " " + 
						toStandardTime(courseTimes[2].getStartTime()) + "-" + toStandardTime(courseTimes[2].getEndTime());
			}
			if ((courseTimes[0].isSameTimeAs(courseTimes[1]) == false && courseTimes[1].isSameTimeAs(courseTimes[2]))) {
				ct = String.valueOf(courseTimes[1].getDay()) + String.valueOf(courseTimes[2].getDay())	+ " " + toStandardTime(courseTimes[1].getStartTime()) + "-" +
						toStandardTime(courseTimes[1].getEndTime()) + ", " + courseTimes[0].getDay() + " " + 
						toStandardTime(courseTimes[0].getStartTime()) + "-" + toStandardTime(courseTimes[0].getEndTime());
			}
			if ((courseTimes[0].isSameTimeAs(courseTimes[1]) == false && courseTimes[0].isSameTimeAs(courseTimes[2]))) {
				ct = String.valueOf(courseTimes[0].getDay()) + String.valueOf(courseTimes[2].getDay())	+ " " + toStandardTime(courseTimes[0].getStartTime()) + "-" +
						toStandardTime(courseTimes[0].getEndTime()) + ", " + courseTimes[1].getDay() + " " + 
						toStandardTime(courseTimes[1].getStartTime()) + "-" + toStandardTime(courseTimes[1].getEndTime());
			}
		}
			
		else {
				for (MeetTimes m : courseTimes) {
					ct += m.toString();
				}
			}
		
		return ct;
	}
	
	public boolean conflictsWith (Course c) {
		MeetTimes[] cTimes = c.getCourseTimes();
		boolean flag = false;;
		
		for (int i = 0; i < cTimes.length; i++) {
			for (int j = 0; j < courseTimes.length; j++) {
				if ( (cTimes[i].getDay() == courseTimes[j].getDay()) &&  ( 
						(cTimes[i].getStartTime() >= courseTimes[j].getStartTime() &&
						cTimes[i].getStartTime() <= courseTimes[j].getEndTime()) || 
						(cTimes[i].getEndTime() >= courseTimes[j].getEndTime() && 
						cTimes[i].getEndTime() <= courseTimes[j].getEndTime()))) {
					flag = true;
					break;
				}
				
			}
			
		}
		return flag;
		
	}
	
	public String toStandardTime (int n ) {
		String time = "";
		
		if (n > 1259) {
			n = n - 1200;
		}
		
		time = Integer.toString(n);
		
		if (time.length() < 4) {
			time = "0" + time;
		}
		
		return time.substring(0, 2) + ":" + time.substring(2);
		
		
	}

	public MeetTimes getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(MeetTimes finalTime) {
		this.finalTime = finalTime;
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


	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public MeetTimes[] getCourseTimes() {
		return courseTimes;
	}

	public void setCourseTimes(MeetTimes[] courseTimes) {
		this.courseTimes = courseTimes;
	}

	public String[] getPreRequisites() {
		return preRequisites;
	}

	public int getCrn() {
		return crn;
	}

	public void setCrn(int crn) {
		this.crn = crn;
	}

	public int getCreditHours() {
		return creditHours;
	}

	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}
	
	public void setPreRequisites(String[] preRequisites) {
		this.preRequisites = preRequisites;
	}
	
	public void setPreRequisites(String preRequisites) {
		String[] courses = preRequisites.split("/");
		
		this.preRequisites = courses;
	
	}
	
	@Override
	public int compareTo(Object o) {
		if (courseCode.equals(((Course)o).getCourseCode())) {
			return 0;
		}
		else
			return -1;
	}
	
}
