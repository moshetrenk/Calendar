package cal;


import java.util.ArrayList;

public class CancelledEventNotification extends Notification {
	private String eventName, date, startTime;
	CancelledEventNotification(ArrayList<String> one) {
		super(one);
		//CancelledEventNotification a = new CancelledEventNotification(one);
		//a.getGroupName();
		//a.getRecipient();
		
	}
	
	CancelledEventNotification(ArrayList<String> one, ArrayList<Object> two) {
		super(one);
		eventName = (String) two.get(0);
		date 	  = (String) two.get(1);
		startTime = (String) two.get(2);
	}
	
	@Override
	public String toString() {
		return "CancelledEventNotification [eventName=" + eventName + ", date=" + date + ", startTime=" + startTime
				+ ", groupName = " + getGroupName() + ", recipient = " + getRecipient() + "]";
	}

	public String getEventName() {
		return eventName;
	}
	public String getDate() {
		return date;
	}
	public String getStartTime() {
		return startTime;
	}

	
}
