

import com.google.gson.annotations.Expose;
public class WeeklySchedule {
	@Expose
	//Boolean array of the 19 shifts in the week
	//Displayed differently in CMDLine and GUI
	boolean[] shift = new boolean[19];
	@Expose
	DateStamp dateStamp = new DateStamp();
	//Stores the first date in the schedule
	public DateStamp getdateStamp() {
		return dateStamp;
	}

	public boolean[] getShift() {
		return shift;
	}
}
