import java.util.LinkedList;
import java.util.Scanner;

public class CMDLine {
	public static Scanner input = new Scanner(System.in);
	UIController uic;
    
	/**
	 * Creates a new Command Line Interface 
	 * @param uiController link to the UI Controller
	 */
	public CMDLine(UIController uiController) {
		uic = uiController;
	}
	
	/**
	 * prints the welcome text
	 */
	public int welcomeOption () {
		print("\n*****************************************\n"
				+ "Welcome to WallyWorld!\n"
				+ "What would you like to do?\n"
				+ "  1) Login\n"
				+ "  2) Create an account\n"
				+ "  3) Switch input mode\n"
				+ "  4) Quit\n"
				+ "  5) View Usernames/Passwords");
		return getIntInput("Option: ", 1, 5);
	}
	/**
	 * allows the user to switch ui mode
	 * @return new mode to switch to (-1 if do not switch)
	 */
	public int getModeSwitch () {
		int value = getIntInput("\nWould you like to switch user interface mode?\n" + 
				UIController.COMMAND_LINE_MODE + " = command line, " + 
				UIController.GUI_MODE + " = gui\n", 0, 1);
		return value;
	}
	public Applicant createNewAccount () {
		String username = getStringInput("Enter Username: ", false);
		String password = getStringInput("Enter Password: ", false);
		print("Created New Applicant Account.\n");
		return new Applicant(username, password);
	}
	
	/**
	 *  Accessed if employee enters the system
	 *  Gets Employee input and accesses functions accordingly
	 * 
	 */
	public void employeeMainMenu() {
        while (true) {
        	print("\n*****************************************\n"
        		+ "  1) Show Schedule\n"
        		+ "  2) View Availability\n"
        		+ "  3) Change Availability\n"
                + "  4) Show Manager Contact Info\n"
                + "  5) Logout ");
        	int choice = getIntInput("Option: ");
            switch(choice) {
            	case 1:
            		printSchedule();
            		break;
            	case 2:
            		System.out.println(uic.getCurrentUserAvailability(getStringInput("Date: ", true)).asString(true));
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
            	default:
            		print("Invalid input");
            		break;
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
        	print("\n*****************************************\n"
        			+ "  1) Display Employee Usernames\n"
        			+ "  2) Show Shifts Covered\n"
                	+ "  3) Assign Shift\n"
                	+ "  4) Remove Shift\n"
                	+ "  5) Display Employee Contact Information\n"
                	+ "  6) View Applicants\n"
                	+ "  7) Hire/Reject Applicants\n"
                	+ "  8) Send Paychecks\n"
                	+ "  9) Logout\n");
        	int choice = getIntInput("Option: ");
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
            	default:
            		print("Invalid input");
            		break;
            }
        }
	}
	private void hireOrRejectApp() {
		viewApplicants();
		System.out.println("Hire:0, Reject:1, Back:2");
		int choice = this.getIntInput("Option: ", 0, 2);
		switch(choice) {
			case 0:
				if(uic.hireApplicant(this.getStringInput("Applicant's Username: ", false)))
					System.out.println("Applicant was hired!");
				else
					System.out.println("Username not found.");
				break;
			case 1:
				if(uic.rejectApplicant(this.getStringInput("Applicant's Username: ", false)))
					System.out.println("Applicant was rejected.");
				else
					System.out.println("Username not found.");
				break;
			default: //case 2
				return;
			
		}
		
	}


	private void generatePaychecks() {
		System.out.println(uic.genPaycheck());
		
	}

	private void viewApplicants() {
		Applicant[] a = uic.getApplicants();
		String string = "";
		for(int i = 0; i < a.length;i++)
			string += a[i].info();
		print(string);
	}

	public void applicantMainMenu() {
		while (true) {
        	print("\n*****************************************\n"
        		+ "  1) View Application\n"
                + "  2) Show Manager Contact Info\n"
                + "  3) Update Basic Information\n"
                + "  4) Update Avability\n"
                + "  5) Update Resume\n"
                + "  6) Delete Application\n"
                + "  7) Logout ");
        	int choice = getIntInput("Option: ");
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
            	default:
            		print("Invalid input");
            		break;
            }
        }
	}
    
   /**
    * Prints Manager's contact information
    */
	private void printManagerInfo() {
		print("\nManager's contact information:\n" + uic.getManagerContactInfo() + "\n");
	}
	/**
	 * Displays all employee contact information
	 */
	private void displayEmployeeInformation() {
		print("Employee List:");
		print(uic.getEmployeeContactInfo());
	}
	private void printApplicantInfo() {
		print("\n" + uic.getApplicantInfo() + "\n");
	}
	
	/**
	 * Displays all employee names and usernames
	 */
	private void displayEmployeeUsernames() {
		print("Employee List:");
		print(uic.getEmployeeNamesUsernames());
	}
	/**
	 * For every employee, counts how many people are covering a certain shift
	 * Displays shift coverage in a table
	 */
	private void showShiftsCovered() {
		int[] s = uic.asIntArray(getStringInput("Date: ", true));
		print("\n\tMon Tue Wed Thu Fri Sat Sun" + 
		"\nShift 1\t "+ s[0] + "   " + s[3]+ "   " + s[6]+ "   " + s[9]+ "   " + s[12]+ "   " + s[15]+ "   " + s[17] +
		"\nShift 2\t "+ s[1] + "   " + s[4]+ "   " + s[7]+ "   " + s[10]+ "   " + s[13]+ "   " + s[16]+ "   " + s[18] +
		"\nShift 3\t "+ s[2] + "   " + s[5]+ "   " + s[8]+ "   " + s[11]+ "   " + s[14] + "\n");
		
	}
	/**
	 * Assigns or removes a shift to/from an employee
	 * @param assign true=assign; false=remove
	 */
	private void assignShift(boolean assign) {
		printShiftConstants();
		String username = getStringInput("Employees Username: ", false);
		int shiftNumber = getIntInput("Shift Number: ", 0, 19);
		String date = getStringInput("Date: ", true);
		int success = uic.assignShift(username, shiftNumber, assign, date);
		print("");
		if(success == -1) 
			print("Employee Doesn't Exist");
		else if((success == 0) && assign) 
			print("Employee is already scheduled for this shift.");
		else if((success == 0) && !assign) 
			print("Employee is already not scheduled for this shift.");
		else if((success == 1) && assign)
			print("Employee assigned to shift.");
		else if((success == 1) && !assign)
			print("Employee removed from shift.");
	}
	/**
	 * Displays Shift information
	 */
	public void printShiftConstants() {
		print("\n(M): Morning Shift, (A): Afternoon Shift, (E): Evening Shift");
		String[] day = { "Mon(M)", "Mon(A)", "Mon(E)", "Tue(M)", "Tue(A)", "Tue(E)", 
				"Wed(M)", "Wed(A)", "Wed(E)", "Thu(M)", "Thu(A)", "Thu(E)", "Fri(M)",
				"Fri(A)", "Fri(E)", "Sat(A)", "Sat(E)", "Sun(A)", "Sun(E)" };
		for(int i = 0; i < 19; i++) {
			if(i < 10)
				System.out.print("Shift  " + i + ":" + day[i] + "|");
			else
				System.out.print("Shift " + i + ":" + day[i] + "|");
			if((i+1) % 4 == 0)
				System.out.print("\n");
		}
		print("\n");
	}

	/**
	 * Prints the current user's current schedule
	 */
	private void printSchedule() {
		print(uic.getCurrentUserSchedule(getStringInput("Date: ", true)));
	}
	
	public void updateApplicantAvailability() {
		printShiftConstants();
		for(int i = 0; i < 19; i++){
			uic.assignAvailability(i, getIntInput("Can you work in shift " + i + "? (1 = yes, 0 = no)", 0, 1) == 1);
		}
	}
	public void updateEmployeeAvailability() {
		printShiftConstants();
		String date = getStringInput("Date: ", false);
		for(int i = 0; i < 19; i++){
			uic.assignAvailability(i, getIntInput("Can you work in shift " + i + "? (1 = yes, 0 = no)", 0, 1) == 1, date);
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
		print("Please enter your previous workplaces\nType _done to finish");
		while (true) { //get previous work
			input = getStringInput("Previous workplace: ", true);
			if (input.equals("_done") || input.equals(""))
				break;
			prevWork.add(input);
		}
		print("Please enter your previous education\nType _done or leave empty to finish");
		while (true) { //get previous education
			input = getStringInput("Previous education: ", true);
			if (input.equals("_done") || input.equals(""))
				break;
			prevEdu.add(input);
		}
		print("Please enter your skills\nType _done or leave empty to finish");
		while (true) { //get skills
			input = getStringInput("skills: ", true);
			if (input.equals("_done") || input.equals(""))
				break;
			skills.add(input);
		}
		
		uic.updateResume(new Resume (prevWork, prevEdu, skills));
	}
	
	/**
	 * Used to get a number input from user
	 * @param prompt text shown to user
	 * @return number entered (-1 if not an integer)
	 */
	private int getIntInput(String prompt) {
		System.out.print(prompt);
		try {
			String string = input.nextLine();
			return Integer.parseInt(string);
		} catch (Exception e) {
			print("invalid input");
			return -1;
		}
	}
	/**
	 * Used to get a number input from user within the given range
	 * @param prompt text shown to user
	 * @return number entered (-1 if not an integer or outside of range)
	 */
	public int getIntInput(String prompt, int min, int max) {
		int value = getIntInput(prompt);
		if (value >= min && value <= max) {return value;}
		else if (value != -1) {
			print("input outside of range");
		}
		return getIntInput(prompt, min, max);
	}
	/**
	 * Used to get a string input from user
	 * @param prompt text shown to user
	 * @return string entered
	 */
	public String getStringInput(String prompt, boolean acceptEmpty) {
		System.out.print(prompt);
		String str = input.nextLine();
		if(!acceptEmpty && str.length() < 1)
			str = input.nextLine();
		return str;
	}
	
	/**
	 * prints a String
	 * @param string what to print
	 */
	public void print(String string) {
		System.out.println(string);
	}
}
