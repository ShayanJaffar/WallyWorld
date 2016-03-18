import com.google.gson.annotations.Expose;

public abstract class User {
	//All information pertaining to all users
	@Expose
	private String name;
	@Expose
	private String email;
	@Expose
	private String phone;
	@Expose
	private String username;
	
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
		return "Username: " + username + "\tName: " + name + "\nEmail: " + email + "\tPhone: " + phone;
	}
}
