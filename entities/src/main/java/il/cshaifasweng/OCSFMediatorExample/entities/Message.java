package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private Object object;
	private Object object2;
	
	
	public Message() {}
	
	public Message(String msg, Object obj) {
		this.message = msg;
		this.object = obj;
	}
	
	public Message(String msg, Object obj,Object obj2) {
		this.message = msg;
		this.object = obj;
		this.object2 = obj2;
	}
	
	public Message(String msg) {
		this.message = msg;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public Object getObject () {
		return this.object;
	}
	
	public Object getObject2 () {
		return this.object2;
	}
	
	public void setMessage(String msg) {
		this.message=msg;
	}
	
	public void setObject(Object obj) {
		this.object=obj;
	}
	
	public void setObject2(Object obj) {
		this.object2=obj;
	}
	
	public String toString() {
		return this.message;
	}
	
	
	
}
