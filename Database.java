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
	 * @return the user
	 */
	public User getUser(String username) {
		return userMap.get(username);
	}
	/**
	 * returns the employer
	 * @return employer
	 */
	public Employer getEmployer() {
		return employers.getFirst();
	}
	/**
	 * Returns list of all employees
	 * @return list of employees
	 */
	public LinkedList<Employee> getEmployees() {
		return employees;
	}
	/**
	 * Returns a certain Employee
	 * @param uN username of desired employee
	 * @return employee with username uN
	 */
	public Employee getEmployee(String uN) {
		User u = userMap.get(uN);
		if(u instanceof Employee)
			return ((Employee)u);
		return null;
	}

}
