//Group class:


public class Group {
	
	int membersInGroup = 0;
	Friend myGroup[] = new Friend[10];
	
	public Group(Friend[] friends, String groupName) {
		for(int i = 0; i < friends.length; i++){
			if(friends[i].receiveGroupRequest())
				addFriend(friends[i]);
		}
		
		//do something with the group name?
	}

	void addFriend(Friend friend){
		if (membersInGroup >= 10)
			System.out.println("Group is full");
		else{
			if(friend.receiveGroupRequest()){
				myGroup[membersInGroup] = friend;
				membersInGroup++;
			}
		}
	}

	public static void main(String[] args) {

	}

}