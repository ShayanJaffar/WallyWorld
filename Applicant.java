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
	
	public Applicant () {}
	//we need to make this consistent
	public Applicant(String u, String p) {
		setUsername(u);
		//this.password = p;
		availability = new WeeklySchedule();
		resume = new Resume();
	}
	//we need to make this consistent
	public Applicant(Applicant old) {
		setName(old.getEmail());
		setEmail(old.getEmail());
		setPhone(old.getPhone());
		this.resume = old.getResume();
		setUsername(old.getUsername());
		this.availability = old.getAvailability();
		//this.password = old.password;
	}
	
	public String info () {
		return contactInfo() + "\nAvailability\n" + availability.toAvaString() + "\nResume\n" + resume;
	}
	
	public boolean assignAvailability (int i, boolean value) {
		return availability.assignShift(i, value);
	}
}
