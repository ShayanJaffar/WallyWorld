

import com.google.gson.annotations.Expose;
public class WeeklySchedule {
	public static final int NUMBER_OF_SHIFTS = 19;
	
	@Expose
	//Boolean array of the 19 shifts in the week
	//Displayed differently in CMDLine and GUI
	boolean[] shift = new boolean[NUMBER_OF_SHIFTS];
	@Expose
	DateStamp dateStamp = new DateStamp();
	//Stores the first date in the schedule
	
	public DateStamp getDateStamp() {return dateStamp;}
	
	public boolean[] getShift() {return shift;}
	
	public boolean isAssigned (int i) {return shift[i];}
	
	public int[] listAsIntArray () {
		int[] array = new int[NUMBER_OF_SHIFTS];
		for (int i = 0; i < NUMBER_OF_SHIFTS; i++) {
			if (shift[i])
				array[i] = 1;
		}
		return array;
	}
	
	private String boolToString (int i) {
		if (shift[i])
			return "X";
		else
			return "-";
	}
	
	public boolean getAssigned (int i) {return shift[i];}
	public boolean assignShift (int i, boolean value) {
		boolean temp = (value != shift[i]);
		shift[i] = value;
		return temp;
	}
	
	public String toString () {
		return dateStamp + " Shift Schedule:\n\tMon Tue Wed Thu Fri Sat Sun" + 
				"\nShift 1\t "+ boolToString(0) + "   " + boolToString(3)+ "   " + boolToString(6)+ "   " + boolToString(9)+ "   " + boolToString(12)+ "   " + boolToString(15)+ "   " + boolToString(17) +
				"\nShift 2\t "+ boolToString(1) + "   " + boolToString(4)+ "   " + boolToString(7)+ "   " + boolToString(10)+ "   " + boolToString(13)+ "   " + boolToString(16)+ "   " + boolToString(18) +
				"\nShift 3\t "+ boolToString(2) + "   " + boolToString(5)+ "   " + boolToString(8)+ "   " + boolToString(11)+ "   " + boolToString(14) + "\n";
	}
	public String toAvaString() {
		return  " Shift Schedule:\n\tMon Tue Wed Thu Fri Sat Sun" + 
				"\nShift 1\t "+ boolToString(0) + "   " + boolToString(3)+ "   " + boolToString(6)+ "   " + boolToString(9)+ "   " + boolToString(12)+ "   " + boolToString(15)+ "   " + boolToString(17) +
				"\nShift 2\t "+ boolToString(1) + "   " + boolToString(4)+ "   " + boolToString(7)+ "   " + boolToString(10)+ "   " + boolToString(13)+ "   " + boolToString(16)+ "   " + boolToString(18) +
				"\nShift 3\t "+ boolToString(2) + "   " + boolToString(5)+ "   " + boolToString(8)+ "   " + boolToString(11)+ "   " + boolToString(14) + "\n";
	}
}
