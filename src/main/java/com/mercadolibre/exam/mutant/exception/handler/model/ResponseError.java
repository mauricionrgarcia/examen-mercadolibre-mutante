package com.mercadolibre.exam.mutant.exception.handler.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Detail of the erro
 *
 * @author <a href="mailto:mauricionrgarica#gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 11/11/2018 18:16:32
 */
@JsonInclude(Include.NON_NULL)
public class ResponseError implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7086555032131362215L;

	private Integer status;
	private String errorCode;
	private String message;
	private String errorMessage;
	private Long timestamp;
	private Object[] argsMessage;

	/**
	 * Construtor no args
	 */
	public ResponseError() {

	}

	/**
	 * @param status
	 * @param errorCode
	 * @param message
	 * @param errorMessage
	 */
	public ResponseError(Integer status, String errorCode, String message, String errorMessage) {
		super();
		this.status = status;
		this.errorCode = errorCode;
		this.message = message;
		this.errorMessage = errorMessage;
		this.setTimestamp(Calendar.getInstance().getTimeInMillis());
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the timestamp
	 */
	public Long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the argsMessage
	 */
	public Object[] getArgsMessage() {
		return argsMessage;
	}

	/**
	 * @param argsMessage the argsMessage to set
	 */
	public void setArgsMessage(Object[] argsMessage) {
		this.argsMessage = argsMessage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResponseError [status=" + status + ", errorCode=" + errorCode + ", message=" + message
				+ ", errorMessage=" + errorMessage + ", timestamp=" + timestamp + ", argsMessage="
				+ Arrays.toString(argsMessage) + "]";
	}
}
