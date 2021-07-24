import java.util.ArrayList;
import java.util.List;

public class CourseWorkStudent extends Student{
	
	/*
	 * State
	 */
	private double assignment1, assignment2;
	private double practicalWork;
	private double finalExamination;
	private double weightedAverage;
	private String finalGrade; 
	
	/**
	 *  Default Constructors
	 * 
	 */
	public CourseWorkStudent() {
	     this.assignment1 = 0.0;
	     this.assignment2 = 0.0; 
	     this.practicalWork = 0.0; 
	     this.finalExamination = 0.0; 
	     this.weightedAverage = 0.0;
	     this.finalGrade = "";
	}
	
	/**
	 * Initializes the Student Information field and Course work grades
	 * @param titleName
	 * @param FirstName
	 * @param lastName
	 * @param idNumber
	 * @param month
	 * @param day
	 * @param year
	 * @param assignment1
	 * @param assignment2
	 * @param practicalWork
	 * @param finalExamination
	 */
	public CourseWorkStudent(String titleName , String FirstName, String lastName, 
							long idNumber , int month, int day, int year, 
							double assignment1, double assignment2, double practicalWork,
							double finalExamination) {
		super(titleName, FirstName, lastName, idNumber, month, day, year); 
		this.assignment1 = assignment1;
	    this.assignment2 = assignment2; 
	    this.practicalWork = practicalWork; 
	    this.finalExamination = finalExamination; 
	    this.weightedAverage = 0.0;
	    this.finalGrade = "";
	}
	
	/**
	 * Initialize Student Information Only
	 * @param titleName
	 * @param FirstName
	 * @param lastName
	 * @param idNumber
	 * @param month
	 * @param day
	 * @param year
	 */
	public CourseWorkStudent(String titleName , String FirstName, String lastName, 
			long idNumber , int month, int day, int year) {
		super(titleName, FirstName, lastName, idNumber, month, day, year); 
	}
	
	/*
	 * Getters and Setters 
	 * Note : for weightedAverage and Final Grade there are no setters
	 * to avoid direct input
	 */
	public double getAssignment1() {
		return assignment1;
	}

	public void setAssignment1(double assignment1) {
		this.assignment1 = assignment1;
	}

	public double getAssignment2() {
		return assignment2;
	}

	public void setAssignment2(double assignment2) {
		this.assignment2 = assignment2;
	}

	public double getPracticalWork() {
		return practicalWork;
	}

	public void setPracticalWork(double practicalWork) {
		this.practicalWork = practicalWork;
	}

	public double getFinalExamination() {
		return finalExamination;
	}

	public void setFinalExamination(double finalExamination) {
		this.finalExamination = finalExamination;
	}

	public double getWeightedAverage() {
		return weightedAverage;
	}

	public String getFinalGrade() {
		return finalGrade;
	}

	/**
	 * Computes the Weighted Average
	 */
	 public void calculateWeightedAverage() {
		 //The two assignments together count for a total of 50% (25% each) 
	     this.weightedAverage += (this.assignment1 * 0.25) + (this.assignment2 * 0.25);
	     //the practical work is worth 20%, 
	     this.weightedAverage += this.practicalWork * 0.20;
	     // the final exam is worth 30% of the final grade.
	     this.weightedAverage += this.finalExamination * 0.30;
	  }
	 
	 /**
	  * The final grade, for coursework students, 
	  * is to be awarded on the basis of an overall mark, w
	  * hich is a number in the range 0 to 100 and 
	  * is obtained by calculating the weighted average of the student's
	  */
	 public void computeFinalGrade() {
		// compute the average 
		 if(weightedAverage <= 0.0) {
			 calculateWeightedAverage();
		 }
		// compute the Final Grade according to the range
	     if(weightedAverage >= 80.0) {
	    	 this.finalGrade = "HD";  			//An overall mark of 80 or higher is an HD, 
	     } else if(weightedAverage >= 70.0) {
	    	 this.finalGrade = "D";					//an overall mark of 70 or higher (but less than 80) is a D
	     } else if(weightedAverage >= 60.0){
	    	 this.finalGrade = "C";					// an overall mark of 60 or higher (but less than 70) is a C,
	     } else if(weightedAverage >= 50.0) {
	    	 this.finalGrade = "P";					//an overall mark of 50 or higher (but less than 60) is a P
	     } else {
	    	 this.finalGrade = "N"; 					//and an overall mark below 50 is an N.
	    } 
	 }
	 
	 /**
	  * Overrides toString method. 
	  * Prints the Course Work Student Information as well as the Student Information
	  */
	 @Override
	public String toString() {		
		String result = super.toString(); 
		result += "\n Assignment 1: " + this.assignment1 +
		           "\n Assignment 2: " + this.assignment2 +
		           "\n Practical Work: " + this.practicalWork +
		           "\n Final Oral Examination: " + this.finalExamination +
		           "\n Weighted Average: " + this.weightedAverage + 
		           "\n Final Grade: " + this.finalGrade;
		return result; 
	}
	
}
