package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Restrictions")
public class TavSagoal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static TavSagoal singleton;
	private static int y;
	private static Boolean effective;
	
	private TavSagoal() {}
	
	public static TavSagoal getTavSagoal() {
		if (singleton == null) {
			singleton = new TavSagoal();
		}
		return singleton;
	}

	public static int getY() {
		return y;
	}

	public static void setY(int y) {
		TavSagoal.y = y;
	}

	public static Boolean isEffective() {
		return effective;
	}

	public static void setEffective(Boolean effective) {
		TavSagoal.effective = effective;
	}
}
