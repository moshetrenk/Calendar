package cal;

import java.util.ArrayList;

public class Notification_Group extends Notification{
	private String sender;
	
	Notification_Group(ArrayList<?> one) {
		super(one);
		sender = (String) one.get(2);
	}
	
	public String getSender() {
		return sender;
	}

	@Override
	public String toString() {
		return "Notification_Group [sender=" + sender + " groupName=" + groupName + " recipient="+ recipient +"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
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
		Notification_Group other = (Notification_Group) obj;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		return true;
	}
}
