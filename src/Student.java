/**
 * Student Class 
 * @author SonicMaster ASUS
 *
 */
public class Student {
	/*
	 * State Variables
	 */
	private String titleName; 
	private String firstName; 
	private String lastName; 
	private long idNumber; 
	private Date birthday; 
	
	/**
	 * Default Constructor
	 */
	public Student() {
		
	}
	
	/**
	 * Custom Constructor
	 * @param titleName
	 * @param FirstName
	 * @param lastName
	 * @param idNumber
	 * @param month
	 * @param day
	 * @param year
	 */
	public Student(String titleName , String FirstName, String lastName, long idNumber , int month, int day, int year ) {
		this.titleName = titleName; 
		this.firstName = FirstName; 
		this.lastName = lastName; 
		this.idNumber = idNumber; 
		this.birthday = new Date(month, day, year);
	}

	/*
	 * Getters and Setters
	 */
	
	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(long idNumber) {
		this.idNumber = idNumber;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	/**
	 * Overrides toString Function to display Student Info
	 */
	@Override
	public String toString() {		
		return String.format("Name: %s %s %s \nId : %d \nBirthday : %s\n",
								this.titleName, this.firstName, this.lastName, this.idNumber, this.birthday.toString());
	}
	
	/**
	 * an equals method which compares two student objects and returns true if they have the same student number (ID), otherwise it returns false.
	 * @param stud1
	 * @param stud2
	 * @return
	 */
	 @Override
	 public boolean equals(Object o) {	
		 // check if the object is a student
		 if (!(o instanceof Student)) {
	            return false;
	     }
		 
		 // check if the object is the same as the instance 
		 if(o == this) {
			 return true; 
		 }
		 
		// typecast o to Student so that we can compare data members 
	    Student stud = (Student) o;
	          
	   // Compare the data members and return accordingly 
	    return Long.compare(this.idNumber, stud.idNumber) == 0;
	}
}
