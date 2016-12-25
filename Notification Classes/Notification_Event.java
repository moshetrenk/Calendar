package cal;

import java.util.ArrayList;

public class Notification_Event extends Notification{
	
	Notification_Event(ArrayList<Object> one) {
		super(one);
		date = (String) one.get(2);
		startTime = (int) one.get(3);
		endTime = (int) one.get(4);
	}
	Notification_Event(String userName, String nameOfGroup , String dateOfEvent, int timeStart, int timeEnd){
		super(userName,nameOfGroup);
		date = dateOfEvent;
		startTime = timeStart;
		endTime = timeEnd;
	}
	
	private String date;
	private int startTime, endTime;
	
	public String getDate() {
		return date;
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public int getEndTime() {
		return endTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + endTime;
		result = prime * result + startTime;
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
		Notification_Event other = (Notification_Event) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (endTime != other.endTime)
			return false;
		if (startTime != other.startTime)
			return false;
		return true;
	}
}
