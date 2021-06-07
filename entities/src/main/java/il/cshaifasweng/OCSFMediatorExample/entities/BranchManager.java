package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "branch_managers")
public class BranchManager extends Worker {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3084916657789526938L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private SirtyaBranch branch;
	

	public SirtyaBranch getBranch() {
		return branch;
	}

	public void setBranch(SirtyaBranch branch) {
		this.branch = branch;
	}
}
