package Calendar;
//import java.math.*;
import java.util.*;
import java.sql.*;

public class Group {
	
	private String groupName;
	private List eventList = new ArrayList();
	private ArrayList userInGroup = new ArrayList();
	private SQLHelper sqlHelper = new SQLHelper();
	
	public Group(String userName, String name){
		groupName = name;
		sqlHelper.insert(userName, userInGroup);
		}
	
	public void deleteEvent(String dateTime){
		for(User user: userInGroup)
			user.deleteEvent(dateTime);
		
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public List getEventList() {
		return eventList;
	}
	
	public List getUsersInGroup() {
		return userInGroup;
	}
	
	public void addEvent(List event){
	eventList.add(event);	
		
	}
	
	public void updateEvent(List event){
		if (!eventList.equals(event))
			eventList.add(event);
		
	}
	
	public void updateGroupMember(List groupMember){
		if (userInGroup.equals(groupMember))
			userInGroup.remove(groupMember);
		else
			userInGroup.add(groupMember);
		
		
	}
	

}
