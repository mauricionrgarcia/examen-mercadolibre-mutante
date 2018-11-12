package com.mercadolibre.exam.mutant.repository;

import com.mercadolibre.exam.mutant.domain.DNAStatus;

/**
 * Result of checking whether a human is a mutant.
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 07/11/2018 02:35:54
 */
public interface DNAStatusRepository {
	/**
	 * Statistics of verifications
	 * 
	 * @return DNAStatus
	 */
	DNAStatus getSummaryStatus();
}
