import java.util.Date;
import java.util.Calendar;
import java.util.Locale;

import com.google.gson.annotations.Expose;

public class WeeklySchedule {
	public static final int NUMBER_OF_SHIFTS = 19;
	private static Calendar calendar = Calendar.getInstance();
	
	@Expose
	//Boolean array of the 19 shifts in the week
	//Displayed differently in CMDLine and GUI
	boolean[] shift = new boolean[NUMBER_OF_SHIFTS];
	@Expose
	Date date = new Date();
	//Stores the first date in the schedule
	
	public WeeklySchedule () {
		this (new Date());
	}
	public WeeklySchedule (Date date) {
		shift = new boolean[NUMBER_OF_SHIFTS];
		this.date = getSunday(date);
	}
	public WeeklySchedule (WeeklySchedule old) {
		shift = new boolean[NUMBER_OF_SHIFTS];
		for (int i = 0; i < NUMBER_OF_SHIFTS; i++) {
			shift[i] = old.shift[i];
		}
		date = (Date)(old.date.clone());
	}
	public WeeklySchedule (WeeklySchedule old, Date date) {
		shift = new boolean[NUMBER_OF_SHIFTS];
		if (old != null) {
			for (int i = 0; i < NUMBER_OF_SHIFTS; i++) {
				shift[i] = old.shift[i];
			}
		}
		this.date = getSunday(date);
	}
	
	public Date getDate() {return date;}
	public boolean[] getShift() {return shift;}
	public boolean getAssigned (int i) {return shift[i];}
	
	public int[] listAsIntArray () {
		int[] array = new int[NUMBER_OF_SHIFTS];
		for (int i = 0; i < NUMBER_OF_SHIFTS; i++) {
			if (shift[i])
				array[i] = 1;
		}
		return array;
	}
	
	public boolean assignShift (int i, boolean value) {
		boolean temp = (value != shift[i]);
		shift[i] = value;
		return temp;
	}
	
	public boolean containsDate (Date date) {
		return (this.date.getTime() <= date.getTime() && date.getTime() <= (this.date.getTime() + 7*86400000));
	}
	
	private String boolToString (int i) {
		if (shift[i])
			return "X";
		else
			return "-";
	}
	public String asString (boolean showDate) {
		calendar.setTime(date);
		String string = "";
		if (showDate)
		string = "\nWeek of " + calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()) + " " + calendar.get(Calendar.DAY_OF_MONTH) + ", " + calendar.get(Calendar.YEAR) + " Shift Schedule:\n";
		return string + "\tMon Tue Wed Thu Fri Sat Sun" + 
				"\nShift 1\t "+ boolToString(0) + "   " + boolToString(3)+ "   " + boolToString(6)+ "   " + boolToString(9)+ "   " + boolToString(12)+ "   " + boolToString(15)+ "   " + boolToString(17) +
				"\nShift 2\t "+ boolToString(1) + "   " + boolToString(4)+ "   " + boolToString(7)+ "   " + boolToString(10)+ "   " + boolToString(13)+ "   " + boolToString(16)+ "   " + boolToString(18) +
				"\nShift 3\t "+ boolToString(2) + "   " + boolToString(5)+ "   " + boolToString(8)+ "   " + boolToString(11)+ "   " + boolToString(14) + "\n";
	}
	
	public int toInt(){
		int x = 0;
		for(int i =0; i < NUMBER_OF_SHIFTS; i++){
			if(shift[i])
				x++;
		}
		return x;
	}
	
	public Date getSunday (Date date) {
		//return first day of week at 0:00:00
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMinimum(Calendar.DAY_OF_WEEK));
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		return new Date(calendar.getTimeInMillis());
	}
}
