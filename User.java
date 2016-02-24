
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
	
	String getName(){
		return name;
	}
	
	void setName(String n){
		name = n;
	}
	
	String getEmail(){
		return email;
	}
	
	void setEmail(String e){
		email = e;
	}
	
	String getPhone(){
		return phone;
	}
	
	void setPhone(String p){
		phone = p;
	}
	
	String getUsername(){
		return username;
	}
	
	void setUsername(String u){
		username = u;
	}
}
