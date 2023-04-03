package treboder.optaplanner.examples.pixelfinder.score;

import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;
import treboder.optaplanner.examples.pixelfinder.domain.PixelFinder;

public class PixelFinderEasyScoreCalculator implements EasyScoreCalculator<PixelFinder, SimpleScore> {

    @Override
    public SimpleScore calculateScore(PixelFinder pixelFinder) {
        int score = -1;

        if(pixelFinder.getPixel().getXValue() == (pixelFinder.getMapSize()/2))
            if(pixelFinder.getPixel().getYValue() == (pixelFinder.getMapSize()/2))
                score = 0 ;

        return SimpleScore.of(score);
    }

//    @Override
//    public SimpleScore calculateScore(PixelFinder pixelFinder) {
//        int score = 0;
//
//        if(pixelFinder.getPixel().getXValue() != (pixelFinder.getMapSize()/2))
//            score-- ;
//
//        if(pixelFinder.getPixel().getYValue() != (pixelFinder.getMapSize()/2))
//            score-- ;
//
//        return SimpleScore.of(score);
//    }



}
