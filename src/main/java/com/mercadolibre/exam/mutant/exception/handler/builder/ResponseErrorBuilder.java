package com.mercadolibre.exam.mutant.exception.handler.builder;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;

import com.mercadolibre.exam.mutant.exception.DNAException;
import com.mercadolibre.exam.mutant.exception.handler.model.ResponseError;
import com.mercadolibre.exam.mutant.util.BundleMessage;

/**
 * Builder to generate {@link ResponseError}
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 18/02/2018 20:44:18
 */
public class ResponseErrorBuilder {

	/**
	 * Messaging
	 */
	private final transient BundleMessage bungleMessage;

	/**
	 * default code error messsage
	 */
	private static final String UNEXPECTED = "unexpected";

	/**
	 * Response Error
	 */
	private ResponseError responseError;

	/**
	 * Contructor no args
	 */
	public ResponseErrorBuilder(BundleMessage bungleMessage) {
		this.bungleMessage = bungleMessage;
		responseError = new ResponseError();
	}

	public ResponseError build() {
		loadMessage(this.responseError.getMessage(), responseError.getArgsMessage());
		return responseError;
	}

	/**
	 * if message is empty, try to find the message from the error code in the
	 * external file: message.properties
	 * 
	 * @param messageCode
	 * @param argsMessage
	 */
	private void loadMessage(String messageCode, Object... argsMessage) {
		if (StringUtils.isEmpty(messageCode)) {
			try {
				String message = bungleMessage.getMessageProperties(this.responseError.getErrorCode(), argsMessage);
				this.responseError.setMessage(message);
			} catch (NoSuchMessageException e) {
				this.responseError.setMessage("No found message error: " + messageCode);
			}
		}
	}

	/**
	 * define response error
	 * 
	 * @param e BusinessException
	 * @return builder
	 */
	public ResponseErrorBuilder witchBusinesException(DNAException e) {
		withErrorCode(e.getCode());
		withMessage(e.getMessage());
		withErrorMessage(StringUtils.defaultIfBlank(e.getErroMessage(), ExceptionUtils.getRootCauseMessage(e)));
		withStatusCode(e.getStatusCode());
		withArgsMessage(e.getArgsMessage());
		return this;
	}

	/**
	 * define the code of response error
	 * 
	 * @param code
	 * @return builder
	 */
	public ResponseErrorBuilder withErrorCode(String code) {
		responseError.setErrorCode(StringUtils.defaultIfBlank(code, UNEXPECTED));
		return this;
	}

	/**
	 * define the message of response error
	 * 
	 * @param message message
	 * @return builder
	 */
	public ResponseErrorBuilder withMessage(String message) {
		responseError.setMessage(message);
		return this;
	}

	/**
	 * define the message of response error
	 * 
	 * @param messageCode code of message
	 * @param argsMessage arguments of message
	 * @return builder
	 */
	public ResponseErrorBuilder withMessageByCode(String messageCode, Object... argsMessage) {
		loadMessage(messageCode, argsMessage);
		return this;
	}

	/**
	 * define the error message f response error
	 * 
	 * @param errorMessage error message
	 * @return builder
	 */
	public ResponseErrorBuilder withErrorMessage(String errorMessage) {
		responseError.setErrorMessage(StringUtils.defaultIfBlank(errorMessage, UNEXPECTED));
		return this;
	}

	/**
	 * define the httpstatus of response error
	 * 
	 * @param httpStatus {@link HttpStatus}
	 * @return builder
	 */
	public ResponseErrorBuilder withStatusCode(HttpStatus httpStatus) {
		httpStatus = ObjectUtils.defaultIfNull(httpStatus, HttpStatus.INTERNAL_SERVER_ERROR);
		responseError.setStatus(httpStatus.value());
		return this;
	}

	/**
	 * message args
	 * 
	 * @param argsMessage arguments of message
	 * @return builder
	 */
	public ResponseErrorBuilder withArgsMessage(Object... argsMessage) {
		this.responseError.setArgsMessage(argsMessage);
		return this;
	}

}
