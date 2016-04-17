import java.util.LinkedList;
import java.util.Date;

import com.google.gson.annotations.Expose;

public class Schedule {
	//Linked List to store all the weekly schedules of a single employee
	@Expose
	public LinkedList<WeeklySchedule> shifts = new LinkedList<WeeklySchedule>();
	
	public Schedule() {}
	
	public WeeklySchedule getShift() {
		return getShift(new Date());
	}
	public WeeklySchedule getShift(int offset) {
		Date date = new Date();
		date.setTime(date.getTime() + offset*7*86400000);
		return getShift(date);
	}
	public WeeklySchedule getShift (Date date) {
		for (WeeklySchedule weeklySchedule : shifts) {
			if (weeklySchedule.containsDate(date))
				return weeklySchedule;
		}
		//weekly schedule for that date does not exist yet
		WeeklySchedule weeklySchedule = new WeeklySchedule(date);
		shifts.add(weeklySchedule);
		return weeklySchedule;
	}
	
	public int[] shiftAsIntArray (WeeklySchedule defaultSchedule) {
		return shiftAsIntArray(defaultSchedule, new Date());
	}
	public int[] shiftAsIntArray (WeeklySchedule defaultSchedule, int offset) {
		Date date = new Date();
		date.setTime(date.getTime() + offset*7*86400000);
		return shiftAsIntArray(defaultSchedule, date);
	}
	public int[] shiftAsIntArray (WeeklySchedule defaultSchedule, Date date) {
		for (WeeklySchedule weeklySchedule : shifts) {
			if (weeklySchedule.containsDate(date))
				return weeklySchedule.listAsIntArray();
		}
		//weekly schedule for that date does not exist yet
		WeeklySchedule weeklySchedule = new WeeklySchedule(defaultSchedule, date);
		shifts.add(weeklySchedule);
		return weeklySchedule.listAsIntArray();
	}
	
	public boolean assignShift (WeeklySchedule defaultSchedule, int i, boolean value) {
		return assignShift(defaultSchedule, i, value, new Date());
	}
	public boolean assignShift (WeeklySchedule defaultSchedule, int i, boolean value, int offset) {
		Date date = new Date();
		date.setTime(date.getTime() + offset*7*86400000);
		return assignShift(defaultSchedule, i, value, date);
	}
	public boolean assignShift (WeeklySchedule defaultSchedule, int i, boolean value, Date date) {
		for (WeeklySchedule weeklySchedule : shifts) {
			if (weeklySchedule.containsDate(date))
				return weeklySchedule.assignShift(i, value);
		}
		//weekly schedule for that date does not exist yet
		WeeklySchedule weeklySchedule = new WeeklySchedule(defaultSchedule, date);
		shifts.add(weeklySchedule);
		return weeklySchedule.assignShift(i, value);
	}
	
	public String toString () {
		Date date = new Date();
		for (WeeklySchedule weeklySchedule : shifts) {
			if (weeklySchedule.containsDate(date))
				return weeklySchedule.toString();
		}
		return null;
	}
}
