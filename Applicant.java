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

	public Applicant () {
		
	}

	public Applicant(String u, String p) {
		setUsername(u);
		//this.password = p;
		availability = new WeeklySchedule();
		resume = new Resume();
	}

	public Applicant(Applicant old) {
		setName(old.getEmail());
		setEmail(old.getEmail());
		setPhone(old.getPhone());
		this.resume = old.getResume();
		setUsername(old.getUsername());
		this.availability = old.getAvailability();
		//this.password = old.password;
	}
}
