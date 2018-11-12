package com.mercadolibre.exam.mutant.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

/**
 * Utility class that encapsulating calls to the message archive of the
 * {@link MessageSource}
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 11/11/2018 12:18:18
 */
@Service
public class BundleMessage {

	/**
	 * retrieve the messages from the messages.properties
	 */
	@Autowired
	private MessageSource messageSource;

	/**
	 * load message
	 *
	 * @param message code
	 * @param args    parameters
	 * @return message
	 */
	public String getMessage(String message, Object... args) {
		return messageSource.getMessage(message, args, LocaleContextHolder.getLocale());
	}

	/**
	 * load message from {@link FieldError}
	 *
	 * @param fieldError Encapsulates a field error
	 * @return message
	 */
	public String getMessage(FieldError fieldError) {
		return messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
	}

	/**
	 * load message
	 * 
	 * @param message code
	 * @param args    code args
	 * @return message
	 */
	public String getMessageProperties(String message, Object... args) {
		int length = args != null ? args.length : 0;
		Object[] messages = new Object[length];
		for (int i = 0; i < length; i++) {
			messages[i] = this.getMessage(args[i].toString());
		}
		return this.getMessage(message, messages);
	}

}
