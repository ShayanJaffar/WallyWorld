import com.google.gson.annotations.Expose;
//Used to keep dates
public class DateStamp {
	@Expose
	String month;
	@Expose
	int day;
	@Expose
	int year;
	
	public String getMonth() {
		return month;
	}
	public int getDay() {
		return day;
	}
	public int getYear() {
		return year;
	}
	
	public boolean equals (DateStamp other) {
		if (month == other.month && day == other.day && year == other.year) {
			return true;
		}
		else {return false;}
	}
	
	public String toString () {
		return "\nWeek of " + month + " " + day + ", " + year;
	}
}

