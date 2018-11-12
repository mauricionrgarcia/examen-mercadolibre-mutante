package com.mercadolibre.exam.mutant.service.detector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract implementation to provide process read to check if a given DNA
 * sequence is a mutant sequences
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 11/11/2018 18:18:20
 */
public abstract class MutantDetectorProcessor {

	private static final Logger log = LoggerFactory.getLogger(MutantDetectorProcessor.class);
	protected final DetectorContext context;

	public MutantDetectorProcessor(DetectorContext context) {
		this.context = context;
	}

	/**
	 * Analyzes a given DNA sequence searching for mutant sequences
	 */
	protected abstract void searchMutanteSequences();

	/**
	 * Move to next coordinate
	 * 
	 * @param coordinate
	 */
	protected abstract void moveNext(Coordinate coordinate);

	/**
	 * @param coordinate
	 * @param actualSequence size of sequence
	 * @return true if has next available sequence
	 */
	protected abstract boolean hasNext(Coordinate coordinate, int actualSequence);

	/**
	 * Search Mutant DNA Sequences in the vector
	 * 
	 * @param coordidate first Coordinate
	 * @return
	 */
	protected boolean findMutantSequence(Coordinate coordidate) {
		char currentChar = coordidate.dna[coordidate.row][coordidate.column];
		int sequence = 1;
		while (hasNext(coordidate, sequence)) {
			moveNext(coordidate);

			if (currentChar != coordidate.curruntChar) {
				sequence = 1;
				currentChar = coordidate.curruntChar;
			} else if (++sequence >= context.getSequenceToMudant()) {
				log.debug("New mutant sequences end at: {}", coordidate);
				this.newSequenceMatch();

				if (hasMatchSequencesMutant()) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}

	/**
	 * Increases the count of identified mutant sequences
	 */
	protected void newSequenceMatch() {
		context.setMatchs(context.getMatchs() + 1);

	}

	/**
	 * Analyze if has more than 1 mutant sequences
	 * 
	 * @param context
	 * @return true if the total of the identified mutant DNA sequence belongs to a
	 *         mutant, else continue processing
	 */
	public boolean hasMatchSequencesMutant() {
		log.debug("Analyze if has more than {} mutant sequences. Actutal: {} ",
				context.getCountSequencesMatchMutant() - 1, context.getMatchs());
		int count = context.getCountSequencesMatchMutant();
		return context.getMatchs() >= count;
	}

	/**
	 * Representation of position of DNA matrix
	 * 
	 * @version
	 * @sinse 10/11/2018 23:09:35
	 */
	protected static class Coordinate {
		char dna[][];
		int subIndex;
		int row;
		int column;
		char lastChar;
		char curruntChar;
		int safeIndex;
		int size;

		public Coordinate(char[][] dna, int subIndex, int row, int column) {
			super();
			this.dna = dna;
			this.safeIndex = dna.length - 1;
			this.size = dna.length;
			this.subIndex = subIndex;
			this.row = row;
			this.column = column;
			this.lastChar = dna[row][column];
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Coordinate [row=" + row + ", column=" + column + ", lastChar=" + lastChar + ", curruntChar="
					+ curruntChar + "]";
		}

		public static Coordinate at(char[][] dna, int row, int column) {
			return new Coordinate(dna, 0, row, column);
		}

		public static Coordinate at(char[][] dna, int row, int column, int index) {
			return new Coordinate(dna, index, row, column);
		}

	}

}
