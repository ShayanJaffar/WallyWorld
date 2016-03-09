import com.google.gson.annotations.Expose;

public abstract class User {
	//All information pertaining to all users
	@Expose
	String name;
	@Expose
	String email;
	@Expose
	String phone;
	@Expose
	String username;
	
	public User () {};
	public User (User old) {
		name = old.name;
		email = old.email;
		phone = old.phone;
		username = old.username;
	}
	
	public String getName () {return name;}
	public String getEmail () {return email;}
	public String getPhone () {return phone;}
	public String getUsername () {return username;}
	
	public void setName (String n) {name = n;}
	public void setEmail (String e) {email = e;}
	public void setPhone (String p) {phone = p;}
	public void setUsername (String u) {username = u;}
	
	public String contactInfo () {
		return "\tName: " + name + "\n\tEmail: " + email + "\n\tPhone: " + phone;
	}
}
