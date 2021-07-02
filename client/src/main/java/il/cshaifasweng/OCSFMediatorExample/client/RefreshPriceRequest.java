package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Price;


public class RefreshPriceRequest {
	private  List<Price> pricesList;

	public List<Price> getPricesList() {
		return pricesList;
	}

	public void setPricesList(List<Price> pricesList) {
		this.pricesList = pricesList;
	}
	
	public RefreshPriceRequest(Message msg) {
		setPricesList((List<Price>)msg.getObject());
	}

}
