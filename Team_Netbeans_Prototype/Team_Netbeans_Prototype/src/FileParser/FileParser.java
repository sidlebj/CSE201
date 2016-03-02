package FileParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class FileParser {
	File csvFile;
	public static ArrayList<CourseInfo> arrayOfClasses;

	public FileParser(File file) {
		this.csvFile = file;
		arrayOfClasses = new ArrayList<CourseInfo>(30);
	}

	public ArrayList<CourseInfo> getClasses() {
		return arrayOfClasses;
	}

	// Using the external CSVReader library, which we found
	// led to the most concise and successful code
	public void parse() {
		CSVReader reader;
		String [] classes;
		try {
			reader = new CSVReader(new FileReader(this.csvFile));
			while ((classes = reader.readNext()) != null) {
				if (classes[0].equals("CRN") == true) {
					//do nothing
				}

				else if((classes[8].equals("")) || (classes[11].equals(""))){

				}
				
				else{
					CourseInfo currentCourse = new CourseInfo(Integer.parseInt(classes[0]), classes[1],
							classes[2], classes[3], classes[4], Integer.parseInt(classes[5]),
							Integer.parseInt(classes[6]), Integer.parseInt(classes[7]), classes[8], classes[9],
							classes[10], classes[11], Integer.parseInt(classes[12]), classes[13]);
					
					if(this.arrayOfClasses.contains(currentCourse)) {
						if(currentCourse.getDays().length() >= 2) {
							arrayOfClasses.set(arrayOfClasses.size() -1,  currentCourse);
						}
					}
					
					else{
						this.arrayOfClasses.add(currentCourse);
					}

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i = 0; i<this.arrayOfClasses.size();i++) {
			System.out.println(this.arrayOfClasses.get(i));
		}
	}

}
