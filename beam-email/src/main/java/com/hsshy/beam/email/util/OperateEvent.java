package com.hsshy.beam.email.util;

import java.io.Serializable;

public class OperateEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	private String targetId;

	private int type;

	public OperateEvent(){
		
	}


	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}



	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}