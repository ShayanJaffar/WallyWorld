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
	
	/**
	 * Links Database information to de-serialized objects
	 */
	public void initialize(){
		for(Employee e : employees)
			userMap.put(e.getUsername(), e);
		for(Manager er : managers)
			userMap.put(er.getUsername(), er);
		for(Applicant a : applicants)
			if(!(a instanceof Employee))
				userMap.put(a.getUsername(), a);
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
	public Applicant getApplicant(String username) {
		User user = userMap.get(username);
		if (user instanceof Applicant)
			return (Employee)(user);
		else
			return null;
	}
	
	public Applicant[] getApplicants(){
		Applicant[] list = new Applicant [applicants.size()];
		int i = 0;
		for(Applicant a : applicants){
			list[i] = a;
			i++;
		}
		return list;
	}
	
	public void addUser(User user) {
		if (user instanceof Employee)
			employees.add((Employee)(user));
		else if (user instanceof Applicant)
			applicants.add((Applicant)(user));
		else
			return; //cannot add managers
		userMap.put(user.getUsername(), user);
	}
	public boolean removeUser(String username) {
		User user = getUser(username);
		if (user == null)
			return false;
		else if (user instanceof Employee)
			employees.remove((Employee)(user));
		else if (user instanceof Applicant)
			applicants.remove((Applicant)(user));
		else
			return false; //cannot remove managers
		userMap.remove(username);
		return true;
	}
	public boolean hireApplicant(String username) {
		if(userMap.get(username) == null)
			return false;
		if(!(userMap.get(username) instanceof Employee) && !(userMap.get(username) instanceof Manager)) {
			Applicant curr = (Applicant)(userMap.get(username));
			Employee e = new Employee(curr);
			this.applicants.remove(curr);
			this.employees.add(e);
			userMap.remove(username);
			userMap.put(e.getUsername(), e);
			return true;
		}
		return false;
	}
	public boolean rejectApplicant(String username) {
		if(userMap.get(username) == null)
			return false;
		if(!(userMap.get(username) instanceof Employee) && !(userMap.get(username) instanceof Manager)) {
			removeUser(username);
			return true;
		}
		return false;
	}
}
