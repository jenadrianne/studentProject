import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientProgram {
	public static ArrayList <Student> students = new ArrayList<Student>();
	public static ArrayList <Student> courses = new ArrayList<Student>();
	public static Scanner scanners = new Scanner(System.in); 
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
	        	   cp.showCourseWordStudent();
	        	   cp.showResearchStudents();
	        	   break;
	           case 5:
	        	   cp.showCourseWordStudent();
	        	   break;
	           case 6:
	        	   cp.showResearchStudents();
	        	   break;
	           case 7:
	        	   cp.showCourseWordStudentBelowAboveAvg();
	        	   break;
	           case 8:
	        	   cp.showResearchStudentBelowAboveAvg();
	        	   break;
	           case 9:
	              System.out.print("\n Enter Strudent ID to search: ");
	              long id = scanners.nextLong();
	              int pos = cp.searchStudentID(id);
	              if(pos == -1) {
		                 System.out.println("\n ERROR: No sudent found on ID: " + id);
	              } else {
		                 System.out.println(cp.students.get(pos));
	              }
	              break;
	           case 10:
	              System.out.print("\n Enter student's Last name to search: ");
	              String name = scanners.next();
	              cp.searchStudentName(name);
	              break;
	           default:
	              System.out.print("\n Invalid choice!!");
	        }
	     }while(option >0 && option <=10);  
	     
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
	     System.out.print("\n\t 1 - Quit \n\t 2 - Add \n\t 3 - Remove Student" +
	           "\n\t 4 - Show All \n\t 5 - Show Coursework \n\t 6 - Research" +
	           "\n\t 7 - Coursework Below or Above average" +
	           "\n\t 8 - Research Below or Above average" +
	           "\n\t 9 - Search Student by ID \n\t 10 - Search Student by name" +
	           "\n\n What is your choice? ");
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
	           String[] splitData = student.split(" ");
	           
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
	  public static void searchStudentName(String name)
	  {
	     int found = -1;
	     for(int c = 0; c < courses.size(); c++)
	     {
	        if(name.equalsIgnoreCase(courses.get(c).getLastName()))
	        {
	           System.out.println(courses.get(c));
	           found = c;
	        }
	     }
	     if(found == -1)
	        System.out.println("\n ERROR: No student found on name: " + name);
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
	           splitData = course.split(" ");                 
	           
	           if(splitData.length == 5) {
	        	   position = searchStudentID(Long.parseLong(splitData[0]));
	        	   if(position != -1) {
	        		   Student stud = students.get(position);
	        		   courses.add(new CourseWorkStudent(
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
	  	 	                 Double.parseDouble(splitData[4])));
	        	   } 
	           }
	        }
	           // Loops till end of the file to read records for research student
	        while(readResearchF.hasNextLine()) {  
	           String reserch = readResearchF.nextLine();
	           splitData = reserch.split(" ");
	           
	           position = searchStudentID(Long.parseLong(splitData[0]));
	           
	           if(position != -1) {
	        	   Student stud = students.get(position);
	        	   courses.add(new ResearchStudent(
	        				 stud.getTitleName(),
	        				 stud.getFirstName(),
	        				 stud.getLastName(),
	        				 stud.getIdNumber(), 
	        				 stud.getBirthday().getMonth(),
	        				 stud.getBirthday().getDay(),
	        				 stud.getBirthday().getYear(),
	        				 Double.parseDouble(splitData[1]),
	        				 Double.parseDouble(splitData[2]),
	        				 Double.parseDouble(splitData[3])));
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
	     for(int c = 0; c < courses.size(); c++) {
	        if(courses.get(c) instanceof CourseWorkStudent){
	           CourseWorkStudent cw = (CourseWorkStudent)courses.get(c);
	           cw.computeFinalGrade();
	        }
	     }
	     
	     for(int c = 0; c < courses.size(); c++){
	        if(courses.get(c) instanceof ResearchStudent) {
	           ResearchStudent rs = (ResearchStudent)courses.get(c);
	           rs.computefinalGrade();
	        }
	     }
	  }
	  
	  
	  /**
	   * Display Course Work Students 
	   */
	  public static void showCourseWordStudent() {
	     System.out.print("********** Course Work Student Information **********\n\n");
	     for(int c = 0; c < courses.size(); c++){
	        if(courses.get(c) instanceof CourseWorkStudent){
	           CourseWorkStudent cw = (CourseWorkStudent)courses.get(c);
	           System.out.println(cw);
	        }
	     }
	  }
	  
	  /**
	   * Display Research Students 
	   */
	  void showResearchStudents(){
	     System.out.print("********** Research Student Information **********\n\n");
	     for(int c = 0; c < courses.size(); c++){
	        if(courses.get(c) instanceof ResearchStudent){
	           ResearchStudent rs = (ResearchStudent)courses.get(c);
	           System.out.println(rs);
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
	     for(int c = 0; c < courses.size(); c++){
	        if(courses.get(c) instanceof CourseWorkStudent) {
	           CourseWorkStudent cw = (CourseWorkStudent)courses.get(c);
	           total += cw.getWeightedAverage();
	           counter++;
	        }
	     }
	     double avg = total / counter;
	     System.out.print("********** Course Work Student below average **********");
	     for(int c = 0; c < courses.size(); c++){
	        if(courses.get(c) instanceof CourseWorkStudent){
	           CourseWorkStudent cw = (CourseWorkStudent)courses.get(c);
	           if(cw.getWeightedAverage() < avg)
	              System.out.println(cw);
	        }
	     }
	     
	     System.out.print("********** Course Work Student above average **********");
	     for(int c = 0; c < courses.size(); c++){
	        if(courses.get(c) instanceof CourseWorkStudent){
	           CourseWorkStudent cw = (CourseWorkStudent)courses.get(c);
	           if(cw.getWeightedAverage() > avg)
	              System.out.println(cw);
	        }
	     }
	  }
	  
	  /**
	   * Show students below average research 
	   */
	  void showResearchStudentBelowAboveAvg()
	  {
	     double total = 0.0;
	     int counter = 0;
	     for(int c = 0; c < courses.size(); c++){
	        if(courses.get(c) instanceof ResearchStudent)
	        {
	           ResearchStudent re = (ResearchStudent)courses.get(c);
	           total += re.getWeightedAverage();
	           counter++;
	        }
	     }
	     double avg = total / counter;
	     System.out.print("********** Research Student below average **********\n\n");
	     for(int c = 0; c < courses.size(); c++)
	     {
	        if(courses.get(c) instanceof ResearchStudent)
	        {
	           ResearchStudent re = (ResearchStudent)courses.get(c);
	           if(re.getWeightedAverage() < avg)
	              System.out.println(re);
	        }
	     }
	     
	     System.out.print("********** Research Student above average **********\n\n");
	     for(int c = 0; c < courses.size(); c++)
	     {
	        if(courses.get(c) instanceof ResearchStudent)
	        {
	           ResearchStudent re = (ResearchStudent)courses.get(c);
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
		 Scanner sc = new Scanner(System.in); 
	     System.out.print("\n Enter student id to remove: ");
	     long id = sc.nextLong();
	     
	     int pos = searchStudentID(id);
	     if(pos == -1) {
		      System.out.println("\n ERROR: No sutdent found having ID: " + id);
	     }else {
		     courses.remove(pos);
	     }
	  }
}
