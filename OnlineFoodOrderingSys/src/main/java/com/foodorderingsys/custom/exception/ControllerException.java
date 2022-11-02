package com.foodorderingsys.custom.exception;

import org.springframework.stereotype.Component;

@Component
public class ControllerException extends RuntimeException {
	

	/**
	 * 
	 */
	private static long serialVersionUID = 1L;
	private String errorcode;
	private String errormessage;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}
	public String getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	public String getErrormessage() {
		return errormessage;
	}
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	public ControllerException(String errorcode, String errormessage) {
		
		this.errorcode = errorcode;
		this.errormessage = errormessage;
	}

   public ControllerException() {
	   
   }
	 

	

}
