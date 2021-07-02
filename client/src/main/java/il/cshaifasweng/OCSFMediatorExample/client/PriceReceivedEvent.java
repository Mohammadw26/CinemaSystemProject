package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class PriceReceivedEvent {
	private Object PricesList;

	public PriceReceivedEvent(Message msg) {
		this.PricesList = msg.getObject();
	}

	public Object getPriceList() {
		return PricesList;
	}

}
