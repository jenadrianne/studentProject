
public class ResearchStudent extends Student{

	/*
	 * State Variables
	 */
	private double proposalComponent;
	private double oralPresentations;
	private double finalThesis;
	private double weightedAverage;
	private String finalGrade;
	
	/**
	 * Default Constructor
	 */
	public ResearchStudent() {
		this.proposalComponent = 0.0;
		this.oralPresentations = 0.0; 
		this.finalThesis = 0.0;
		this.weightedAverage = 0.0; 
		this.finalGrade = "";
	}
	
	/**
	 * Custom Constructor that sets Student Information and Research Student Information
	 * @param titleName
	 * @param FirstName
	 * @param lastName
	 * @param idNumber
	 * @param month
	 * @param day
	 * @param year
	 * @param proposalComponent
	 * @param oralPresentations
	 * @param finalThesis
	 */
	public ResearchStudent(String titleName , String FirstName, String lastName, 
			long idNumber , int month, int day, int year, 
			double proposalComponent, double oralPresentations, double finalThesis) {
		super(titleName, FirstName, lastName, idNumber, month, day, year); 
		this.proposalComponent = proposalComponent;
		this.oralPresentations = oralPresentations; 
		this.finalThesis = finalThesis;
		this.weightedAverage = 0.0; 
		this.finalGrade = "";
	}
	
	/**
	 * Custome Constructor that sets only the Student information 
	 * @param titleName
	 * @param FirstName
	 * @param lastName
	 * @param idNumber
	 * @param month
	 * @param day
	 * @param year
	 */
	public ResearchStudent(String titleName , String FirstName, String lastName, 
			long idNumber , int month, int day, int year) {
		super(titleName, FirstName, lastName, idNumber, month, day, year); 
		this.proposalComponent = 0.0;
		this.oralPresentations = 0.0; 
		this.finalThesis = 0.0;
		this.weightedAverage = 0.0; 
		this.finalGrade = "";
	}

	/*
	 * Getters and Setters 
	 * Note : for weightedAverage and Final Grade there are no setters
	 * to avoid direct input
	 */
	public double getProposalComponent() {
		return proposalComponent;
	}

	public void setProposalComponent(double proposalComponent) {
		this.proposalComponent = proposalComponent;
	}

	public double getOralPresentations() {
		return oralPresentations;
	}

	public void setOralPresentations(double oralPresentations) {
		this.oralPresentations = oralPresentations;
	}

	public double getFinalThesis() {
		return finalThesis;
	}

	public void setFinalThesis(double finalThesis) {
		this.finalThesis = finalThesis;
	}

	public double getWeightedAverage() {
		return weightedAverage;
	}

	public String getfinalGrade() {
		return finalGrade;
	}

	/**
	  * The final grade, for coursework students, 
	  * is to be awarded on the basis of an overall mark, w
	  * hich is a number in the range 0 to 100 and 
	  * is obtained by calculating the weighted average of the student's
	  */
	 public void computefinalGrade() {
		// compute the average 
		 if(weightedAverage <= 0.0) {
			 calculateWeightedAverage();
		 }
		// compute the Final Grade according to the range
	     if(weightedAverage >= 80.0) {
	    	 this.finalGrade = "HD";  			//An overall mark of 80 or higher is an HD, 
	     } else if(weightedAverage >= 70.0) {
	    	 this.finalGrade = "D";				//an overall mark of 70 or higher (but less than 80) is a D
	     } else if(weightedAverage >= 60.0){
	    	 this.finalGrade = "C";				// an overall mark of 60 or higher (but less than 70) is a C,
	     } else if(weightedAverage >= 50.0) {
	    	 this.finalGrade = "P";				//an overall mark of 50 or higher (but less than 60) is a P
	     } else {
	    	 this.finalGrade = "N"; 			//and an overall mark below 50 is an N.
	    } 
	 }
	 
	 /**
	 * Computes the Weighted Average
	 */
	 public void calculateWeightedAverage() {
	     // final oral presentations are worth a total of 20%
	     this.weightedAverage += this.oralPresentations  * 0.20;
	     // and the final thesis is worth 80% of the
	     this.weightedAverage += this.finalThesis  * 0.80;
	  }

}
