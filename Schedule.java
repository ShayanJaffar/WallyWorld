import java.util.LinkedList;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.annotations.Expose;

public class Schedule {
	private static Calendar calendar = Calendar.getInstance();
	
	//Linked List to store all the weekly schedules of a single employee
	@Expose
	public LinkedList<WeeklySchedule> shifts = new LinkedList<WeeklySchedule>();
	
	public Schedule() {}
	
	private WeeklySchedule getShift() {
		return getShift(new Date());
	}
	private WeeklySchedule getShift(int offset) {
		Date date = new Date();
		date.setTime(date.getTime() + offset*7*86400000);
		return getShift(date);
	}
	private WeeklySchedule getShift (Date date) {
		
		for (WeeklySchedule weeklySchedule : shifts) {
			if (weeklySchedule.containsDate(date))
				return weeklySchedule;
		}
		//weekly schedule for that date does not exist yet
		WeeklySchedule weeklySchedule = new WeeklySchedule(date);
		shifts.add(weeklySchedule);
		return weeklySchedule;
		
	}
	public WeeklySchedule getShift (String date) {
		if (date.equals("")) {
			return getShift();
		}
		try {
			int offset = Integer.parseInt(date);
			return getShift(offset);
		}
		catch (Exception exception1) {}
		try {
			String[] d = date.split("/");
			assert (d.length == 3);
			calendar.set(Integer.parseInt(d[2]), Integer.parseInt(d[0]), Integer.parseInt(d[1]));
			return getShift(calendar.getTime());
		}
		catch (Exception exception2) {
			return null;
		}
	}
	
	private int[] shiftAsIntArray (WeeklySchedule defaultSchedule) {
		return shiftAsIntArray(defaultSchedule, new Date());
	}
	private int[] shiftAsIntArray (WeeklySchedule defaultSchedule, int offset) {
		Date date = new Date();
		date.setTime(date.getTime() + offset*7*86400000);
		return shiftAsIntArray(defaultSchedule, date);
	}
	private int[] shiftAsIntArray (WeeklySchedule defaultSchedule, Date date) {
		for (WeeklySchedule weeklySchedule : shifts) {
			if (weeklySchedule.containsDate(date))
				return weeklySchedule.listAsIntArray();
		}
		//weekly schedule for that date does not exist yet
		WeeklySchedule weeklySchedule = new WeeklySchedule(defaultSchedule, date);
		shifts.add(weeklySchedule);
		return weeklySchedule.listAsIntArray();
	}
	public int[] shiftAsIntArray (WeeklySchedule defaultSchedule, String date) {
		if (date.equals("")) {
			return shiftAsIntArray(defaultSchedule);
		}
		try {
			int offset = Integer.parseInt(date);
			return shiftAsIntArray(defaultSchedule, offset);
		}
		catch (Exception exception1) {}
		try {
			String[] d = date.split("/");
			assert (d.length == 3);
			calendar.set(Integer.parseInt(d[2]), Integer.parseInt(d[0]), Integer.parseInt(d[1]));
			return shiftAsIntArray(defaultSchedule, calendar.getTime());
		}
		catch (Exception exception2) {
			return null;
		}
	}
	
	private boolean assignShift (WeeklySchedule defaultSchedule, int i, boolean value) {
		return assignShift(defaultSchedule, i, value, new Date());
	}
	private boolean assignShift (WeeklySchedule defaultSchedule, int i, boolean value, int offset) {
		Date date = new Date();
		date.setTime(date.getTime() + offset*7*86400000);
		return assignShift(defaultSchedule, i, value, date);
	}
	private boolean assignShift (WeeklySchedule defaultSchedule, int i, boolean value, Date date) {
		for (WeeklySchedule weeklySchedule : shifts) {
			if (weeklySchedule.containsDate(date))
				return weeklySchedule.assignShift(i, value);
		}
		//weekly schedule for that date does not exist yet
		WeeklySchedule weeklySchedule = new WeeklySchedule(defaultSchedule, date);
		shifts.add(weeklySchedule);
		return weeklySchedule.assignShift(i, value);
	}
	public int assignShift (WeeklySchedule defaultSchedule, int i, boolean value, String date) {
		shifts.getFirst().shift[i] = value;
		return 1; /*
		if (date.equals("")) {
			if (assignShift(defaultSchedule, i, value))
				return 1;
			else
				return 0;
		}
		try {
			int offset = Integer.parseInt(date);
			if (assignShift(defaultSchedule, i, value, offset))
				return 1;
			else
				return 0;
		}
		catch (Exception exception1) {}
		try {
			String[] d = date.split("/");
			assert (d.length == 3);
			calendar.set(Integer.parseInt(d[2]), Integer.parseInt(d[0]), Integer.parseInt(d[1]));
			if (assignShift(defaultSchedule, i, value, calendar.getTime()))
				return 1;
			else
				return 0;
		}
		catch (Exception exception2) {
			return -2;
		}*/
	}
	
	private String asString () {
		Date date = new Date();
		return asString (date);
	}
	private String asString (int offset) {
		Date date = new Date();
		date.setTime(date.getTime() + offset*7*86400000);
		return asString (date);
	}
	private String asString (Date date) {
		for (WeeklySchedule weeklySchedule : shifts) {
			if (weeklySchedule.containsDate(date))
				return weeklySchedule.asString(true);
		}
		return null;
	}
	public String asString (String date) {
		if (date.equals("")) {
			return asString();
		}
		try {
			int offset = Integer.parseInt(date);
			return asString(offset);
		}
		catch (Exception exception1) {}
		try {
			String[] d = date.split("/");
			assert (d.length == 3);
			calendar.set(Integer.parseInt(d[2]), Integer.parseInt(d[0]), Integer.parseInt(d[1]));
			return asString(calendar.getTime());
		}
		catch (Exception exception2) {
			return null;
		}
	}
}
