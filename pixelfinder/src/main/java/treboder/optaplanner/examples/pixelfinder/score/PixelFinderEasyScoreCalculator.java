package treboder.optaplanner.examples.pixelfinder.score;

import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;
import treboder.optaplanner.examples.pixelfinder.domain.PixelFinder;

public class PixelFinderEasyScoreCalculator implements EasyScoreCalculator<PixelFinder, SimpleScore> {

    @Override
    public SimpleScore calculateScore(PixelFinder pixelFinder) {
        int score = 0;

        if(pixelFinder.getPixel().getXValue() != 5555)
            score-- ;

        if(pixelFinder.getPixel().getYValue() != 8888)
            score-- ;

        return SimpleScore.of(score);
    }

}
