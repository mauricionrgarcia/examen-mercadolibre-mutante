package com.mercadolibre.exam.mutant.exception;

import org.springframework.http.HttpStatus;

/**
 * When the inputted DNA sequence is inconsistent.
 * <p>
 * The only valid characters are A, T, C e G.
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 08/11/2018 01:34:50
 */
public class InvalidNitrogenousBaseException extends DNAException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1701103574391064913L;

	/**
	 * error code
	 * <p>
	 * The only valid characters are A, T, C e G.
	 */
	private static final String error = "dna.invalid.nitrogenous.base";

	/**
	 * default args
	 * 
	 * @param dnaRow
	 */
	public InvalidNitrogenousBaseException(String dnaRow) {
		super(error, HttpStatus.BAD_REQUEST,
				"The only valid characters are A, T, C e G. Found invalida char in " + dnaRow);
	}

}
