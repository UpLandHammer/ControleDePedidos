package br.com.hammer.controledepedidos.resources.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	private String message;
	private String fiedName;

	public FieldMessage() {
	}

	public FieldMessage(String fiedName, String message) {
		super();
		this.fiedName = fiedName;
		this.message = message;
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFiedName() {
		return fiedName;
	}

	public void setFiedName(String fiedName) {
		this.fiedName = fiedName;
	}
}
