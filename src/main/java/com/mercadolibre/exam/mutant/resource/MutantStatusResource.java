package com.mercadolibre.exam.mutant.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.exam.mutant.domain.DNAStatus;
import com.mercadolibre.exam.mutant.service.DNAStatusService;

/**
 * REST controller to provide status of human and mutant DNA.
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 11/11/2018 02:37:55
 */
@RestController
@RequestMapping("/stats")
public class MutantStatusResource {

	private static final Logger log = LoggerFactory.getLogger(MutantStatusResource.class);

	@Autowired
	private DNAStatusService dnaStatusService;

	/**
	 * return the statistics of verifications the exposed method (humans, mutants
	 * and ratio).
	 * 
	 * @return status
	 */
	@GetMapping("/")
	public ResponseEntity<DNAStatus> statisticsDNAMutant() {
		log.debug("REQUEST to get the statistics of verifications the exposed method (humans, mutants and ratio).");
		DNAStatus status = dnaStatusService.getStatistics();
		return ResponseEntity.ok(status);
	}

}
