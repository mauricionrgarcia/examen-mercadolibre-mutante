package com.mercadolibre.exam.mutant.service.detector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Analyzes a diagonal sequences from DNA
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 10/11/2018 23:31:39
 */
public class DianonalSequenceProcessor extends MutantDetectorProcessor {

	private static final Logger log = LoggerFactory.getLogger(DianonalSequenceProcessor.class);
	private static final Direction DIRECTION = Direction.DIAGONAL_DOWN;

	public DianonalSequenceProcessor(DetectorContext context) {
		super(context);
	}

	@Override
	public void searchMutanteSequences() {
		log.debug("Analyze colums of given dna sequence at direction {}: ", DIRECTION);
		char[][] dna = context.getDna();

		boolean match = findMutantSequence(Coordinate.at(dna, 0, 0));
		if (match) {
			return;
		}
		for (int row = 1; row <= dna.length - context.getSequenceToMudant(); row++) {
			match = findMutantSequence(Coordinate.at(dna, row, 0, row))
					|| findMutantSequence(Coordinate.at(dna, 0, row, row));
			if (match) {
				break;
			}
		}

	}

	@Override
	protected void moveNext(Coordinate coordinate) {
		coordinate.row++;
		coordinate.column++;
		coordinate.subIndex++;
		coordinate.lastChar = coordinate.curruntChar;
		coordinate.curruntChar = coordinate.dna[coordinate.row][coordinate.column];
	}

	@Override
	protected boolean hasNext(Coordinate coordinate, int actualSequence) {
		int subIndex = coordinate.subIndex;
		int available = coordinate.size - subIndex;
		return available + actualSequence >= context.getSequenceToMudant()
				&& Math.max(coordinate.column, coordinate.row) + 1 < coordinate.size;
	}

}
