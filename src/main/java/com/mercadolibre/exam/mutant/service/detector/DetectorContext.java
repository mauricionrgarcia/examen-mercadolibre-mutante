package com.mercadolibre.exam.mutant.service.detector;

/**
 * Context to process a dna sequence
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 11/11/2018 18:17:05
 */
public class DetectorContext {

	char[][] dna;
	private int sequenceToMudant;
	private int countSequencesMatchMutant;
	private int matchs;

	/**
	 * @return the matchs
	 */
	public int getMatchs() {
		return matchs;
	}

	/**
	 * @param matchs the matchs to set
	 */
	public void setMatchs(int matchs) {
		this.matchs = matchs;
	}

	/**
	 * @return the dna
	 */
	public char[][] getDna() {
		return dna;
	}

	/**
	 * @param dna the dna to set
	 */
	public void setDna(char[][] dna) {
		this.dna = dna;
	}

	/**
	 * @return the sequenceToMudant
	 */
	public int getSequenceToMudant() {
		return sequenceToMudant;
	}

	/**
	 * @param sequenceToMudant the sequenceToMudant to set
	 */
	public void setSequenceToMudant(int sequenceToMudant) {
		this.sequenceToMudant = sequenceToMudant;
	}

	/**
	 * @return the countSequencesMatchMutant
	 */
	public int getCountSequencesMatchMutant() {
		return countSequencesMatchMutant;
	}

	/**
	 * @param countSequencesMatchMutant the countSequencesMatchMutant to set
	 */
	public void setCountSequencesMatchMutant(int countSequencesMatchMutant) {
		this.countSequencesMatchMutant = countSequencesMatchMutant;
	}

}
