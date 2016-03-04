import java.util.*;

import com.google.gson.annotations.Expose;
public class Schedule {
	//Linked List to store all the weekly schedules of a single employee
	@Expose
	public LinkedList<WeeklySchedule> shifts = new LinkedList<WeeklySchedule>();
	@Expose
	int scheduleID;
	
	private Schedule (Schedule old) {
		for (WeeklySchedule ws : old.shifts) {
			shifts.add(ws.clone());
		}
		scheduleID = old.scheduleID;
	}

	public int getScheduleID() {
		return scheduleID;
	}
	public WeeklySchedule getNewestShift() {
		return shifts.getLast();
	}
	
	public int[] shiftAsIntArray () {return shifts.getLast().listAsIntArray();}
	public boolean assignShift (int i, boolean value) {
		return shifts.getLast().assignShift(i, value);
	}
	
	public String toString () {return shifts.getLast().toString();}
	
	public Schedule clone () {
		return new Schedule(this);
	}
}
