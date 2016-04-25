public class UIController {
	public static final int COMMAND_LINE_MODE = 0;
	public static final int GUI_MODE = 1;
	
	private Database database;
	private User currentUser = null;
	private CMDLine cmd;
	private JOPGUI gui;
	
	private int mode = GUI_MODE;
	
	/**
	 * Creates the UI Controller
	 * @param d reference to Database
	 */
	public UIController(Database d) {
		database = d;
		cmd = new CMDLine(this);
		gui = new JOPGUI(this);
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
				if (currentUser instanceof Employee) 
					employeeMainMenu();
				else if (currentUser instanceof Manager) 
					managerMainMenu();
				else 
					applicantMainMenu();
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
			case 5:
				showUNPW();
				break;
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
			String inputUN = "";
			String inputPW = "";
			if (mode == COMMAND_LINE_MODE) {
				inputUN = cmd.getStringInput("Enter Username: ", false);
				inputPW = cmd.getStringInput("Enter Password: ", false);
			}
			else if (mode == GUI_MODE) {
				inputUN = gui.getStringInput("Enter Username: ", false);
				inputPW = gui.getStringInput("Enter Password: ", false);
			}
			
			User user = database.getUser(inputUN);
			if (user != null) {
				if (user.passwordIs(inputPW)) {
					currentUser = user;
				}
			}
			
			if (currentUser == null) {
				if (mode == COMMAND_LINE_MODE)
					cmd.print("Invalid Username/Password\n");
				else if (mode == GUI_MODE)
					gui.showMessage("Invalid Username/Password\n");
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
		else { //(mode == GUI_MODE)
			return gui.welcomeOption();
		}
	}
	/**
	 * Allows the user to switch to a different ui mode
	 */
	private void inputModeSwitch () {
		if (mode == COMMAND_LINE_MODE) 
			mode = cmd.getModeSwitch();	
		else if (mode == GUI_MODE) 
			mode = gui.getModeSwitch();
	}
	/**
	 * Logs out of the system
	 */
	public void logout() {
		currentUser = null;
		Controller.save(database);
		if (mode == COMMAND_LINE_MODE)
			cmd.print("Database Saved\n");
		else if (mode == GUI_MODE)
			gui.showMessage("Database Saved\n");
	}
	public void createNewAccount () {
		Applicant app = null;
		if (mode == COMMAND_LINE_MODE)
			app = cmd.createNewAccount();
		if (mode == GUI_MODE)
			app = gui.createNewAccount();
		database.addUser(app);
		Controller.save(database);
	}

	public void employeeMainMenu () {
		if (mode == COMMAND_LINE_MODE) 
			cmd.employeeMainMenu();	
		if (mode == GUI_MODE) 
			gui.employeeMainMenu();
	}
	public void managerMainMenu() {
		if (mode == COMMAND_LINE_MODE) 
			cmd.managerMainMenu();	
		if (mode == GUI_MODE) 
			gui.managerMainMenu();
	}
	public void applicantMainMenu() {
		if (mode == COMMAND_LINE_MODE) 
			cmd.applicantMainMenu();	
		if (mode == GUI_MODE) 
			gui.applicantMainMenu();
	}
	
	/**
	 * Assigns/removes a user to/from a shift
	 * @param username
	 * @param shift
	 * @param value
	 * @return 1 if successful, 0 if already assigned/not assigned, -1 if no user with that username, -2 if date is bad
	 */
	public int assignShift (String username, int shift, boolean value, String date) {
		Employee e = database.getEmployee(username);
		if (e != null) {
			return e.assignShift(shift, value, date);
		}
		else
			return -1;
	}
	public int assignAvailability (int shift, boolean value) {
		//applicant
		if (((Applicant)(currentUser)).assignAvailability(shift, value))
			return 1;
		else
			return 0;
	}
	public int assignAvailability (int shift, boolean value, String date) {
		//employee
		return ((Employee)(currentUser)).assignAvailability(shift, value, date);
	}
	
	//Basic functions to navigate UI and access information held in the database
	public String getApplicantInfo () {
		return ((Applicant)(currentUser)).info();
	}
	public String getUserSchedule (String username, String date) {
		if (username == null)
			return ((Employee)(currentUser)).scheduleString(date);
		else {
			Employee e = database.getEmployee(username);
			if (e != null)
				return e.scheduleString(date);
			else
				return "Employee does not exist";
		}
	}
	public String getUserAvailability(String username, String date){
		if (username == null)
			return ((Employee)(currentUser)).getAvailability().getShift(date).asString(true);
		else {
			Employee e = database.getEmployee(username);
			if (e != null)
				return e.getAvailability().getShift(date).asString(true);
			else
				return "Employee does not exist";
		}
	}
	public String getManagerContactInfo() {
		return database.getManager().contactInfo();
	}
	public String getEmployeeContactInfo () {
		Employee[] e = database.getEmployees();
		String string = "";
		for (int i = 0; i < e.length; i++)
			string += e[i].contactInfo() + "\n\n";
		return string;
	}
	public String getEmployeeNamesUsernames () {
		Employee[] e = database.getEmployees();
		String string = "";
		for (int i = 0; i < e.length; i++)
			string += e[i].getName() + ": " + e[i].getUsername() + "\n";
		return string;
	}
	public Applicant[] getApplicants() {
		return database.getApplicants();
	}
	public int[] asIntArray (String date) {
		int[] total = new int[WeeklySchedule.NUMBER_OF_SHIFTS];
		for (Employee e: database.getEmployees()) {
			int[] shift = e.shiftAsIntArray(date);
			for (int i = 0; i < total.length; i++)
				total[i] += shift[i];
		}
		return total;
	}
	public void deleteApplicant(String username) {
		if(username == null)
			database.removeUser(currentUser.getUsername());
		database.removeUser(username);
	}
	//End of basic functions
	
	public void updateBasicInfo (String name, String phone, String email) {
		currentUser.setName(name);
		currentUser.setPhone(phone);
		currentUser.setEmail(email);
	}
	public void updateResume (Resume resume) {
		((Applicant)(currentUser)).setResume(resume);
	}
	
	public String genPaycheck() {
		Employee[] e = database.getEmployees();
		String string = "";
		for(int i=0;i < e.length;i++){
			string += e[i].getName() + ": $"
			+ e[i].getHourlyRate()*e[i].getSchedule().getShift("").toInt()
			+ "\n";
		}
		return string;
	}
	
	public boolean hireApplicant(String username) {
		if(database.hireApplicant(username))
			return true;
		return false;
	}
	public boolean rejectApplicant(String username) {
		if(database.rejectApplicant(username))
			return true;
		return false;
	}
	
	public void showUNPW () {
		if (mode == COMMAND_LINE_MODE) {
			for (User u : database.getUsers()) {
				cmd.print(u.getUsername() + ": " + u.getPassword());
			}
		}
		else if (mode == GUI_MODE) {
			String string = "";
			for (User u : database.getUsers()) {
				string += (u.getUsername() + ": " + u.getPassword() + "\n");
			}
			gui.showMessage(string);
		}
	}
}
