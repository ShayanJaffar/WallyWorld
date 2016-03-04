
public class Employer extends User{
	public Employer (Employer old) {
		super(old);
	}
	
	public Employer clone () {
		return new Employer (this);
	}
	
	public boolean update (User u) {
		if (u instanceof Employer)
			return true; //same as in employee ((User)(this)).update(u);
		else
			return false;
	}
}
