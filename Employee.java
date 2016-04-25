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
	private WeeklySchedule defaultSchedule = new WeeklySchedule();
	@Expose
	private WeeklySchedule defaultAvailability = new WeeklySchedule();
	@Expose
	private Resume resume;
	
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
	public int getHourlyRate(){
		return hourlyRate;
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
	public void setHourlyRate(int rate){
		hourlyRate = rate;
	}
	
	public int[] shiftAsIntArray(String date) {
		return schedule.shiftAsIntArray(defaultSchedule, date);
	}
	
	public int assignShift(int i, boolean value, String date) {
		return schedule.assignShift(defaultSchedule, i, value, date);
	}
	
	public int assignAvailability (int i, boolean value, String date) {
		return availability.assignShift(defaultAvailability, i, value, date);
	}
	
	public String scheduleString(String date) {
		return schedule.asString(defaultSchedule, date);
	}
}
