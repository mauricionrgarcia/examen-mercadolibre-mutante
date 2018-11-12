package com.mercadolibre.exam.mutant.exception;

import org.springframework.http.HttpStatus;

/**
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 11/11/2018 18:16:45
 */
public class DNAException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1701103574391064913L;

	/**
	 * error code
	 */
	private String code;

	/**
	 * represent the {@link HttpStatus} code
	 */
	private HttpStatus statusCode;

	/**
	 * error mesage (business)
	 */
	private String message;

	/**
	 * description of the error message (developer)
	 */
	private String erroMessage;

	/**
	 * Arguments to message
	 */
	private Object[] argsMessage;

	/**
	 * @param error error code
	 */
	public DNAException(String error, HttpStatus status) {
		super(error);
		this.code = error;
		this.statusCode = status;
	}

	/**
	 * @param error error code
	 */
	public DNAException(String error, HttpStatus status, String errorMessage) {
		super(error);
		this.code = error;
		this.statusCode = status;
		this.erroMessage = errorMessage;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the statusCode
	 */
	public HttpStatus getStatusCode() {
		return statusCode;
	}

	/**
	 * @return the message
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * @return the erroMessage
	 */
	public String getErroMessage() {
		return erroMessage;
	}

	/**
	 * @param erroMessage the erroMessage to set
	 */
	public void setErroMessage(String erroMessage) {
		this.erroMessage = erroMessage;
	}

	/**
	 * @return the argsMessage
	 */
	public Object[] getArgsMessage() {
		return argsMessage;
	}

}
