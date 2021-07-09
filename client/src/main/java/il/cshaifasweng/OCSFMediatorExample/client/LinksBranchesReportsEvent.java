package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;

public class LinksBranchesReportsEvent {
	private List<SirtyaBranch> branchesList;

	public LinksBranchesReportsEvent(Message msg) {
		setBranchesList( (List<SirtyaBranch>) msg.getObject());

	}

	public List<SirtyaBranch> getBranchesList() {
		return branchesList;
	}

	public void setBranchesList(List<SirtyaBranch> branchesList) {
		this.branchesList = branchesList;
	}


}
