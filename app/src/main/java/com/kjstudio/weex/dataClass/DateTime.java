package com.kjstudio.weex.dataClass;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime implements Serializable, Cloneable, Comparable<DateTime> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8887243500047069925L;
	
    public static final int SUNDAY = 1;
    public static final int MONDAY = 2;
    public static final int TUESDAY = 3;
    public static final int WEDNESDAY = 4;
    public static final int THURSDAY = 5;
    public static final int FRIDAY = 6;
    public static final int SATURDAY = 7;
	
	
    private final Calendar mCalendar = Calendar.getInstance();
	private final static SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat sMonthDayFormat = new SimpleDateFormat("MM/dd");
	private final static SimpleDateFormat sDayOfWeekFormat = new SimpleDateFormat("E요일");
	private final static SimpleDateFormat sTimeFormat = new SimpleDateFormat("HH:mm");
	private final static SimpleDateFormat sDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private final static SimpleDateFormat sDateTimeSecFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private boolean mDateOnly = false;
	
	public DateTime()
	{
		mCalendar.set(Calendar.HOUR_OF_DAY, 0);
		mCalendar.set(Calendar.MINUTE, 0);
		mCalendar.set(Calendar.SECOND, 0);
		mCalendar.set(Calendar.MILLISECOND, 0);
	}
	
	
	public DateTime(Calendar calendar)
	{
		this();
		mCalendar.set(calendar.YEAR, calendar.MONTH, calendar.DAY_OF_MONTH);
	}
	
	public DateTime(DateTime date)
	{
		this();
		mCalendar.set(date.getYear(), date.getMonth()-1, date.getDay(), date.getHour(), date.getMinute());
	}
	
	public DateTime(Date date)
	{
		this();
		mCalendar.setTime(date);
	}
	
	public DateTime(int year, int month, int day)
	{
		this();
		mCalendar.set(year, month, day);
		mDateOnly = true;
	}
	
	public DateTime(int year, int month, int day, int hour, int minute)
	{
		this();
		mCalendar.set(year, month, day, hour, minute);
		mDateOnly = false;
	}
	
	public boolean isDateOnly() {
		return mDateOnly;
	}

	public void setDateOnly(boolean dateOnly) {
		this.mDateOnly = dateOnly;
	}

	public void addYear(int year) {
		mCalendar.add(Calendar.YEAR, year);
	}
	
	public void addMonth(int month) {
		mCalendar.add(Calendar.MONTH, month);
	}
	
	public void addDay(int day) {
		mCalendar.add(Calendar.DAY_OF_MONTH, day);
	}
	
	public void addHour(int hour) {
		mCalendar.add(Calendar.HOUR_OF_DAY, hour);
	}
	
	public void addMinute(int minute) {
		mCalendar.add(Calendar.MINUTE, minute);
	}
	
	public int get(int field) {
		int value = mCalendar.get(field);
		if (field == Calendar.MONTH)
			value++;
		return value;
	}
	public void set(int field, int value) {
		mCalendar.set(field, value);
	}
	public void add(int field, int value) {
		mCalendar.add(field, value);
	}
	public int getYear() {
		return mCalendar.get(Calendar.YEAR);
	}
	
	public int getMonth() {
		return mCalendar.get(Calendar.MONTH) + 1;
	}
	
	public int getDay() {
		return mCalendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public int getDayOfWeek() {
		return mCalendar.get(Calendar.DAY_OF_WEEK);
	}
	
	public int getHour() {
		return mCalendar.get(Calendar.HOUR_OF_DAY);
	}
	
	public int getMinute() {
		return mCalendar.get(Calendar.MINUTE);
	}

	public long getTimeInMillis() {
		return mCalendar.getTimeInMillis();
	}
	
	private long getTotalDays() {
		return (((mCalendar.getTimeInMillis() / 1000) / 60) / 60) / 24;
	}
	
	private long getTotalMinutes() {
		return (mCalendar.getTimeInMillis() / 1000) / 60;
	}	
	
	public void setYear(int year) {
		mCalendar.set(Calendar.YEAR, year);
	}
	
	public void setMonth(int month) {
		mCalendar.set(Calendar.MONTH, month - 1);
	}
	
	public void setDay(int day) {
		mCalendar.set(Calendar.DAY_OF_MONTH, day);
	}
	
	public void setHour(int hour) {
		mCalendar.set(Calendar.HOUR_OF_DAY, hour);
	}
	
	public void setMinute(int minute) {
		mCalendar.set(Calendar.MINUTE, minute);
	}
	
	public boolean equalDay(DateTime other) {
		return this.getYear() == other.getYear() &&
				this.getMonth() == other.getMonth() &&
				this.getDay() == other.getDay();
	}
	
	public static DateTime parse(String format) {
		if (format == null) 
			return null;
		DateTime result = null;
		try {
			Date date = sDateTimeFormat.parse(format);
			result = new DateTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static DateTime parseDate(String format) {
		if (format == null) 
			return null;
		DateTime result = null;
		try {
			Date date = sDateFormat.parse(format);
			result = new DateTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	public Date toDate() {
		return mCalendar.getTime();
	}

	public String toDateString() {
		return sDateFormat.format(toDate());
	}
	
	public String toMonthDayString() {
		return sMonthDayFormat.format(toDate());
	}
	
	public String toDayOfWeekString() {
		return sDayOfWeekFormat.format(toDate());
	}
	
	public String toTimeString() {
		return sTimeFormat.format(toDate());
	}
	
	public String toDateTimeString() {
		return sDateTimeFormat.format(toDate());
	}
	
	public String toDateTimeSecString() {
		return sDateTimeSecFormat.format(toDate());
	}
	
	@Override
	public String toString() {
		if (mDateOnly)
			return toDateString();
		else
			return toDateTimeString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long total = getTotalMinutes();
		result = prime * result + (int) (total ^ (total >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DateTime other = (DateTime) obj;
		return getTotalMinutes() == other.getTotalMinutes();
	}

	@Override
	public int compareTo(DateTime another) {
		long cmp = getTotalMinutes() - another.getTotalMinutes();
		if (cmp < 0) 
			return -1;
		else if (cmp > 0) 
			return 1;
		else 
			return 0;
	}
	
	
}
