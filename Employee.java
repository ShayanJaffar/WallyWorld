import com.google.gson.annotations.Expose;

public class Employee extends User {
    public static final int DEFAULT_HOURLY_RATE = 7;
    
	@Expose
	private Schedule schedule;
	@Expose
	private Schedule availability;
	@Expose
	private int hourlyRate;
	@Expose
	private WeeklySchedule defaultAvailability;
	@Expose
	private Resume resume;
	
	public Employee() {
	}

	public Employee(Applicant applicant, int hourlyRate) {
		super(applicant);
		schedule = new Schedule();
		availability = new Schedule();
		setHourlyRate(hourlyRate);
		defaultAvailability = applicant.getAvailability();
		resume = applicant.getResume();
	}

	public Schedule getSchedule() {
		return schedule;
	}
	public Schedule getAvailability() {
		return availability;
	}
	public Resume getResume() {
		return resume;
	}
	
	public void setSchedule(Schedule sched) {
		schedule = sched;
	}
	public void setAvailability(Schedule availability) {
		this.availability = availability;
	}
	public void setResume(Resume resume) {
		this.resume = resume;
	}
	
	public int[] shiftAsIntArray() {
		return schedule.shiftAsIntArray();
	}

	public boolean assignShift(int i, boolean value) {
		return schedule.assignShift(i, value);
	}
	public boolean assignAvailability (int i, boolean value) {
		return availability.assignShift(i, value);
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
