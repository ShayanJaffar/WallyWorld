import java.util.LinkedList;

import javax.swing.JOptionPane;

public class JOPGUI {
	UIController uic;
    
	/**
	 * Creates a new GUI Interface 
	 * @param uiController link to the UI Controller
	 */
	public JOPGUI (UIController uiController) {
		uic = uiController;
	}
	
	/**
	 * prints the welcome text
	 */
	public int welcomeOption () {
    	String[] options = {
			"1) Login\n", 
			"2) Create an account\n", 
			"3) Switch input mode\n", 
			"4) Quit", 
			"5) View Usernames/Passwords"
		};
		return getChoiceInput("Welcome to WallyWorld!\nWhat would you like to do?\n", options);
	}
	/**
	 * allows the user to switch ui mode
	 * @return new mode to switch to (-1 if do not switch)
	 */
	public int getModeSwitch () {
		String[] options = {"0) command line", "1) gui"};
		return getChoiceInput("\nWould you like to switch user interface mode?\n", options);
	}
	public Applicant createNewAccount () {
		String username = getStringInput("Enter Username: ", false);
		String password = getStringInput("Enter Password: ", false);
		showMessage("Created New Applicant Account.\n");
		return new Applicant(username, password);
	}
	
	/**
	 *  Accessed if employee enters the system
	 *  Gets Employee input and accesses functions accordingly
	 * 
	 */
	public void employeeMainMenu() {
        while (true) {
        	String[] options = {
        		"1) Show Schedule\n", 
        		"2) View Availability\n", 
        		"3) Change Availability\n", 
                "4) Show Manager Contact Info\n", 
                "5) Logout "
            };
        	int choice = getChoiceInput(null, options);
            switch(choice) {
            	case 1:
            		printSchedule();
            		break;
            	case 2:
            		showMessage(uic.getCurrentUserAvailability(getStringInput("Date: ", false)).asString(true));
            		break;
            	case 3:
            		updateEmployeeAvailability();
            		break;
            	case 4:
            		printManagerInfo();
            		break;
            	case 5:
            		uic.logout();
            		return;
            }
        }
	}
	/**
	 * Accessed if user is an manager
	 * Gets Manager input and accesses functions accordingly
	 * 
	 */
	public void managerMainMenu() {
        while (true) {
        	String[] options = {
        		"1) Display Employee Usernames\n", 
        		"2) Show Shifts Covered\n", 
                "3) Assign Shift\n", 
                "4) Remove Shift\n", 
                "5) Display Employee Contact Information\n", 
                "6) View Applicants\n", 
                "7) Hire/Reject Applicants\n", 
                "8) Send Paychecks\n", 
                "9) Logout\n"
            };
        	int choice = getChoiceInput(null, options);
            switch(choice) {
            	case 1:
            		displayEmployeeUsernames();
            		break;
            	case 2:
            		showShiftsCovered();
            		break;
            	case 3:
            		assignShift(true);
            		break; 		
            	case 4:
            		assignShift(false);
            		break;
            	case 5:
            		displayEmployeeInformation();
            		break;
            	case 6:
            		viewApplicants();
            		break;
            	case 7:
            		hireOrRejectApp();
            		break;
            	case 8:
            		generatePaychecks();
            		break;
            	case 9:
            		uic.logout();
            		return;
            }
        }
	}
	private void hireOrRejectApp() {
		viewApplicants();
		String[] options = {"0) Hire", "1) Reject", "2) Back"};
		int choice = this.getChoiceInput(null, options);
		switch(choice) {
			case 0:
				if(uic.hireApplicant(this.getStringInput("Applicant's Username: ", false)))
					showMessage("Applicant was hired!");
				else
					showMessage("Username not found.");
				break;
			case 1:
				if(uic.rejectApplicant(this.getStringInput("Applicant's Username: ", false)))
					showMessage("Applicant was rejected.");
				else
					showMessage("Username not found.");
				break;
			default: //case 2
				return;
			
		}
		
	}
	
	private void generatePaychecks() {
		showMessage(uic.genPaycheck());
	}

	private void viewApplicants() {
		for (Applicant a : uic.getApplicants()) {
			showMessage(a.info());
		}
	}

	public void applicantMainMenu() {
		while (true) {
        	String[] options = {
        		"1) View Application\n", 
                "2) Show Manager Contact Info\n", 
                "3) Update Basic Information\n", 
                "4) Update Avability\n", 
                "5) Update Resume\n", 
                "6) Delete Application\n", 
                "7) Logout "
        	};
        	int choice = getChoiceInput(null, options);
            switch(choice) {
            	case 1:
            		printApplicantInfo();
            		break;
            	case 2:
            		printManagerInfo();
            		break;
            	case 3:
            		updateBasicInfo();
            		break;
            	case 4:
            		updateApplicantAvailability();
            		break;
            	case 5:
            		updateResume();
            		break;
            	case 6:
            		uic.deleteApplicant(null);
            		break;
            	case 7:
            		uic.logout();
            		return;
            }
        }
	}
    
   /**
    * Prints Manager's contact information
    */
	private void printManagerInfo() {
		showMessage("\nManager's contact information:\n" + uic.getManagerContactInfo() + "\n");
	}
	/**
	 * Displays all employee contact information
	 */
	private void displayEmployeeInformation() {
		showMessage("Employee List:\n" + uic.getEmployeeContactInfo());
	}
	private void printApplicantInfo() {
		showMessage(uic.getApplicantInfo());
	}
	
	/**
	 * Displays all employee names and usernames
	 */
	private void displayEmployeeUsernames() {
		showMessage("Employee List:\n" + uic.getEmployeeNamesUsernames());
	}
	/**
	 * For every employee, counts how many people are covering a certain shift
	 * Displays shift coverage in a table
	 */
	private void showShiftsCovered() {
		int[] s = uic.asIntArray(getStringInput("Date: ", false));
		showMessage("            Mon Tue Wed Thu Fri Sat Sun" + 
		"\nShift 1: "+ s[0] + "      " + s[3]+ "      " + s[6]+ "      " + s[9]+ "      " + s[12]+ "      " + s[15]+ "      " + s[17] +
		"\nShift 2: "+ s[1] + "      " + s[4]+ "      " + s[7]+ "      " + s[10]+ "      " + s[13]+ "      " + s[16]+ "      " + s[18] +
		"\nShift 3: "+ s[2] + "      " + s[5]+ "      " + s[8]+ "      " + s[11]+ "      " + s[14]);
		
	}
	/**
	 * Assigns or removes a shift to/from an employee
	 * @param assign true=assign; false=remove
	 */
	private void assignShift(boolean assign) {
		String shiftConstants = "(M): Morning Shift, (A): Afternoon Shift, (E): Evening Shift\n" + 
				"Shift  0:Mon(M)|Shift  1:Mon(A)|Shift  2:Mon(E)|Shift  3:Tue(M)|\n" + 
				"Shift  4:Tue(A)|Shift  5:Tue(E)|Shift  6:Wed(M)|Shift  7:Wed(A)|\n" + 
				"Shift  8:Wed(E)|Shift  9:Thu(M)|Shift 10:Thu(A)|Shift 11:Thu(E)|\n" + 
				"Shift 12:Fri(M)|Shift 13:Fri(A)|Shift 14:Fri(E)|Shift 15:Sat(A)|\n" + 
				"Shift 16:Sat(E)|Shift 17:Sun(A)|Shift 18:Sun(E)|\n\n";
		
		String username = getStringInput("Employees Username: ", false);
		int shiftNumber = getIntInput(shiftConstants + "Shift Number: ", 0, 19);
		String date = getStringInput("Date: ", false);
		
		int success = uic.assignShift(username, shiftNumber, assign, date);
		if(success == -1) 
			showMessage("Employee Doesn't Exist");
		else if((success == 0) && assign) 
			showMessage("Employee is already scheduled for this shift.");
		else if((success == 0) && !assign) 
			showMessage("Employee is already not scheduled for this shift.");
		else if((success == 1) && assign)
			showMessage("Employee assigned to shift.");
		else if((success == 1) && !assign)
			showMessage("Employee removed from shift.");
	}
	
	/**
	 * Prints the current user's current schedule
	 */
	private void printSchedule() {
		showMessage(uic.getCurrentUserSchedule(getStringInput("Date: ", false)));
	}
	
	public void updateEmployeeAvailability () {
		String shiftConstants = "(M): Morning Shift, (A): Afternoon Shift, (E): Evening Shift\n" + 
				"Shift  0:Mon(M)|Shift  1:Mon(A)|Shift  2:Mon(E)|Shift  3:Tue(M)|\n" + 
				"Shift  4:Tue(A)|Shift  5:Tue(E)|Shift  6:Wed(M)|Shift  7:Wed(A)|\n" + 
				"Shift  8:Wed(E)|Shift  9:Thu(M)|Shift 10:Thu(A)|Shift 11:Thu(E)|\n" + 
				"Shift 12:Fri(M)|Shift 13:Fri(A)|Shift 14:Fri(E)|Shift 15:Sat(A)|\n" + 
				"Shift 16:Sat(E)|Shift 17:Sun(A)|Shift 18:Sun(E)|\n\n";
		String[] options = {"0) no", "1) yes"};
		
		String date = getStringInput("Date: ", false);
		
		
		for(int i = 0; i < 19; i++){
			uic.assignAvailability(i, getChoiceInput(shiftConstants + "Can you work in shift " + i, options) == 1, date);
		}
	}
	public void updateApplicantAvailability() {
		String shiftConstants = "(M): Morning Shift, (A): Afternoon Shift, (E): Evening Shift\n" + 
				"Shift  0:Mon(M)|Shift  1:Mon(A)|Shift  2:Mon(E)|Shift  3:Tue(M)|\n" + 
				"Shift  4:Tue(A)|Shift  5:Tue(E)|Shift  6:Wed(M)|Shift  7:Wed(A)|\n" + 
				"Shift  8:Wed(E)|Shift  9:Thu(M)|Shift 10:Thu(A)|Shift 11:Thu(E)|\n" + 
				"Shift 12:Fri(M)|Shift 13:Fri(A)|Shift 14:Fri(E)|Shift 15:Sat(A)|\n" + 
				"Shift 16:Sat(E)|Shift 17:Sun(A)|Shift 18:Sun(E)|\n\n";
		String[] options = {"0) no", "1) yes"};
		
		for(int i = 0; i < 19; i++){
			uic.assignAvailability(i, getChoiceInput(shiftConstants + "Can you work in shift ", options) == 1);
		}
	}
	public void updateBasicInfo () {
		uic.updateBasicInfo(getStringInput("Enter Name: ", false), getStringInput("Enter Phone Number: ", false), getStringInput("Enter Email: ", false));
	}
	public void updateResume () {
		LinkedList<String> prevWork = new LinkedList<>();
		LinkedList<String> prevEdu = new LinkedList<>();
		LinkedList<String> skills = new LinkedList<>();
		
		String input;
		while (true) { //get previous work
			input = getStringInput("Please enter your previous workplaces\nPress ok to add or press cancel to continue\nPrevious workplace: ", true);
			if (input == null)
				break;
			prevWork.add(input);
		}
		while (true) { //get previous education
			input = getStringInput("Please enter your previous education\nPress ok to add or press cancel to continue\nPrevious education: ", true);
			if (input == null)
				break;
			prevEdu.add(input);
		}
		while (true) { //get skills
			input = getStringInput("Please enter your skills\nPress ok to add or press cancel to continue\nskills: ", true);
			if (input == null)
				break;
			skills.add(input);
		}
		
		uic.updateResume(new Resume (prevWork, prevEdu, skills));
	}
	
	public int getChoiceInput (String prompt, String[] options) {
		return Integer.parseInt(((String)(JOptionPane.showInputDialog(null, prompt, null, JOptionPane.PLAIN_MESSAGE, null, options, options[0]))).substring(0, 1));
	}
	/**
	 * Used to get a number input from user
	 * @param prompt text shown to user
	 * @return number entered (-1 if not an integer)
	 */
	private int getIntInput(String prompt) {
		try {
			return Integer.parseInt(JOptionPane.showInputDialog(prompt));
		} catch (Exception e) {
			showMessage("invalid input");
			return -1;
		}
	}
	/**
	 * Used to get a number input from user within the given range
	 * @param prompt text shown to user
	 * @return number entered (-1 if not an integer or outside of range)
	 */
	public int getIntInput(String prompt, int min, int max) {
		int value;
		while (true) {
		value = getIntInput(prompt);
		if (value >= min && value <= max) {return value;}
		else if (value != -1)
			showMessage("input outside of range");
		}
	}
	/**
	 * Used to get a string input from user
	 * @param prompt text shown to user
	 * @return string entered
	 */
	public String getStringInput(String prompt, boolean acceptCancel) {
		String string = JOptionPane.showInputDialog(prompt);
		while (string == null && !acceptCancel)
			string = JOptionPane.showInputDialog(prompt);
		return string;
	}
	
	/**
	 * Outputs a message
	 * @param string what to output
	 */
	public void showMessage (String promt) {
		JOptionPane.showMessageDialog(null, promt);
	}
}
