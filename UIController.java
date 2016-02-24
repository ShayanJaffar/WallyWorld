import java.util.LinkedList;

public class UIController {
	private Database database;
	private User currentUser = null;
	private CMDLine cmd;
	
	private boolean cmdMode = true;
	
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
		if(cmdMode)
			cmd.startTerminal();
	}
	/**
	 * Sets the Current User for the system
	 * @param uN entered username of user
	 * @param pW entered password of user
	 */
	public void login(String uN, String pW) {
		setCurrentUser(database.getUser(uN));
	}
	/**
	 * Logs out of the system
	 */
	public void logout() {
		Controller.endAll(database);		
	}
	
	//Basic functions to navigate UI and access information held in the database
	
	public User getCurrentUser() {
		return currentUser;
	}
	public LinkedList<Employee> getEmployeeList() {
		return database.getEmployees();
	}
	private void setCurrentUser(User newUser) {
		currentUser = newUser;
	}
	public Employer getEmployer() {
		return database.getEmployer();
	}
	public Employee getEmployee(String uN) {
		return database.getEmployee(uN);
	}
	//End of basic functions
}
