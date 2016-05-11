package calendarTest;

import java.util.ArrayList;

public class Event {
	
	private int eventNumber;
	private String eventName;
	private String eventDate;
	private Object startTime;
	private Object endTime;
	private String id;
	private String description;
	private int numberOfAccepted;
	private int numberOfTotal;
	

	public Event(ArrayList eventList){
		
		eventNumber = (int) eventList.get(0);
		eventName = (String) eventList.get(1);
		id = (String) eventList.get(2);
		eventDate = (String) eventList.get(3);
		startTime =  eventList.get(4);
		endTime =   eventList.get(5);
		description = (String) eventList.get(6);
		numberOfAccepted = (int) eventList.get(7);
		numberOfTotal = (int) eventList.get(8);
		
	}
	public ArrayList getEventDetails(){
		
		ArrayList eventDetails = new ArrayList();
		
		eventDetails.add(0, eventNumber);
		eventDetails.add(1, eventName);
		eventDetails.add(2,id);
		eventDetails.add(3,eventDate);
		eventDetails.add(4,startTime);
		eventDetails.add(5, endTime);
		eventDetails.add(6, description);
		eventDetails.add(7, numberOfAccepted);
		eventDetails.add(8,numberOfTotal);
		
		return eventDetails;
		
	}
	public String getDateTime(){
		
		return eventDate + startTime.toString();
	}
	
	
	public int getEventNumber() {
		return eventNumber;
	}
	public String getEventName() {
		return eventName;
	}
	public String getEventDate() {
		return eventDate;
	}
	public String getStartTime() {
		return startTime.toString();
	}
	public String getEndTime() {
		return endTime.toString();
	}
	public String getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public int getNumberOfAccepted() {
		return numberOfAccepted;
	}
	public int getNumberOfTotal() {
		return numberOfTotal;
	}
	
	public boolean isEventApproved(){
		return numberOfAccepted == numberOfTotal;
	}
	@Override
	public String toString() {
		return "Event [eventNumber=" + eventNumber + ", eventName=" + eventName + ", eventDate=" + eventDate
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", id=" + id + ", description=" + description
				+ ", numberOfAccepted=" + numberOfAccepted + ", numberOfTotal=" + numberOfTotal + "]";
	}
	
	public void userAcceptedEvent(){
		if (numberOfAccepted < numberOfTotal){
		numberOfAccepted ++;
		}
	}
	
	public Notification_Event createNotification(User user){
		return new Notification_Event(user.getUsername(), id, eventDate,((int)startTime),((int)endTime));
		
		
	}
	
	
}
