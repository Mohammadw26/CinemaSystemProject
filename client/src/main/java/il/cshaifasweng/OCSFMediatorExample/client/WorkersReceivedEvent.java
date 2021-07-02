package il.cshaifasweng.OCSFMediatorExample.client;


import il.cshaifasweng.OCSFMediatorExample.entities.Message;


public class WorkersReceivedEvent {
	private Object WorkersList;

	public WorkersReceivedEvent(Message msg) {
		this.WorkersList = msg.getObject();
	}
	
	public Object getWorkersList() {
		return WorkersList;
	}
	
}
