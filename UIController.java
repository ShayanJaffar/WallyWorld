public class UIController {
	public static final int COMMAND_LINE_MODE = 0;
	public static final int GUI_MODE = 1;
	
	private Database database;
	private User currentUser = null;
	private CMDLine cmd;
	
	private int mode = COMMAND_LINE_MODE;
	
	/**
	 * Creates the UI Controller
	 * @param d reference to Database
	 */
	public UIController(Database d) {
		database = d;
		cmd = new CMDLine(this);
	}
	/**
	 * Starts the interface
	 */
	public void run () {
		int choice;
		
		while (true) {
			choice = welcomeOption();
			
			switch (choice) {
			case 1:
				login();
				if (currentUser instanceof Employee) {
					cmd.employeeMainMenu();
				}
				else if (currentUser instanceof Manager) {
					cmd.managerMainMenu();
				}
				break;
			case 2:
				createNewAccount();
				break;
			case 3:
				inputModeSwitch();
				break;
			case 4:
				if (mode == COMMAND_LINE_MODE)
					cmd.print("Exit Successful\n");
				return;
			}
		}
	}
	/**
	 * Sets the Current User for the system
	 * @param uN entered username of user
	 * @param pW entered password of user
	 */
	private void login() {
		while (currentUser == null) {
			if (mode == COMMAND_LINE_MODE) {
				currentUser = database.getUser(cmd.getUsername());
			}
		}
	}
	/**
	 * Shows the welcome text/window/etc
	 */
	private int welcomeOption () {
		if (mode == COMMAND_LINE_MODE) {
			return cmd.welcomeOption();
		}
		else
			return 4;
	}
	/**
	 * Allows the user to switch to a different ui mode
	 */
	private void inputModeSwitch () {
		int temp = -1;
		if (mode == COMMAND_LINE_MODE) {
			temp = cmd.getModeSwitch();
		}
		
		if (temp != -1) {mode = temp;}
	}
	/**
	 * Logs out of the system
	 */
	public void logout() {
		currentUser = null;
		Controller.save(database);
		if (mode == COMMAND_LINE_MODE)
			cmd.print("Database Saved\n");
		//Controller.endAll(database);		
	}
	/**
	 * Assigns/removes a user to/from a shift
	 * @param username
	 * @param shift
	 * @param value
	 * @return 1 if successful, 0 if already assigned/not assigned, -1 if no user with that username
	 */
	public int assignShift (String username, int shift, boolean value) {
		Employee e = database.getEmployee(username);
		if (e != null) {
			//only need to update if shift is changed
			if (e.assignShift(shift, value))
				return 1;
			else
				return 0;
		}
		else
			return -1;
	}
	public void createNewAccount () {
		String name = "";
		String username = cmd.getUsername();
		String password = cmd.getPassword();
		String phone = "";
		
		
	}
	
	//Basic functions to navigate UI and access information held in the database
	public String getCurrentUserSchedule () {
		return ((Employee)(currentUser)).schedule();
	}
	public String getManagerContactInfo() {
		return database.getManager().contactInfo();
	}
	public String getEmployeeContactInfo () {
		Employee[] e = database.getEmployees();
		String string = "";
		for (int i = 0; i < e.length; i++)
			string += e[i].contactInfo() + "\n";
		return string;
	}
	public String getEmployeeNamesUsernames () {
		Employee[] e = database.getEmployees();
		String string = "";
		for (int i = 0; i < e.length; i++)
			string += e[i].getName() + ": " + e[i].getUsername() + "\n";
		return string;
	}
	public int[] mostRecentAsIntArray () {
		Employee[] e = database.getEmployees();
		int[] total = new int[WeeklySchedule.NUMBER_OF_SHIFTS];
		for (int i = 0; i < e.length; i++) {
			int[] shift = e[i].shiftAsIntArray();
			for (int j = 0; j < total.length; j++)
				total[j] += shift[j];
		}
		return total;
	}
	//End of basic functions
}
