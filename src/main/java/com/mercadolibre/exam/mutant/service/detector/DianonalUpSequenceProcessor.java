package com.mercadolibre.exam.mutant.service.detector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Analyzes a diagonal UP sequences from DNA
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 10/11/2018 23:31:39
 */
public class DianonalUpSequenceProcessor extends DianonalSequenceProcessor {

	private static final Logger log = LoggerFactory.getLogger(DianonalUpSequenceProcessor.class);
	private static final Direction DIRECTION = Direction.DIAGONAL_UP;

	public DianonalUpSequenceProcessor(DetectorContext context) {
		super(context);
	}

	@Override
	public void searchMutanteSequences() {
		log.debug("Analyze colums of given dna sequence at direction {}: ", DIRECTION);
		char[][] dna = context.getDna();

		int fistIndexRow = dna.length - 1;
		int fistIndexColumn = 0;
		boolean match = findMutantSequence(Coordinate.at(dna, fistIndexRow, fistIndexColumn));
		if (match) {
			return;
		}
		for (int row = 1; row <= dna.length - context.getSequenceToMudant(); row++) {
			match = findMutantSequence(Coordinate.at(dna, fistIndexRow - row, 0, row))
					|| findMutantSequence(Coordinate.at(dna, fistIndexRow, row, row));
			if (match) {
				break;
			}
		}

	}

	@Override
	protected void moveNext(Coordinate coordinate) {
		coordinate.row--;
		coordinate.column++;
		coordinate.subIndex++;
		coordinate.lastChar = coordinate.curruntChar;
		coordinate.curruntChar = coordinate.dna[coordinate.row][coordinate.column];
	}

	@Override
	protected boolean hasNext(Coordinate coordinate, int actualSequence) {
		int index = coordinate.subIndex;
		int available = coordinate.size - index;
		return available + actualSequence >= context.getSequenceToMudant() && coordinate.row - 1 >= 0;
	}

}
