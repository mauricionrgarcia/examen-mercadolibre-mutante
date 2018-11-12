package com.mercadolibre.exam.mutant.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * return the statistics of verifications the exposed method (humans, mutants
 * and ratio).
 * <p>
 * {“count_mutant_dna”:40,“count_human_dna”: 100, “ratio”: 0.4}
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 11/11/2018 02:52:29
 */
public class DNAStatus {
	@JsonProperty("count_mutant_dna")
	private Long mutantCount = 0L;
	@JsonProperty("count_human_dna")
	private Long humanCount = 0L;
	private BigDecimal ratio;
	@JsonIgnore
	private Long total = 0L;

	/**
	 * @return the mutantCount
	 */
	public Long getMutantCount() {
		return mutantCount;
	}

	/**
	 * @param mutantCount the mutantCount to set
	 */
	public void setMutantCount(Long mutantCount) {
		this.mutantCount = mutantCount;
	}

	/**
	 * @return the humanCount
	 */
	public Long getHumanCount() {
		return humanCount;
	}

	/**
	 * @param humanCount the humanCount to set
	 */
	public void setHumanCount(Long humanCount) {
		this.humanCount = humanCount;
	}

	/**
	 * @return the ratio
	 */
	public BigDecimal getRatio() {
		return ratio;
	}

	/**
	 * @param ratio the ratio to set
	 */
	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}

	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DNAStatus [mutantCount=" + mutantCount + ", humanCount=" + humanCount + ", ratio=" + ratio + ", total="
				+ total + "]";
	}

}
