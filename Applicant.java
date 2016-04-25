import com.google.gson.annotations.Expose;

public class Applicant extends User {

	@Expose
	private Resume resume;
	@Expose
	private WeeklySchedule availability;
	
	public Resume getResume() {
		return resume;
	}
	public void setResume(Resume resume) {
		this.resume = resume;
	}
	public WeeklySchedule getAvailability() {
		return availability;
	}
	public void setAvailability(WeeklySchedule availability) {
		this.availability = availability;
	}
	
	public Applicant(String u, String p) {
		super (u, p);
		availability = new WeeklySchedule();
		resume = new Resume();
	}
	public Applicant(Applicant old) {
		super(old); 
		this.resume = old.getResume();
		this.availability = old.getAvailability();
	}
	
	public String info () {
		return contactInfo() + "\n\nAvailability\n" + availability.asString(false) + "\nResume\n" + resume;
	}
	
	public boolean assignAvailability (int i, boolean value) {
		return availability.assignShift(i, value);
	}
}
