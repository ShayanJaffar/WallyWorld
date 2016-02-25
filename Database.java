import java.util.HashMap;
import java.util.LinkedList;
import com.google.gson.annotations.Expose;
import java.util.Map;

public class Database {
	//Data Collections
	@Expose
	LinkedList<Employee> employees = new LinkedList<>();
	@Expose
	LinkedList<Employer> employers = new LinkedList<>();
	@Expose
	LinkedList<Schedule> schedules = new LinkedList<>();
	
	HashMap<String, User> userMap = new HashMap<String,User>();
	HashMap<Integer, Schedule> scheduleMap = new HashMap<Integer, Schedule>();
	
	/**
	 * Links Database information to de-serialized objects
	 */
	public void initialize(){
		for(Schedule s : schedules)
			scheduleMap.put(s.getScheduleID(), s);
		for(Employee e : employees){
			e.setSchedule(scheduleMap.get(e.getScheduleID()));
			userMap.put(e.getUsername(),e);
		}
		for(Employer er : employers)
			userMap.put(er.getUsername(),er);
	}
	/**
	 * returns the user with the specified username
	 * @param username of the desired user
	 * @param password of the desired user
	 * @return the user
	 */
	public User getUser(String username, String password) {
		return userMap.get(username);
	}
	/**
	 * returns the employer's contact info
	 * @return String
	 */
	public String getEmployerContactInfo() {
		return employers.getFirst().contactInfo();
	}
	/**
	 * Returns an employees contact info
	 * @param uN username
	 * @return String
	 */
	public String getEmployeeContactInfo(String uN) {
		User u = userMap.get(uN);
		if(u instanceof Employee)
			return u.contactInfo();
		return "No employee has that username";
	}
	/**
	 * Returns all employees contact info
	 * @return String
	 */
	public String getEmployeeContactInfo () {
		String string = "";
		for(Employee e : employees)
			string += e.contactInfo() + "\n\n";
		return string;
	}
	/**
	 * Returns all employees names and usernames (\tname: username\n)
	 * @return String
	 */
	public String getEmployeeNamesUsernames () {
		String string = "";
		for(Employee e : employees)
			string += "\t" + e.getName() + ": " + e.getUsername() + "\n";
		return string;
	}
	/**
	 * returns true if employee with username exists, false otherwise
	 * @param username
	 * @return
	 */
	public boolean doesEmployeeExist (String username) {
		return (userMap.get(username) instanceof Employee);
		//User u = userMap.get(username);
		//return (u instanceof Employee);
	}
	/**
	 * returns and integer array for the total number of people covering the current shift
	 * @return
	 */
	public int[] mostRecentAsIntArray () {
		int[] array = new int[19];
		int[] eArray = null;
		for(Employee e : employees) {
			eArray = e.shiftAsIntArray();
			for (int i = 0; i < 19; i++)
				if (eArray != null)
					array[i] += eArray[i];
		}
		return array;
	}
	/**
	 * assigns/removes an employee to/from a shift
	 * @param username
	 * @param shift
	 * @param value
	 * @return 1 if successful, 0 if already assigned/not assigned, -1 if no user with that username
	 */
	public int assignShift (String username, int shift, boolean value) {
		User u = userMap.get(username);
		if (u instanceof Employee) {
			if (((Employee) u).assignShift(shift, value))
				return 1;
			else
				return 0;
		}
		else
			return -1;
	}
}
