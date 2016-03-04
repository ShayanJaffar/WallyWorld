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
		showWelcome();
		//allowModeSwitch();
		login();
		if (currentUser instanceof Employee) {
			cmd.employeeMainMenu();
		}
		else if (currentUser instanceof Employer) {
			cmd.employerMainMenu();
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
	private void showWelcome () {
		if (mode == COMMAND_LINE_MODE) {
			cmd.showWelcome();
		}
	}
	/**
	 * Allows the user to switch to a different ui mode
	 */
	private void allowModeSwitch () {
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
		Controller.endAll(database);		
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
			if (e.assignShift(shift, value) && database.updateUser(e))
				return 1;
			else
				return 0;
		}
		else
			return -1;
	}
	
	//Basic functions to navigate UI and access information held in the database
	public String getCurrentUserSchedule () {
		return ((Employee)(currentUser)).schedule();
	}
	public String getEmployerContactInfo() {
		return database.getEmployer().contactInfo();
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
