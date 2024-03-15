package com.agencia.viajes.common;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageResponse<T> implements Serializable {
	public static final long serialVersionUID=1L;

	private Integer status=0;
	private String message="";
	private transient T result=null;
}
