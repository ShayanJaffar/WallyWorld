import com.google.gson.annotations.Expose;

public class Employee extends User{
	private Schedule schedule;
	@Expose
	int scheduleID;
	
	private Employee (Employee old) {
		super(old);
		schedule = old.schedule.clone();
		scheduleID = old.scheduleID;
	}
	
	public Schedule getSchedule() {return schedule;}
	public void setSchedule(Schedule sched) {schedule = sched;}
	
	public Object getScheduleID() {
		return scheduleID;
	}
	public int[] shiftAsIntArray () {return schedule.shiftAsIntArray();}
	public boolean assignShift (int i, boolean value) {
		return schedule.assignShift(i, value);
	}
	public String schedule () {
		return schedule.toString();
	}
	
	public Employee clone () {
		return new Employee(this);
	}
	public boolean update (User u) {
		if (u instanceof Employee) {
			//for now don't update the name, etc when updating (update only called from assignShift right now)
			//((User)(this)).update(u);
			schedule = ((Employee)(u)).schedule.clone();
			//scheduleID should stay the same
			return true;
		}
		else
			return false;
	};
}
