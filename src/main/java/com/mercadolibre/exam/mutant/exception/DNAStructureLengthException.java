package com.mercadolibre.exam.mutant.exception;

import org.springframework.http.HttpStatus;

/**
 * When the inputted DNA sequence is inconsistent.
 * <p>
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 08/11/2018 01:34:50
 */
public class DNAStructureLengthException extends DNAException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1701103574391064913L;

	/**
	 * error code
	 * <p>
	 * The length of the DNA sequences must be the same size
	 */
	private static final String error = "dna.sequence.inconsistent.length";

	/**
	 * default args
	 * 
	 * @param expected
	 */
	public DNAStructureLengthException(int expected, int found) {
		super(error, HttpStatus.BAD_REQUEST);
		String msgError = "The length of the DNA sequences must be the same size. Expected " + expected + ", found "
				+ found;
		super.setErroMessage(msgError);
	}

}
