package il.cshaifasweng.OCSFMediatorExample.client;


import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;


public class BranchesReceivedEvent {
	private Object BranchesList;

	public BranchesReceivedEvent(Message msg) {
		this.BranchesList = msg.getObject();
	}
	
	public Object getBranchesList() {
		return BranchesList;
	}
	
}
