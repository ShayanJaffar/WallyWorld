import java.util.LinkedList;
import java.util.Scanner;
//testing 1, 2, 3
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
	private void employeeMainMenu() {
        while (true) {
        	print("  1) Show Schedule\n"
                + "  2) Show Employer Contact Info\n"
                + "  3) Logout\n ");
        	int choice = getIntInput("\nOption: ");
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
		Employer er = uic.getEmployer(); 
		System.out.println("\nEmployer's contact information:"
				+ "\n\tName: " + er.getName()
				+ "\n\tEmail: " + er.getEmail()
				+ "\n\tPhone: " + er.getPhone() + "\n");
	}

	/**
	 * Prints the current user's current schedule
	 */
	private void printSchedule() {
		Employee e = (Employee)uic.getCurrentUser();
		WeeklySchedule wS = e.getSchedule().getNewestShift();
		System.out.println(printDateStamp(wS.getdateStamp()) + printWScheduleTable(wS));
	}

	/**
	 * Returns a formated table of a schedule
	 * @param wS specified weekly schedule to be printed
	 * @return formated table
	 */
	private String printWScheduleTable(WeeklySchedule wS) {
		boolean[] s = wS.getShift();
		return "\n\tMon Tue Wed Thu Fri Sat Sun" + 
		"\nShift 1\t "+ displayBool(s[0]) + "   " + displayBool(s[3])+ "   " + displayBool(s[6])+ "   " + displayBool(s[9])+ "   " + displayBool(s[12])+ "   " + displayBool(s[15])+ "   " + displayBool(s[17]) +
		"\nShift 2\t "+ displayBool(s[1]) + "   " + displayBool(s[4])+ "   " + displayBool(s[7])+ "   " + displayBool(s[10])+ "   " + displayBool(s[13])+ "   " + displayBool(s[16])+ "   " + displayBool(s[18]) +
		"\nShift 3\t "+ displayBool(s[2]) + "   " + displayBool(s[5])+ "   " + displayBool(s[8])+ "   " + displayBool(s[11])+ "   " + displayBool(s[14]) + "\n";
	}
	
	/**
	 * print value for a boolean value
	 * @param b boolean value
	 * @return corresponding string for the boolean
	 */
	private String displayBool(boolean b) {
		if(b)
			return "X";
		return "-";
	}

	/**
	 * formats the date a schedule was issued
	 * @param dS 
	 * @return
	 */
	private String printDateStamp(DateStamp dS) {
		return "\nWeek of " + dS.getMonth()+ " " + dS.getDay() + ", " 
	+ dS.getYear() + " Shift Schedule:";
	}

	/**
	 * Accessed if user is an employer
	 * Gets Employer input and accesses functions accordingly
	 * 
	 */
	private void employerMainMenu() {
  
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
		int[] s = new int[19];
		LinkedList<Employee> emps = uic.getEmployeeList();
		for(Employee e : emps) {
			boolean[] shift = e.getSchedule().getNewestShift().getShift();
			if(shift != null) {
				for(int i = 0; i < 19; i++) {
					if(shift[i])
						s[i]++;
				}
			}
		}
		System.out.println("\n\tMon Tue Wed Thu Fri Sat Sun" + 
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
		System.out.println("\n");
		
	}

	
	/**
	 * Assigns or removes a shift to/from an employee
	 * @param assign true=assign; false=remove
	 */
	private void assignShift(boolean assign) {
		printShiftConstants();
		String username = getStringInput("Employees Username: ");
		int shiftNumber = getIntInput("Shift Number: ");
		Employee e = uic.getEmployee(username);
		print("");
		if(e == null) 
			print("Employee Doesn't Exist");
		else if(shiftNumber < 0 || shiftNumber > 19) 
			print("Invalid Shift Number");	
		else if(e.getSchedule().getNewestShift().getShift()[shiftNumber] && assign) 
			print("Employee is already scheduled for this shift.");	
		else if(!e.getSchedule().getNewestShift().getShift()[shiftNumber] && !assign) 
			print("Employee is already not scheduled for this shift.");	
		else if(e.getSchedule().getNewestShift().getShift()[shiftNumber] && !assign) {
			e.getSchedule().getNewestShift().getShift()[shiftNumber] = false;
			print("Employee removed from shift.");	
		}
		else {
			e.getSchedule().getNewestShift().getShift()[shiftNumber] = true;
			print("Employee assigned to shift.");
		}
	}

	/**
	 * Displays all employee names and usernames
	 */
	private void displayEmployeeUsernames() {
		LinkedList<Employee> employees = uic.getEmployeeList();
		print("Employee List:");
		for(Employee e : employees)
			print("\t" + e.getName() + ": " + e.getUsername());	
		print("");
	}
	
	/**
	 * Displays all employee contact information
	 */
	private void displayEmployeeInformation() {
		LinkedList<Employee> employees = uic.getEmployeeList();
		print("Employee List:");
		for(Employee e : employees)
			print("\t" + e.getName() + ": " + e.getEmail() + ", " + e.getPhone());	
		print("");
	}
	
	/**
	 * Used to get a number input from user
	 * @param prompt text shown to user
	 * @return number entered
	 */
	private int getIntInput(String prompt) {
			System.out.print(prompt);
			try {
				return input.nextInt();
			} catch (Exception e) {
				input.next();
				return -1;
			}
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
	private void print(String string) {
		System.out.println(string);
	}
	
	/**
	 * Beginning of program
	 * User logs in
	 * System decides whether to access employer functions or employee functions
	 */
	public void startTerminal() {
		print("Welcome to WallyWorld!");
		while(uic.getCurrentUser() == null) {
			uic.login(getStringInput("\nEnter username: "), "");
			if(uic.getCurrentUser() == null)
				print("invalid username");
		} 
		
		if(uic.getCurrentUser() instanceof Employee)
			employeeMainMenu();
		else
			employerMainMenu();
	}	
}
