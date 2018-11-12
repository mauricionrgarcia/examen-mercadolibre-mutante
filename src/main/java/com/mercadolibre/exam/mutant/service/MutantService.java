package com.mercadolibre.exam.mutant.service;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.exam.mutant.config.MutantProperties;
import com.mercadolibre.exam.mutant.domain.DNAResult;
import com.mercadolibre.exam.mutant.domain.DNASequence;
import com.mercadolibre.exam.mutant.exception.DNAStructureLengthException;
import com.mercadolibre.exam.mutant.exception.InvalidNitrogenousBaseException;
import com.mercadolibre.exam.mutant.repository.DNAResultRepository;
import com.mercadolibre.exam.mutant.service.detector.DetectorMutant;

/**
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 07/11/2018 02:39:12
 */
@Service
public class MutantService {

	/**
	 * logger
	 */
	private static final Logger log = LoggerFactory.getLogger(MutantService.class);
	/**
	 * Nitrogenous base pattern
	 */
	private static final Pattern NITROGENOUS_BASE_PATTERN = Pattern.compile("[atcg]+", Pattern.CASE_INSENSITIVE);

	@Autowired
	private MutantProperties mutantProperties;

	@Autowired
	private DNAResultRepository dnaResultRepository;

	/**
	 * Process and return if dna sequence belongs a mutent or human
	 * 
	 * @param dna
	 * @return true if find the count DNA sequences needed to consider a Mutant,
	 *         else false so Human
	 */
	public boolean isMutantDNA(DNASequence dna) {
		boolean dnaMudant = isDNAMutant(dna);

		DNAResult result = new DNAResult();
		result.setDna(dna);
		result.setMutant(dnaMudant);
		this.dnaResultRepository.save(result);

		return dnaMudant;

	}

	/**
	 * processing to decide if a dna Sequence belongs a mutant
	 * 
	 * @param dnaSequence NDA
	 * @return true if find the count DNA sequences needed to consider a Mutant
	 */
	private boolean isDNAMutant(DNASequence dnaSequence) {
		log.debug("Start processin to decote if a dnaSequence '{}' is a mutant' DNA", dnaSequence);

		if (dnaSequence.getDna().size() <= this.mutantProperties.getSequenceToMudant()) {
			log.debug("Minimum length must be greater than {} to belong to mutant.",
					this.mutantProperties.getSequenceToMudant());
			return false;
		}

		char[][] dna = loadDNAStructure(dnaSequence);
		DetectorMutant mutantDetector = new DetectorMutant(dna, mutantProperties.getCountSequencesMatchMutant(),
				mutantProperties.getSequenceToMudant());
		return mutantDetector.isMutante();

	}

	/**
	 * Load the DNA structure into a Two-dimensional vectors.
	 * 
	 * @param dnaSequence the dna sequence
	 * @return Two-dimensional vectors representation from DNA sequence
	 */
	private char[][] loadDNAStructure(DNASequence dnaSequence) {
		log.debug("Load the DNA structure into a Two-dimensional vectors.");
		int vectorLength = dnaSequence.getDna().size();
		char[][] dna = new char[vectorLength][vectorLength];

		for (int i = 0; i < vectorLength; i++) {
			String dnaRow = dnaSequence.getDna().get(i);
			validateDNAConsistency(vectorLength, dnaRow);
			dna[i] = dnaRow.toUpperCase().toCharArray();
		}
		return dna;
	}

	/**
	 * Validate DNA consistency
	 * <p>
	 * 
	 * @param vectorLength length of DNA sequeces
	 * @param dnaRow       row of dna
	 * @throws DNAStructureLengthException     when length of the DNA sequences is
	 *                                         not the same size.
	 * @throws InvalidNitrogenousBaseException when exist a invalid valid characters
	 */
	private void validateDNAConsistency(int vectorLength, String dnaRow) {
		if (dnaRow.length() != vectorLength) {
			log.error("The length of the DNA sequences must be the same size. Expected {}, found {}: {} ", vectorLength,
					dnaRow.length(), dnaRow);
			throw new DNAStructureLengthException(vectorLength, dnaRow.length());
		} else if (!NITROGENOUS_BASE_PATTERN.matcher(dnaRow).matches()) {
			log.error("The only valid characters are A, T, C e G. Found {}", dnaRow);
			throw new InvalidNitrogenousBaseException(dnaRow);
		}
	}

}
