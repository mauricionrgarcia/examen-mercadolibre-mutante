package com.mercadolibre.exam.mutant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.exam.mutant.domain.DNAStatus;
import com.mercadolibre.exam.mutant.repository.DNAStatusRepository;

/**
 * Statistics of verifications the exposed method (humans, mutants and ratio).
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 11/11/2018 02:58:47
 */
@Service
public class DNAStatusService {
	/**
	 * logger
	 */
	private static final Logger log = LoggerFactory.getLogger(DNAStatusService.class);

	@Autowired
	private DNAStatusRepository dnaStatusRepository;

	/**
	 * return the statistics of verifications the exposed method (humans, mutants
	 * and ratio).
	 * 
	 * @return statistics and ratio
	 */
	public DNAStatus getStatistics() {
		log.debug("Find statistics of verifications the exposed method (humans, mutants and ratio)");
		return dnaStatusRepository.getSummaryStatus();
	}

}