package com.mercadolibre.exam.mutant.service.detector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Analyzes a vertical sequences from DNA
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 11/11/2018 18:23:36
 */
public class VerticalSequenceProcessor extends MutantDetectorProcessor {

	private static final Logger log = LoggerFactory.getLogger(VerticalSequenceProcessor.class);
	private static final Direction DIRECTION = Direction.VESTICAL;

	public VerticalSequenceProcessor(DetectorContext context) {
		super(context);
	}

	@Override
	protected void searchMutanteSequences() {
		log.debug("Analyze colums of given dna sequence at direction {}: ", DIRECTION);
		char[][] dna = context.getDna();
		for (int column = 0; column < dna.length; column++) {
			boolean match = findMutantSequence(Coordinate.at(dna, 0, column));
			if (match) {
				break;
			}
		}
	}

	@Override
	protected void moveNext(Coordinate coordinate) {
		coordinate.row++;
		coordinate.subIndex++;
		coordinate.lastChar = coordinate.curruntChar;
		coordinate.curruntChar = coordinate.dna[coordinate.row][coordinate.column];
	}

	@Override
	protected boolean hasNext(Coordinate coordinate, int actualSequence) {
		return coordinate.row + 1 <= coordinate.safeIndex;
	}

}
