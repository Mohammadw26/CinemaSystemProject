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
	//private static final long serialVersionUID = ??;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private list<refund> refunds;
	
	public ChainManager() {}
	
	public ChainManager (list <refund> _refunds){
		this.refunds = _refunds;
	}
	
	public int getRefunds(){
		return this.refunds;
	}
	
	public void setRefunds(list <refund> _refunds){
		this.refunds = _refunds;
	}