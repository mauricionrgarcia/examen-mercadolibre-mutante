package com.mercadolibre.exam.mutant.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mercadolibre.exam.mutant.domain.DNAResult;

/**
 * Result of checking whether a human is a mutant.
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 07/11/2018 02:35:54
 */
@Repository
public interface DNAResultRepository extends MongoRepository<DNAResult, DNAResult> {

}
