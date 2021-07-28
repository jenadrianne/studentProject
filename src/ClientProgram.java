import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ClientProgram {
	public static ArrayList<Student> students = new ArrayList<Student>();
	public static Scanner scanners = new Scanner(System.in); 
	public static boolean isSorted = false;
	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String [] args) {
		ClientProgram cp = new ClientProgram();
		int option =0 ;
		//reads the students in the File
		cp.readStudents();
	     
		//Show the menu    
	     try {
			do {
				option = cp.showMenu(); 
			    switch(option) {
			    	case 1:
			    		  System.exit(0);
			    		  break;
			        case 2:
			              cp.addStudent();;
			              break; 
			       case 3:
			    	   	  cp.removeStudent(); 
			    	   	  break;
			       case 4:
			    	   	  cp.showStudent(0);
			    	      break;
			       case 5:
			    	   	  System.out.print("\n Enter which type of Student (1-Coursework , 2-Research) : ");
			    	   	  int type = scanners.nextInt();
			    	   	  if(type<=2 && type>0) {
				    	      cp.showStudent(type);
			    	   	  }
			    	      break;
			       case 6:
			    	     cp.showCourseWordStudentBelowAboveAvg();
			    	     cp.showResearchStudentBelowAboveAvg();
			    	     break;
			       case 7:
			    	      cp.showStudent(0);
			    	      System.out.print("\n Enter Student ID to search: ");
				          long id = scanners.nextLong();
				          int pos = cp.searchStudentID(id);
				          if(pos == -1) {
				                 System.out.println("\n ERROR: No Student found on ID: " + id);
				          } else {
				                 System.out.println(cp.students.get(pos));
				          }
			    	      break;
			       
			       case 8:
				          System.out.print("\n Enter student's Last name to search: ");
				          String lastName = scanners.next();
				          System.out.print("\n Enter student's First name to search: ");
				          String firstName = scanners.next();
				          cp.searchStudentName(lastName, firstName);
				          break;
			       case 9 : 
			    	   	  sortById();
			    	   	  break; 
			       case 10: 
			    	   	  if(isSorted) {
			    	   		  //output csv
			    	   		  outputtocsv();
			    	   	  }else {
			    	   		  System.out.println("Please Sort Array first"); 
			    	   	  }
			    	   	  break;
			       default:
			    	   	   System.out.print("\n Invalid choice!!");
			    }
			 }while(option != 1);
		} catch (Exception e) {
			System.out.println("Invalid input. Terminating the program....");
		}  
	     
	     scanners.close();
	  }
	
	 /**
	  * Show menu options
	  * @return
	  */
	  public static  int showMenu()
	  {
		 int option = 0; 
	     System.out.print("\n\n ************** MENU ************** ");
	     System.out.print("\n\t 1 - Quit "
	     					+ "\n\t 2 - Add CourseWork or Research Information"
	     					+ "\n\t 3 - Remove Student" 
	     					+ "\n\t 4 - Show All Student Information "
	     					+ "\n\t 5 - Show CourseWork or Research Information"
	     					+ "\n\t 6 - Students Below or Above average" 
	     					+ "\n\t 7 - Search Student by ID "
	     					+ "\n\t 8 - Search Student by name"
	     					+ "\n\t 9 - Sort Student by ID"
	     					+ "\n\t 10 - Output Sorted Array"
	     					+ "\n\n What is your choice? ");
	     option = scanners.nextInt();
	     return option; 
	  }
	  
	  /**
	   * Reads the student information stored in a file student.txt
	   */
	  public static void readStudents()
	  {
	     // Scanner class object declared
	     Scanner reader = null; 
	     
	     try
	     {
	        // Opens the file for reading
	    	 reader = new Scanner(new File("student.txt"));
	        
	        // Loops till end of the file to read records
	        while(reader.hasNextLine())
	        {  
	           String student = reader.nextLine();
	           String[] splitData = student.split(",");
	           
	           if(splitData.length == 7) {
	        	   String title = splitData[0]; 
	        	   String FirstName = splitData[1]; 
	        	   String LastName = splitData[2]; 
	        	   Long id = Long.parseLong(splitData[3]);
	        	   int month = Integer.parseInt(splitData[4]);
	        	   int day = Integer.parseInt(splitData[5]);
	        	   int year = Integer.parseInt(splitData[6]);
	        	   
	        	   students.add(new Student(title, FirstName, LastName, id, month, day, year));
	           }
	        }   

		     
		     reader.close();
	     } catch(FileNotFoundException fe) {
	         System.out.println("\n ERROR: Unable to open the file for reading.\n");
	     } catch(NumberFormatException nf) {
	    	 System.out.println("\n Error in parsing data\n"); 
	    	 nf.printStackTrace();
	     } catch(Exception ex) {
	    	 System.out.println("\n Error when reading the file.\n"); 
	     }
	    
	  }
	  
	  /**
	   * Search Student by Student ID
	   * @param id
	   * @return index in the Array
	   */
	  public static int searchStudentID(long id)
	  {
	     for(int x = 0; x < students.size(); x++) {
	    	 if(id == students.get(x).getIdNumber()) {
		           return x;
	    	 }
	     }
	        
	     return -1; // not found 
	  }
	  
	  /**
	   * Search Students by Student Name
	   * @param name
	   */
	  public static void searchStudentName(String lastName, String FirstName)
	  {
	     int found = -1;
	     for(int c = 0; c < students.size(); c++)
	     {
	        if(lastName.equalsIgnoreCase(students.get(c).getLastName()) && 
	        		FirstName.equalsIgnoreCase(students.get(c).getFirstName()))
	        {
	           System.out.println(students.get(c));
	           System.out.println("----------\n");
	           found = c;
	        }
	     }
	     if(found == -1)
	        System.out.println("\n ERROR: No student found on name: " + FirstName + " " + lastName);
	  }
	  
	  /**
	   * Add Student Records
	   */
	  public static void addStudent()
	  {
	     // Scanner class object declared
	     Scanner readCourseF = null;
	     Scanner readResearchF = null;
	     int position = -1; 
	     String[] splitData = null;
	     
	     try {
	        // Opens the file for reading
	        readCourseF = new Scanner(new File("courseWorkStudent.txt"));
	        readResearchF = new Scanner(new File("researchStudent.txt"));
	              
	        // Loops till end of the file to read records for course work student
	        while(readCourseF.hasNextLine()) {  
	           String course = readCourseF.nextLine();
	           splitData = course.split(",");                 
	           
	           if(splitData.length == 5) {
	        	   position = searchStudentID(Long.parseLong(splitData[0]));
	        	   if(position != -1) {
	        		   Student stud = students.get(position);
	        		   CourseWorkStudent temp = new CourseWorkStudent(
	        				 stud.getTitleName(),
	        				 stud.getFirstName(),
	        				 stud.getLastName(),
	        				 stud.getIdNumber(), 
	        				 stud.getBirthday().getMonth(),
	        				 stud.getBirthday().getDay(),
	        				 stud.getBirthday().getYear(),
	  	 	                 Double.parseDouble(splitData[1]),
	  	 	                 Double.parseDouble(splitData[2]),
	  	 	                 Double.parseDouble(splitData[3]),
	  	 	                 Double.parseDouble(splitData[4]));
	        		   
	        		   students.set(position, temp);
	        	   } 
	           }
	        }
	           // Loops till end of the file to read records for research student
	        while(readResearchF.hasNextLine()) {  
	           String reserch = readResearchF.nextLine();
	           splitData = reserch.split(",");
	           
	           if(splitData.length == 3) {
		           position = searchStudentID(Long.parseLong(splitData[0]));
		           if(position != -1) {
		        	   Student stud = students.get(position);
		        	   ResearchStudent temp = new ResearchStudent(
		        				 stud.getTitleName(),
		        				 stud.getFirstName(),
		        				 stud.getLastName(),
		        				 stud.getIdNumber(), 
		        				 stud.getBirthday().getMonth(),
		        				 stud.getBirthday().getDay(),
		        				 stud.getBirthday().getYear(),
		        				 Double.parseDouble(splitData[1]),
		        				 Double.parseDouble(splitData[2]));
		        	   students.set(position, temp);
		           }
	           }
	        	   
	        } 
	        calculateGrade(); 
	        readCourseF.close();
		    readResearchF.close();
	     } catch(FileNotFoundException fe) {
	        System.out.println("\n ERROR: Unable to open the file for reading.");
	     } catch(Exception fe) {
		    System.out.println("\n ERROR: Unable to open the file for reading.");
		 }
	  }
	  
	  /**
	   * Compute the File grade of the student 
	   */
	  public static void calculateGrade(){
	     for(int c = 0; c < students.size(); c++) {
	        if(students.get(c) instanceof CourseWorkStudent){
	           CourseWorkStudent cw = (CourseWorkStudent)students.get(c);
	           cw.computeFinalGrade();
	        }
	     }
	     
	     for(int c = 0; c < students.size(); c++){
	        if(students.get(c) instanceof ResearchStudent) {
	           ResearchStudent rs = (ResearchStudent)students.get(c);
	           rs.computefinalGrade();
	        }
	     }
	  }
	  
	  /**
	   * Show students below average - course work student
	   */
	  public void showCourseWordStudentBelowAboveAvg()
	  {
	     double total = 0.0;
	     int counter = 0;
	     for(int c = 0; c < students.size(); c++){
	        if(students.get(c) instanceof CourseWorkStudent) {
	           CourseWorkStudent cw = (CourseWorkStudent)students.get(c);
	           total += cw.getWeightedAverage();
	           counter++;
	        }
	     }
	     double avg = total / counter;
	     System.out.print("********** Course Work Student below average **********");
	     for(int c = 0; c < students.size(); c++){
	        if(students.get(c) instanceof CourseWorkStudent){
	           CourseWorkStudent cw = (CourseWorkStudent)students.get(c);
	           if(cw.getWeightedAverage() < avg)
	              System.out.println(cw);
	        }
	     }
	     
	     System.out.print("********** Course Work Student above average **********");
	     for(int c = 0; c < students.size(); c++){
	        if(students.get(c) instanceof CourseWorkStudent){
	           CourseWorkStudent cw = (CourseWorkStudent)students.get(c);
	           if(cw.getWeightedAverage() > avg)
	              System.out.println(cw);
	        }
	     }
	  }
	  
	  /**
	   * Show students below average research 
	   */
	  public static void showResearchStudentBelowAboveAvg()
	  {
	     double total = 0.0;
	     int counter = 0;
	     for(int c = 0; c < students.size(); c++){
	        if(students.get(c) instanceof ResearchStudent)
	        {
	           ResearchStudent re = (ResearchStudent)students.get(c);
	           total += re.getWeightedAverage();
	           counter++;
	        }
	     }
	     double avg = total / counter;
	     System.out.print("********** Research Student below average **********\n\n");
	     for(int c = 0; c < students.size(); c++)
	     {
	        if(students.get(c) instanceof ResearchStudent)
	        {
	           ResearchStudent re = (ResearchStudent)students.get(c);
	           if(re.getWeightedAverage() < avg)
	              System.out.println(re);
	        }
	     }
	     
	     System.out.print("********** Research Student above average **********\n\n");
	     for(int c = 0; c < students.size(); c++)
	     {
	        if(students.get(c) instanceof ResearchStudent)
	        {
	           ResearchStudent re = (ResearchStudent)students.get(c);
	           if(re.getWeightedAverage() > avg)
	              System.out.println(re);
	        }
	     }
	  }
	  
	  /**
	   * Removes student from list
	   */
	  public static void removeStudent()
	  {
		 showStudent(0);
	     System.out.print("\n Enter student id to remove: ");
	     long id = scanners.nextLong();
	     
	     int pos = searchStudentID(id);
	     if(pos == -1) {
		      System.out.println("\n ERROR: No student found having ID: " + id);
	     }else {
	    	 Student stud = students.get(pos);
	    	 System.out.format("\nAre you sure to remove %d: %s %s %s ? (Yes/No)", stud.getIdNumber(), stud.getTitleName(), stud.getFirstName(), stud.getLastName());
		     String opt = scanners.next(); 
		     if(opt.equalsIgnoreCase("Yes")) {
		    	 students.remove(pos);
		    	 System.out.format("\nSuccessfully removed %d: %s %s %s", stud.getIdNumber(), stud.getTitleName(), stud.getFirstName(), stud.getLastName());
		     }
	     }
	  }
	  
	 /**
	  * Display Student Information according to type 
	  * 1 - Course Work Student
	  * 2 - Research Students
	  * 0 or Any - All Student
	  * @param type
	  */
	  public static void showStudent(int type)
	  {
		 switch(type) {
		 case 1: 
			 System.out.print("********** Course Work Student Information **********\n\n");
		     for(int c = 0; c < students.size(); c++){
		        if(students.get(c) instanceof CourseWorkStudent){
		           CourseWorkStudent cw = (CourseWorkStudent)students.get(c);
		           System.out.println(cw);
		           System.out.println("----------");
		        }
		     }
		     break; 
		 case 2: 
			 System.out.print("********** Research Student Information **********\n\n");
		     for(int c = 0; c < students.size(); c++){
		        if(students.get(c) instanceof ResearchStudent){
		           ResearchStudent rs = (ResearchStudent)students.get(c);
		           System.out.println(rs);
		           System.out.println("----------");
		        }
		     }
		     break; 
		 default:
			 for(Student stud : students) {
			    	System.out.println(stud.toString());
			    	System.out.println("\n-------");
			     } 
			 break;
		 }
	  }
	  
	  /**
	   * Sort ArrayLis by ID in ascending order
	   */
	  public static void sortById() {
		  Collections.sort(students);
		  isSorted = true;
	  }
	  
	  /**
	   * Output sorted array to csv
	   */
	  public static void outputtocsv() {
        try
        {
        	String CSV_SEPARATOR = ",";
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.csv"), "UTF-8"));
            StringBuffer oneLine = new StringBuffer();
            
            //header
            oneLine.append("ID" + CSV_SEPARATOR);
            oneLine.append("Title" + CSV_SEPARATOR);
            oneLine.append("First Name" + CSV_SEPARATOR);
            oneLine.append("Last Name" + CSV_SEPARATOR);
            oneLine.append("Birthday" + CSV_SEPARATOR);
            oneLine.append("Assignment 1" + CSV_SEPARATOR);
            oneLine.append("Assignment 2" + CSV_SEPARATOR);
            oneLine.append("Practical" + CSV_SEPARATOR);
            oneLine.append("Final Examination" + CSV_SEPARATOR);
            oneLine.append("Oral Presentation" + CSV_SEPARATOR);
            oneLine.append("Final Thesis" + CSV_SEPARATOR);
            oneLine.append("Weighted Average" + CSV_SEPARATOR);
            oneLine.append("Final Grade" + CSV_SEPARATOR);
            bw.write(oneLine.toString());
            bw.newLine();
            
            //contents
            for (Student student : students)
            {
            	oneLine = new StringBuffer();
                oneLine.append(student.toCSVFormt());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {
        	e.printStackTrace();
        }
        catch (FileNotFoundException e){
        	e.printStackTrace();
        }
        catch (IOException e){
        	e.printStackTrace();
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
		    
	  }
}
