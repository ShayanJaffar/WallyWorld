import com.google.gson.annotations.Expose;

public class Employee extends Applicant {
	@Expose
	private Schedule schedule;
	@Expose
	private int hourlyRate;

	public Employee() {
	}

	public Employee(Applicant applicant) {
		super(applicant);
		setHourlyRate(7);
		schedule = new Schedule();
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule sched) {
		schedule = sched;
	}
	public int[] shiftAsIntArray() {
		return schedule.shiftAsIntArray();
	}

	public boolean assignShift(int i, boolean value) {
		return schedule.assignShift(i, value);
	}

	public String scheduleString() {
		return schedule.toString();
	}
	
	public int getHourlyRate(){
		return hourlyRate;
	}
	
	public void setHourlyRate(int rate){
		hourlyRate = rate;
	}
}
