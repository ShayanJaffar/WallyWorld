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
				currentUser = database.getUser(cmd.getUsername(), "");
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
		return database.assignShift(username, shift, value);
	}
	
	//Basic functions to navigate UI and access information held in the database
	public String getCurrentUserSchedule () {
		return ((Employee)(currentUser)).schedule();
	}
	public String getEmployerContactInfo() {
		return database.getEmployerContactInfo();
	}
	public String getEmployeeContactInfo () {
		return database.getEmployeeContactInfo();
	}
	public String getEmployeeNamesUsernames () {
		return database.getEmployeeNamesUsernames();
	}
	public int[] mostRecentAsIntArray () {
		return database.mostRecentAsIntArray();
	}
	//End of basic functions
}
