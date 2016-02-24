import java.util.*;

import com.google.gson.annotations.Expose;
public class Schedule {
	//Linked List to store all the weekly schedules of a single employee
	@Expose
	public LinkedList<WeeklySchedule> shifts = new LinkedList<WeeklySchedule>();
	@Expose
	int scheduleID;

	public int getScheduleID() {
		return scheduleID;
	}
	public WeeklySchedule getNewestShift() {
		return shifts.getLast();
	}
}
