package com.mercadolibre.exam.mutant.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Mutant parameter configuration
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 07/11/2018 23:48:45
 */
@Configuration
@ConfigurationProperties(prefix = "mutant", ignoreUnknownFields = false)
public class MutantProperties {

	/**
	 * default sequence to mutant
	 */
	private static final int DEFAULT_SEQUENCE_SIZE_MUTANT = 4;
	/**
	 * default count sequences mutant
	 */
	private static final int DEFAULT_COUNT_SEQUENCES_MATCH_MUTANT = 2;

	/**
	 * default sequence to mutant, default 4
	 */
	private int sequenceToMudant = DEFAULT_SEQUENCE_SIZE_MUTANT;

	/**
	 * default count sequences mutant, default = 2
	 */
	private int countSequencesMatchMutant = DEFAULT_COUNT_SEQUENCES_MATCH_MUTANT;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MutantProperties [sequenceToMudant=" + sequenceToMudant + ", countSequencesMatchMutant="
				+ countSequencesMatchMutant + "]";
	}

}
