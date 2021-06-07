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
@Table(name = "chainmanagers")
public class ChainManager extends Worker {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7646477538037466098L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private List<Refund> refunds;
	
	public ChainManager() {}
	
	public ChainManager (List <Refund> _refunds){
		this.refunds = _refunds;
	}
	
	public List <Refund> getRefunds(){
		return this.refunds;
	}
	
	public void setRefunds(List <Refund> _refunds){
		this.refunds = _refunds;
	}
}