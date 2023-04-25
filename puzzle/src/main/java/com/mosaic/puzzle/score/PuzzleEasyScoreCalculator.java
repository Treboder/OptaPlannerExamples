package com.mosaic.puzzle.score;

import com.mosaic.puzzle.domain.PuzzleSolution;
import com.mosaic.puzzle.domain.PuzzleTile;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;

public class PuzzleEasyScoreCalculator implements EasyScoreCalculator<PuzzleSolution, HardSoftScore> {

    @Override
    public HardSoftScore calculateScore(PuzzleSolution posSolution) {

        int hardScore = 0;
        for(PuzzleTile leftTile : posSolution.getTileList())
            for(PuzzleTile rightTile : posSolution.getTileList()) {
                if(leftTile != rightTile &&  leftTile.interferesWith(rightTile))
                    hardScore--;
            }

        int softScore = 0;
        int[][] valueMap = posSolution.getCanvas().getValueMap();
        for(PuzzleTile myTile: posSolution.getTileList()) {
            if(myTile.isInitialized()) {
                int mapValue = valueMap[myTile.getXValue()][myTile.getYValue()];
                softScore = softScore - Math.abs(myTile.getValue() - mapValue);
            }
        }

        return HardSoftScore.of(hardScore, softScore);
    }

//    @Override
//    public SimpleScore calculateScore(PosSolution posSolution) {
//
//        int score = 0;
//        for(PosTile leftTile : posSolution.getTileList())
//            for(PosTile rightTile : posSolution.getTileList()) {
//                if(leftTile != rightTile &&  leftTile.interferesWith(rightTile))
//                    score--;
//            }
//
//        int[][] valueMap = posSolution.getCanvas().getValueMap();
//        for(PosTile myTile: posSolution.getTileList()) {
//            if(myTile.isInitialized()) {
//                int mapValue = valueMap[myTile.getXValue()][myTile.getYValue()];
//                score = score - Math.abs(myTile.getValue() - mapValue);
//            }
//        }
//
//        return SimpleScore.of(score);
//    }


}
