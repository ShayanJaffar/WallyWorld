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
}

