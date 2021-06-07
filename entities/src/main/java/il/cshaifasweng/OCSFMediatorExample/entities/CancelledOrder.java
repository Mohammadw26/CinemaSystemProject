package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cancelled")
public class CancelledOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5541945372013277387L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private List <Order> ordersList;
	private int cancelledOrdersNum;
	private List <String> reasons;
	
	public CancelledOrder() {}
	
	public CancelledOrder(List <Order> _ordersList,int _cancelledOrdersNum,List <String> _reasons){
		this.ordersList = _ordersList;
		this.cancelledOrdersNum = _cancelledOrdersNum;
		this.reasons = _reasons;
	}
	
	public List<Order> getOrdersList(){
		return this.ordersList;
	}
	
	public void setOrdersList(List <Order> _ordersList){
		this.ordersList = _ordersList;
	}
	
	
	public int getCancelledOrdersNum(){
		return this.cancelledOrdersNum;
	}
	
	public void setCancelledOrdersNum(int _cancelledOrdersNum){
		this.cancelledOrdersNum = _cancelledOrdersNum;
	}
	
	public List<String> getReasons(){
		return this.reasons;
	}
	
	public void setReasons(List<String> _reasons){
		this.reasons = _reasons;
	}
	
}

	
	

	