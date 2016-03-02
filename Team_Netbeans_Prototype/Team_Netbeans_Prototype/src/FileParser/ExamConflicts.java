package FileParser;

import java.util.ArrayList;

//test

public class ExamConflicts {

	public ArrayList<CourseInfo> conflicts;

	// Note: All of the following methods filter the complete
	// list of courses into their respective exam days and times.
	// They are aptly titled.
	// The methods then call the conflictFinder helper method.
	
	public ArrayList<CourseInfo> tues800(ArrayList<CourseInfo> fullList) {
		conflicts = new ArrayList<>();
		for(int i = 0; i < fullList.size(); i++) {
			if(fullList.get(i).getStartTime().equals("800") || 
					fullList.get(i).getStartTime().equals("830") ) {
				if((fullList.get(i).getDays().contains("T") ||
						fullList.get(i).getDays().contains("R")) && 
						(!fullList.get(i).getDays().contains("M") ||
								!fullList.get(i).getDays().contains("W") ||
								!fullList.get(i).getDays().contains("F"))) {
					conflicts.add(fullList.get(i));
				}
			}
		}
		conflicts = this.conflictFinder(conflicts);
		return conflicts;
	}

	public ArrayList<CourseInfo> wed800(ArrayList<CourseInfo> fullList) {
		conflicts = new ArrayList<>();
		for(int i = 0; i < fullList.size(); i++) {
			if(fullList.get(i).getStartTime().equals("800") || 
					fullList.get(i).getStartTime().equals("830") ) {
				if(fullList.get(i).getDays().contains("M") ||
						fullList.get(i).getDays().contains("W") ||
						fullList.get(i).getDays().contains("F"))
				{
					conflicts.add(fullList.get(i));
				}
			}
		}
		conflicts = this.conflictFinder(conflicts);
		return conflicts;

	}

	public ArrayList<CourseInfo> thurs800(ArrayList<CourseInfo> fullList) {
		conflicts = new ArrayList<>();
		for(int i = 0; i < fullList.size(); i++) {
			if(fullList.get(i).getStartTime().equals("1000")) {
				if((fullList.get(i).getDays().contains("T") ||
						fullList.get(i).getDays().contains("R")) &&
						(!fullList.get(i).getDays().contains("M") ||
								!fullList.get(i).getDays().contains("W") ||
								!fullList.get(i).getDays().contains("F")))
				{
					conflicts.add(fullList.get(i));
				}
			}
		}
		conflicts = this.conflictFinder(conflicts);
		
		return conflicts;

	}

	public ArrayList<CourseInfo> wed1015(ArrayList<CourseInfo> fullList) {
		conflicts = new ArrayList<>();
		for(int i = 0; i < fullList.size(); i++) {
			if(fullList.get(i).getStartTime().equals("1130")) {
				if(fullList.get(i).getDays().contains("M") ||
						fullList.get(i).getDays().contains("W") ||
						fullList.get(i).getDays().contains("F"))
				{
					conflicts.add(fullList.get(i));
				}
			}
		}
		conflicts = this.conflictFinder(conflicts);
		return conflicts;

	}

	public ArrayList<CourseInfo> fri1015(ArrayList<CourseInfo> fullList) {
		conflicts = new ArrayList<>();
		for(int i = 0; i < fullList.size(); i++) {
			if(fullList.get(i).getStartTime().equals("1000")) {
				if(fullList.get(i).getDays().contains("M") ||
						fullList.get(i).getDays().contains("W") ||
						fullList.get(i).getDays().contains("F"))
				{
					conflicts.add(fullList.get(i));
				}
			}
		}
		conflicts = this.conflictFinder(conflicts);
		return conflicts;

	}

	public ArrayList<CourseInfo> mon1245(ArrayList<CourseInfo> fullList) {
		conflicts = new ArrayList<>();
		for(int i = 0; i < fullList.size(); i++) {
			if(fullList.get(i).getStartTime().equals("1300") || 
					fullList.get(i).getStartTime().equals("1325") ||
					fullList.get(i).getStartTime().equals("1400")) {
				if(fullList.get(i).getDays().contains("M") ||
						fullList.get(i).getDays().contains("W") ||
						fullList.get(i).getDays().contains("F"))
				{
					conflicts.add(fullList.get(i));
				}
			}
		}
		conflicts = this.conflictFinder(conflicts);
		return conflicts;

	}

	public ArrayList<CourseInfo> tues1245(ArrayList<CourseInfo> fullList) {
		conflicts = new ArrayList<>();
		for(int i = 0; i < fullList.size(); i++) {
			if(fullList.get(i).getStartTime().equals("1130")) {
				if((fullList.get(i).getDays().contains("T") ||
						fullList.get(i).getDays().contains("R")) &&
						(!fullList.get(i).getDays().contains("M") ||
								!fullList.get(i).getDays().contains("W") ||
								!fullList.get(i).getDays().contains("F")))
				{
					conflicts.add(fullList.get(i));
				}
			}
		}
		conflicts = this.conflictFinder(conflicts);
		return conflicts;

	}

	public ArrayList<CourseInfo> thurs1245(ArrayList<CourseInfo> fullList) {
		conflicts = new ArrayList<>();
		for(int i = 0; i < fullList.size(); i++) {
			if(fullList.get(i).getStartTime().equals("1300")) {
				if((fullList.get(i).getDays().contains("T") ||
						fullList.get(i).getDays().contains("R")) &&
						(!fullList.get(i).getDays().contains("M") ||
								!fullList.get(i).getDays().contains("W") ||
								!fullList.get(i).getDays().contains("F")))
				{
					conflicts.add(fullList.get(i));
				}
			}
		}
		conflicts = this.conflictFinder(conflicts);
		return conflicts;

	}

	public ArrayList<CourseInfo> mon1500(ArrayList<CourseInfo> fullList) {
		conflicts = new ArrayList<>();
		for(int i = 0; i < fullList.size(); i++) {
			if(fullList.get(i).getStartTime().equals("1600")) {
				if(fullList.get(i).getDays().contains("M") ||
						fullList.get(i).getDays().contains("W") ||
						fullList.get(i).getDays().contains("F"))
				{
					conflicts.add(fullList.get(i));
				}
			}
		}
		conflicts = this.conflictFinder(conflicts);
		return conflicts;

	}

	public ArrayList<CourseInfo> tues1500(ArrayList<CourseInfo> fullList) {
		conflicts = new ArrayList<>();
		for(int i = 0; i < fullList.size(); i++) {
			if(fullList.get(i).getStartTime().equals("1430")) {
				if((fullList.get(i).getDays().contains("T") ||
						fullList.get(i).getDays().contains("R")) &&
						(!fullList.get(i).getDays().contains("M") ||
								!fullList.get(i).getDays().contains("W") ||
								!fullList.get(i).getDays().contains("F")))
				{
					conflicts.add(fullList.get(i));
				}
			}
		}
		conflicts = this.conflictFinder(conflicts);
		return conflicts;

	}

	public ArrayList<CourseInfo> wed1500(ArrayList<CourseInfo> fullList) {
		conflicts = new ArrayList<>();
		for(int i = 0; i < fullList.size(); i++) {
			if(fullList.get(i).getStartTime().equals("1430")) {
				if(fullList.get(i).getDays().contains("M") ||
						fullList.get(i).getDays().contains("W") ||
						fullList.get(i).getDays().contains("F"))
				{
					conflicts.add(fullList.get(i));
				}
			}
		}
		conflicts = this.conflictFinder(conflicts);
		return conflicts;

	}

	public ArrayList<CourseInfo> thurs1500(ArrayList<CourseInfo> fullList) {
		conflicts = new ArrayList<>();
		for(int i = 0; i < fullList.size(); i++) {
			if(fullList.get(i).getStartTime().equals("1600")) {
				if((fullList.get(i).getDays().contains("T") ||
						fullList.get(i).getDays().contains("R")) &&
						(!fullList.get(i).getDays().contains("M") ||
								!fullList.get(i).getDays().contains("W") ||
								!fullList.get(i).getDays().contains("F")))
				{
					conflicts.add(fullList.get(i));
				}
			}
		}
		conflicts = this.conflictFinder(conflicts);
		return conflicts;

	}

	public ArrayList<CourseInfo> tues1730(ArrayList<CourseInfo> fullList) {
		conflicts = new ArrayList<>();
		for(int i = 0; i < fullList.size(); i++) {
			if(Integer.parseInt(fullList.get(i).getStartTime()) >= 1730) {
				if((fullList.get(i).getDays().contains("T") &&
						fullList.get(i).getDays().contains("R")) &&
						(!fullList.get(i).getDays().contains("M") ||
								!fullList.get(i).getDays().contains("W") ||
								!fullList.get(i).getDays().contains("F")))
				{
					conflicts.add(fullList.get(i));
				}
			}
		}
		conflicts = this.conflictFinder(conflicts);
		return conflicts;

	}

	public ArrayList<CourseInfo> wed1730(ArrayList<CourseInfo> fullList) {
		conflicts = new ArrayList<>();
		for(int i = 0; i < fullList.size(); i++) {
			if(Integer.parseInt(fullList.get(i).getStartTime()) >= 1730) {
				if((fullList.get(i).getDays().contains("M") &&
						fullList.get(i).getDays().contains("W")) &&
						(!fullList.get(i).getDays().contains("T") ||
								!fullList.get(i).getDays().contains("R") ||
								!fullList.get(i).getDays().contains("F")))
				{
					conflicts.add(fullList.get(i));
				}
			}
		}
		conflicts = this.conflictFinder(conflicts);
		return conflicts;

	}

	public ArrayList<CourseInfo> mon1945(ArrayList<CourseInfo> fullList) {
		conflicts = new ArrayList<>();
		for(int i = 0; i < fullList.size(); i++) {
			if(Integer.parseInt(fullList.get(i).getStartTime()) >= 1730) {
				if((fullList.get(i).getDays().contains("M")) &&
						(!fullList.get(i).getDays().contains("T") &&
								!fullList.get(i).getDays().contains("W") &&
								!fullList.get(i).getDays().contains("R") &&
								!fullList.get(i).getDays().contains("F")))
				{
					conflicts.add(fullList.get(i));
				}
			}
		}
		conflicts = this.conflictFinder(conflicts);
		return conflicts;

	}

	public ArrayList<CourseInfo> tues1945(ArrayList<CourseInfo> fullList) {
		conflicts = new ArrayList<>();
		for(int i = 0; i < fullList.size(); i++) {
			if(Integer.parseInt(fullList.get(i).getStartTime()) >= 1730) {
				if((fullList.get(i).getDays().contains("T")) &&
						(!fullList.get(i).getDays().contains("M") &&
								!fullList.get(i).getDays().contains("W") &&
								!fullList.get(i).getDays().contains("R") &&
								!fullList.get(i).getDays().contains("F")))
				{
					conflicts.add(fullList.get(i));
				}
			}
		}
		conflicts = this.conflictFinder(conflicts);
		return conflicts;

	}

	public ArrayList<CourseInfo> wed1945(ArrayList<CourseInfo> fullList) {
		conflicts = new ArrayList<>();
		for(int i = 0; i < fullList.size(); i++) {
			if(Integer.parseInt(fullList.get(i).getStartTime()) >= 1730) {
				if((fullList.get(i).getDays().contains("W")) &&
						(!fullList.get(i).getDays().contains("T") &&
								!fullList.get(i).getDays().contains("M") &&
								!fullList.get(i).getDays().contains("R") &&
								!fullList.get(i).getDays().contains("F")))
				{
					conflicts.add(fullList.get(i));
				}
			}
		}
		conflicts = this.conflictFinder(conflicts);
		return conflicts;

	}

	public ArrayList<CourseInfo> thurs1945(ArrayList<CourseInfo> fullList) {
		conflicts = new ArrayList<>();
		for(int i = 0; i < fullList.size(); i++) {
			if(Integer.parseInt(fullList.get(i).getStartTime()) >= 1730) {
				if((fullList.get(i).getDays().contains("R")) &&
						(!fullList.get(i).getDays().contains("M") &&
								!fullList.get(i).getDays().contains("W") &&
								!fullList.get(i).getDays().contains("T") &&
								!fullList.get(i).getDays().contains("F")))
				{
					conflicts.add(fullList.get(i));
				}
			}
		}
		conflicts = this.conflictFinder(conflicts);
		return conflicts;

	}

	public ArrayList<CourseInfo> fri1945(ArrayList<CourseInfo> fullList) {
		conflicts = new ArrayList<>();
		for(int i = 0; i < fullList.size(); i++) {
			if(Integer.parseInt(fullList.get(i).getStartTime()) >= 1730) {
				if((fullList.get(i).getDays().contains("F")) &&
						(!fullList.get(i).getDays().contains("M") &&
								!fullList.get(i).getDays().contains("W") &&
								!fullList.get(i).getDays().contains("T") &&
								!fullList.get(i).getDays().contains("R")))
				{
					conflicts.add(fullList.get(i));
				}
			}
		}
		conflicts = this.conflictFinder(conflicts);
		return conflicts;

	}

	// The MAIN logic for conflict finding: easily changed for future additions.
	// It is called in every method above, for each "time slot" of finals week
	public ArrayList<CourseInfo> conflictFinder(ArrayList<CourseInfo> fullList) {
		for(int i = 0; i < fullList.size(); i++) {
			for(int j = 0; j < fullList.size(); j++) {
				if(fullList.get(i).getName().equals(fullList.get(j).getName()) &&
						fullList.get(i).getCRN() != fullList.get(j).getCRN()) {
					if(fullList.get(i).getStartTime().equals(fullList.get(j).getStartTime())) {

					}
					else{
						fullList.get(i).setConflict(true);
						}
				}
				if(Integer.parseInt(fullList.get(j).getStartTime()) > Integer.parseInt(fullList.get(i).getEndTime())) {
					//if(!fullList.get(j).getCourseNum().equals(fullList.get(i).getCourseNum())) {
						fullList.get(j).setConflict(true);
						fullList.get(i).setConflict(true);
					}
				//}
			}
		}
		return fullList;

	}

}


