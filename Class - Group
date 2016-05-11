package cal;

import java.util.*;

public class Group {
	private String currentUser;
	private static String groupName;
	private HashMap<String, Event> groupEvents = new HashMap<String, Event>();
	private static HashMap<String, Integer> copyOfGroupEventsForFindingIfThereIsAnEvent = new HashMap<String, Integer>();
	private User user;
	private ArrayList<String> usersInGroup = new ArrayList<String>();
	private static SQLHelper sqlHelper = new SQLHelper();
	//private ArrayList<User> members = new ArrayList<User>();
	
	public Group(String username, String tempGroupName){
		
		currentUser = username;
		this.groupName = tempGroupName;
		usersInGroup = sqlHelper.getGroupMembersByGroupName(groupName);
		groupEvents = initEvents();
		//System.out.println("This is to make sure user is right =" + currentUser);
		usersInGroup.remove(currentUser.toLowerCase());
		//System.out.println("after user removed, usersInGroup = "+ usersInGroup);
		
		/*for(int i = 0; i < usersInGroup.size(); i++){
			user = new User(usersInGroup.get(i));
			members.add(new User(usersInGroup.get(i)));
		}
		System.out.println(usersInGroup);
		for(int i = 0; i < usersInGroup.size(); i++){
			System.out.println(members);
			System.out.println("THIS IS WHAT WE WANT TO SEE" + usersInGroup.get(i));
			user[i] = new User(usersInGroup.get(i));
			System.out.println("user[i] =" + user[i]);
			members.add(user[i]);
			System.out.println("members after adding " + usersInGroup.get(i) + "=" + members);
		}
		*/
		
	}
	
	public void eventHasBeenDeleted(CancelledEventNotification event){
		for(String u : usersInGroup){
			new User(u).addEventCancelledNotification(event);
		}
		
		int x =  Integer.parseInt(event.getStartTime());
		
		sqlHelper.deleteEvent(groupName, event.getDate(), x);
	}
	
	private static HashMap<String, Event> initEvents(){
		ArrayList cur = sqlHelper.getEvents(groupName),
				 temp = new ArrayList();	
		
		int numEvents = sqlHelper.getNumberOfEventsByUser(groupName);
		HashMap<String, Event> these = new HashMap<String, Event>();
		Event event;
		String delimiter = sqlHelper.getDelimiter();
		
		for(int i = 0; i < cur.size(); i++){
			if(!cur.get(i).equals(delimiter)){
				temp.add(cur.get(i));	
				if(i % 10 == 3)
					putEventDatesIntoCopyOfMyEvents((String) cur.get(i));
			}
			else{
				event = new Event(temp);
				temp = new ArrayList();
				
				these.put(event.getDateTime(), event);
				}
		}
		
		return these;
	}
	
	private static void putEventDatesIntoCopyOfMyEvents(String date){
		if(copyOfGroupEventsForFindingIfThereIsAnEvent.containsKey(date)){
			int a = copyOfGroupEventsForFindingIfThereIsAnEvent.get(date);
			copyOfGroupEventsForFindingIfThereIsAnEvent.replace(date, a, a++);
		}
		else
			copyOfGroupEventsForFindingIfThereIsAnEvent.put(date, 1);
	}
	
	public void addApproval(Event e){
		groupEvents.get(e.getEventDate()).userAcceptedEvent();
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public void addMember(User u){
		if(!usersInGroup.contains(u.getUsername()))
			usersInGroup.add(u.getUsername());
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if (currentUser == null) {
			if (other.currentUser != null)
				return false;
		} else if (!currentUser.equals(other.currentUser))
			return false;
		if (groupEvents == null) {
			if (other.groupEvents != null)
				return false;
		} else if (!groupEvents.equals(other.groupEvents))
			return false;
		if (usersInGroup == null) {
			if (other.usersInGroup != null)
				return false;
		} else if (!usersInGroup.equals(other.usersInGroup))
			return false;
		if (usersInGroup == null) {
			if (other.usersInGroup != null)
				return false;
		} else if (!usersInGroup.equals(other.usersInGroup))
			return false;
		return true;
	}

	boolean isThereAnEventToday(String date){
		if (copyOfGroupEventsForFindingIfThereIsAnEvent.containsKey(date))
			return true;
		
		return false;
	}
	
	Event getEvent(String dateTime){
		return groupEvents.get(dateTime);
	}
	
	public HashMap<String, Event> getEventList() {
		return groupEvents;
	}
	
	public ArrayList getUsersInGroup() {
		return usersInGroup;
	}
	
	public void addEvent(Event event){
		if(!groupEvents.containsKey(event.getDateTime()))
			groupEvents.put(event.getDateTime(), event);
		else System.out.println("you dun goofed");
	}	

	public void sendEventNotifications(Event event){
		for(int i = 0; i < usersInGroup.size();i++){
			/*
			System.out.println("members count = " + members.size());
			//System.out.println(members.get(i).getUsername() + " was notified");
			//members.get(i).addNewEventNotification(event);
			user[i].addNewEventNotification(event);
			*/
			new User(usersInGroup.get(i)).addNewEventNotification(event);
	
		
		}
		
	
	}
}
