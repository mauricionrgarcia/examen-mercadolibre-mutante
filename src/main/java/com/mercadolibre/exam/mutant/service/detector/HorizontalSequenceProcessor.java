package com.mercadolibre.exam.mutant.service.detector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Analyzes a horizontal sequences from DNA
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 11/11/2018 18:19:32
 */
public class HorizontalSequenceProcessor extends MutantDetectorProcessor {

	private static final Logger log = LoggerFactory.getLogger(HorizontalSequenceProcessor.class);
	private static final Direction DIRECTION = Direction.HORIZONTAL;

	public HorizontalSequenceProcessor(DetectorContext context) {
		super(context);
	}

	@Override
	protected void searchMutanteSequences() {
		log.debug("Analyze colums of given dna sequence at direction {}: ", DIRECTION);
		char[][] dna = context.getDna();
		for (int row = 0; row < dna.length; row++) {
			boolean match = findMutantSequence(Coordinate.at(dna, row, 0));
			if (match) {
				break;
			}
		}
	}

	@Override
	protected void moveNext(Coordinate coordinate) {
		coordinate.column++;
		coordinate.subIndex++;
		coordinate.lastChar = coordinate.curruntChar;
		coordinate.curruntChar = coordinate.dna[coordinate.row][coordinate.column];
	}

	@Override
	protected boolean hasNext(Coordinate coordinate, int actualSequence) {
		return coordinate.column + 1 <= coordinate.safeIndex;
	}

}
