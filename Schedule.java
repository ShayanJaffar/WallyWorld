import java.util.*;

import com.google.gson.annotations.Expose;
public class Schedule {
	//Linked List to store all the weekly schedules of a single employee
	@Expose
	public LinkedList<WeeklySchedule> shifts = new LinkedList<WeeklySchedule>();

	public WeeklySchedule getNewestShift() {
		return shifts.getLast();
	}
	
	public int[] shiftAsIntArray () {
		return shifts.getLast().listAsIntArray();
		}
	public boolean assignShift (int i, boolean value) {
		return shifts.getLast().assignShift(i, value);
	}
	
	public String toString () {
		return shifts.getLast().toString();
		}
}
