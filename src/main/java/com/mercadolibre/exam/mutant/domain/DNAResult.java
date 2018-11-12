package com.mercadolibre.exam.mutant.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * DNA result isMutant
 * <p>
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 07/11/2018 01:17:35
 */
@Document(collection = "dna_result")
public class DNAResult {

	@Id
	private DNASequence dna;

	@Indexed(name = "is_mutant")
	private boolean isMutant;

	public DNASequence getDna() {
		return dna;
	}

	public void setDna(DNASequence dna) {
		this.dna = dna;
	}

	public boolean isMutant() {
		return isMutant;
	}

	public void setMutant(boolean isMutant) {
		this.isMutant = isMutant;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dna == null) ? 0 : dna.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DNAResult other = (DNAResult) obj;
		if (dna == null) {
			if (other.dna != null)
				return false;
		} else if (!dna.equals(other.dna))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DNAResult [dna=" + dna + ", isMutant=" + isMutant + "]";
	}

}
