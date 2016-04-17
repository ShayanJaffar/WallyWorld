import java.util.Date;

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
	private WeeklySchedule defaultSchedule;
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
	
	public int[] shiftAsIntArray() {
		return schedule.shiftAsIntArray(defaultSchedule);
	}
	public int[] shiftAsIntArray(int offset) {
		return schedule.shiftAsIntArray(defaultSchedule, offset);
	}
	public int[] shiftAsIntArray(Date date) {
		return schedule.shiftAsIntArray(defaultSchedule, date);
	}
	
	public boolean assignShift(int i, boolean value) {
		return schedule.assignShift(defaultSchedule, i, value);
	}
	public boolean assignShift(int i, boolean value, int offset) {
		return schedule.assignShift(defaultSchedule, i, value, offset);
	}
	public boolean assignShift(int i, boolean value, Date date) {
		return schedule.assignShift(defaultSchedule, i, value, date);
	}
	
	public boolean assignAvailability (int i, boolean value) {
		return availability.assignShift(defaultAvailability, i, value);
	}
	public boolean assignAvailability (int i, boolean value, int offset) {
		return availability.assignShift(defaultAvailability, i, value, offset);
	}
	public boolean assignAvailability (int i, boolean value, Date date) {
		return availability.assignShift(defaultAvailability, i, value, date);
	}

	public String scheduleString() {
		String string = schedule.toString();
		if (string != null)
			return string;
		else
			return defaultSchedule.toString();
	}
}
