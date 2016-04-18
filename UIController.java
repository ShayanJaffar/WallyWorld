import gui.ScheduleWindow;
import gui.welcomeMenu;

public class UIController {
	public static final int COMMAND_LINE_MODE = 0;
	public static final int GUI_MODE = 1;
	
	private Database database;
	private User currentUser = null;
	private CMDLine cmd;
	private JOPGUI gui;
    private welcomeMenu wcm;
	
	private int mode = COMMAND_LINE_MODE;
	
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
					cmd.employeeMainMenu();
				else if (currentUser instanceof Manager) 
					cmd.managerMainMenu();
				else 
					cmd.applicantMainMenu();
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
        
        //MUQTADAA CHANGED STUFF HERE 4/17/16
	private void login() {
		while (currentUser == null) {
			String inputUN = "";
			String inputPW = "";
			if (mode == COMMAND_LINE_MODE) {
				inputUN = cmd.getStringInput("Enter Username: ");
				inputPW = cmd.getStringInput("Enter Password: ");
			}
			else if (mode == GUI_MODE)
				inputUN = gui.getStringInput("Enter Username: ", false);
				inputPW = gui.getStringInput("Enter Password: ", false);
			
			User user = database.getUser(inputUN);
			if (user.passwordIs(inputPW))
				currentUser = user;
			
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
        
        //MUQTADAA CHANGED STUFF HERE 4/17/16
	private int welcomeOption () {
		if (mode == COMMAND_LINE_MODE) {
			return cmd.welcomeOption();
		}
		else if (mode == GUI_MODE) {
			return gui.welcomeOption();
		}
		else {
			wcm = new welcomeMenu();
			return 1;
		}
	}
	/**
	 * Allows the user to switch to a different ui mode
	 */
	private void inputModeSwitch () {
		int temp = -1;
		if (mode == COMMAND_LINE_MODE) 
			temp = cmd.getModeSwitch();	
		if (mode == GUI_MODE) 
			temp = gui.getModeSwitch();	
		if (temp != -1) 
			mode = temp;
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
		database.addUser(app);
		Controller.save(database);
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
	public int assignAvailability (int shift, boolean value) {
		//only need to update if shift is changed
		if (currentUser instanceof Applicant && ((Applicant)(currentUser)).assignAvailability(shift, value))
			return 1;
		else if (currentUser instanceof Employee && ((Employee)(currentUser)).assignAvailability(shift, value))
			return 1;
		else
			return 0;
	}
	
	//Basic functions to navigate UI and access information held in the database
	public String getApplicantInfo () {
		return ((Applicant)(currentUser)).info();
	}
	public String getCurrentUserSchedule () {
            if(mode == COMMAND_LINE_MODE || mode == GUI_MODE){
                return ((Employee)(currentUser)).scheduleString();
            }
            else{
                ScheduleWindow sw = new ScheduleWindow();
                sw.createAndShowGui();
                return ((Employee)(currentUser)).scheduleString();
            }
	}
	public WeeklySchedule getCurrentUserAvailability(){
		return ((Employee)(currentUser)).getAvailability().getShift();
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
	public String getApplications() {
		Applicant[] a = database.getApplicants();
		String string = "";
		for(int i = 0; i < a.length;i++)
			string += a[i].info();
		return string;
		
	}
	public int[] mostRecentAsIntArray () {
		int[] total = new int[WeeklySchedule.NUMBER_OF_SHIFTS];
		for (Employee e: database.getEmployees()) {
			int[] shift = e.shiftAsIntArray();
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
			+ e[i].getHourlyRate()*e[i].getSchedule().getShift().toInt()
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

}
