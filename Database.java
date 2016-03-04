import java.util.HashMap;
import java.util.LinkedList;
import com.google.gson.annotations.Expose;
import java.util.Map;

public class Database {
	//Data Collections
	@Expose
	LinkedList<Employee> employees = new LinkedList<>();
	@Expose
	LinkedList<Manager> managers = new LinkedList<>();
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
		for(Manager er : managers)
			userMap.put(er.getUsername(),er);
	}
	/**
	 * returns the user with the specified username
	 * @param username of the desired user
	 * @return the user
	 */
	public User getUser(String username) {
		return userMap.get(username).clone();
	}
	/**
	 * returns the employee with the specified username
	 * @param username of the desired user
	 * @return the employee
	 */
	public Employee getEmployee(String username) {
		//important question: will this properly call the subtype clone not the user clone
		User user = userMap.get(username).clone();
		if (user instanceof Employee)
			return (Employee)(user);
		else
			return null;
	}
	/**
	 * Returns all employees
	 * @return list of employees
	 */
	public Employee[] getEmployees () {
		Employee[] list = new Employee [employees.size()];
		int i = 0;
		for(Employee e : employees) {
			list[i] = e.clone();
			i++;
		}
		return list;
	}
	/**
	 * returns the manager
	 * @return the manager
	 */
	public Manager getManager() {
		return managers.getFirst().clone();
	}
	public boolean updateUser (User u) {
		User old = userMap.get(u.getUsername());
		if (old != null)
			return old.update(u);
		else
			return false;
	}
}
