
public class Manager extends User{
	public Manager (Manager old) {
		super(old);
	}
	
	public Manager clone () {
		return new Manager (this);
	}
	
	public boolean update (User u) {
		if (u instanceof Manager)
			return true; //same as in employee ((User)(this)).update(u);
		else
			return false;
	}
}
