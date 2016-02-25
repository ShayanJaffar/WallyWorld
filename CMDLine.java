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
	 *  Accessed if employee enters the system
	 *  Gets Employee input and accesses functions accordingly
	 * 
	 */
	public void employeeMainMenu() {
        while (true) {
        	print("  1) Show Schedule\n"
                + "  2) Show Employer Contact Info\n"
                + "  3) Logout ");
        	int choice = getIntInput("Option: ");
            switch(choice) {
            	case 1:
            		printSchedule();
            		break;
            	case 2:
            		printEmployerInfo();
            		break;
            	case 3:
            		logout();
            		break;
            	default:
            		print("Invalid input");
            		break;
            }
        }
	}
	
	/**
	 *  Logs out of the system.
	 */
    private void logout() {
    	print("\nDatabase Saved\nExit Successful");
		uic.logout();
	}
    
   /**
    * Prints Employer's contact information
    */
	private void printEmployerInfo() {
		print("\nEmployer's contact information:\n" + uic.getEmployerContactInfo());
	}

	/**
	 * Prints the current user's current schedule
	 */
	private void printSchedule() {
		print(uic.getCurrentUserSchedule());
	}

	/**
	 * Accessed if user is an employer
	 * Gets Employer input and accesses functions accordingly
	 * 
	 */
	public void employerMainMenu() {
  
        while (true) {
        	print("  1) Display Employee Usernames\n"
        			+ "  2) Show Shifts Covered\n"
                	+ "  3) Assign Shift\n"
                	+ "  4) Remove Shift\n"
                	+ "  5) Display Employee Contact Information\n"
                	+ "  6) Logout\n ");
        	int choice = getIntInput("\nOption: ");
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
            		logout();
            		break;
            	default:
            		print("Invalid input");
            		break;
            }
        }
        
	}
    
	/**
	 * For every employee, counts how many people are covering a certain shift
	 * Displays shift coverage in a table
	 */
	private void showShiftsCovered() {
		int[] s = uic.mostRecentAsIntArray();
		print("\n\tMon Tue Wed Thu Fri Sat Sun" + 
		"\nShift 1\t "+ s[0] + "   " + s[3]+ "   " + s[6]+ "   " + s[9]+ "   " + s[12]+ "   " + s[15]+ "   " + s[17] +
		"\nShift 2\t "+ s[1] + "   " + s[4]+ "   " + s[7]+ "   " + s[10]+ "   " + s[13]+ "   " + s[16]+ "   " + s[18] +
		"\nShift 3\t "+ s[2] + "   " + s[5]+ "   " + s[8]+ "   " + s[11]+ "   " + s[14] + "\n");
		
	}
	/**
	 * Displays Shift information
	 */
	private void printShiftConstants() {
		print("\n(M): Morning Shift, (A): Afternoon Shift, (E): Evening Shift\n");
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
		print("\n\n");
		
	}
	
	/**
	 * Assigns or removes a shift to/from an employee
	 * @param assign true=assign; false=remove
	 */
	private void assignShift(boolean assign) {
		printShiftConstants();
		String username = getStringInput("Employees Username: ");
		int shiftNumber = getIntInput("Shift Number: ", 0, 19);
		int success = uic.assignShift(username, shiftNumber, assign);
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
	 * Displays all employee names and usernames
	 */
	private void displayEmployeeUsernames() {
		print("Employee List:");
		print(uic.getEmployeeNamesUsernames());
	}
	
	/**
	 * Displays all employee contact information
	 */
	private void displayEmployeeInformation() {
		print("Employee List:");
		print(uic.getEmployeeContactInfo());
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
	private int getIntInput(String prompt, int min, int max) {
		int value = getIntInput(prompt);
		if (value >= min && value <= max) {return value;}
		else if (value != -1) {
			print("input outside of range");
			return -1;
		}
		else {return -1;}
	}
	
	/**
	 * Used to get a string input from user
	 * @param prompt text shown to user
	 * @return string entered
	 */
	private String getStringInput(String prompt) {
		System.out.print(prompt);
		String str = input.nextLine();
		if(str.length() < 1)
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
	
	/**
	 * gets a username
	 * @return string username
	 */
	public String getUsername () {
		return getStringInput("Enter username: ");
	}
	/**
	 * gets a password
	 * @return string password
	 */
	public String getPassword () {
		return getStringInput("Enter password: ");
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
	/**
	 * prints the welcome text
	 */
	public void showWelcome () {
		print("Welcome to WallyWorld!");
	}
}
