package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;

public class TabReportsEvent {
	private List<SirtyaBranch> branchesList;
	private List<Purchase> purchasesListDemand;

	public TabReportsEvent(Message msg) {
		this.branchesList = (List<SirtyaBranch>) msg.getObject();
		this.purchasesListDemand = (List<Purchase>) msg.getObject2();

	}

	public List<SirtyaBranch> getBranchesList() {
		return branchesList;
	}

	public void setBranchesList(List<SirtyaBranch> branchesList) {
		this.branchesList = branchesList;
	}

	public List<Purchase> getPurchasesListDemand() {
		return purchasesListDemand;
	}

	public void setPurchasesListDemand(List<Purchase> purchasesListDemand) {
		this.purchasesListDemand = purchasesListDemand;
	}

}
