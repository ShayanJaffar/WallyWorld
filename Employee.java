import com.google.gson.annotations.Expose;

public class Employee extends User{
	private Schedule schedule;
	@Expose
	int scheduleID;
	

	public void setSchedule(Schedule sched) {
		schedule = sched;
	}
	public Schedule getSchedule() {
		return schedule;
	}
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
}
