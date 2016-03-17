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
	LinkedList<Applicant> applicants = new LinkedList<>();
	
	HashMap<String, User> userMap = new HashMap<String,User>();
	HashMap<String, Applicant> applicantMap = new HashMap<>();
	
	/**
	 * Links Database information to de-serialized objects
	 */
	public void initialize(){
		for(Employee e : employees){
			userMap.put(e.getUsername(),e);
		}
		for(Manager er : managers)
			userMap.put(er.getUsername(),er);
		for(Applicant a : applicants)
			if(!(a instanceof Employee))
				applicantMap.put(a.getUsername(), a);
				
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
	 * returns the employee with the specified username
	 * @param username of the desired user
	 * @return the employee
	 */
	public Employee getEmployee(String username) {
		//important question: will this properly call the subtype clone not the user clone
		User user = userMap.get(username);
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
			list[i] = e;
			i++;
		}
		return list;
	}
	/**
	 * returns the manager
	 * @return the manager
	 */
	public Manager getManager() {
		return managers.getFirst();
	}
	public void addApp(Applicant applicant) {
		applicants.add(applicant);
		applicantMap.put(applicant.getUsername(), applicant);
	}
	public Applicant getApplicant(String inputUN) {
		return applicantMap.get(inputUN);
	}
	public void removeApp(String username) {
		applicantMap.remove(username);
	}
	public void updateAppAva(String username, int i, int x) {
		Applicant a = getEmployee(username);
		if(a == null)
			a = getApplicant(username);
		a.getAvailability().assignShift(i, x == 0 ? false : true);
		
	}
}
