package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;


public class SeatSavedEvent {
	private  Screening newScrn;
	
	public SeatSavedEvent(Message msg) {
		this.setNewScrn((Screening)msg.getObject());

	}

	public Screening getNewScrn() {
		return newScrn;
	}

	public void setNewScrn(Screening newScrn) {
		this.newScrn = newScrn;
	}

	
	

}
