import java.util.*;
public abstract class User {
	String name;
	String email;
	String phone;
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
