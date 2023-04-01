package treboder.optaplanner.examples.pixelfinder.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import treboder.optaplanner.examples.pixelfinder.PixelfinderApplication;
import treboder.optaplanner.examples.pixelfinder.domain.Pixel;
import treboder.optaplanner.examples.pixelfinder.domain.PixelFinder;
import treboder.optaplanner.examples.pixelfinder.domain.XCoordinate;
import treboder.optaplanner.examples.pixelfinder.domain.YCoordinate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PixelFinderGenerator {

    // There is only one problem instance, so there is only one id
    public static final Long SINGLETON_PIXELFINDER_ID = 1L;

    private static Logger logger = LoggerFactory.getLogger(PixelfinderApplication.class);

    public PixelFinder createPixelFinder(int mapSize) {
        PixelFinder pixelFinder = new PixelFinder(SINGLETON_PIXELFINDER_ID);
        pixelFinder.setMapSize(mapSize);
        pixelFinder.setXAxis(createXAxis(pixelFinder.getMapSize()));
        pixelFinder.setYAxis(createYAxis(pixelFinder.getMapSize()));
        pixelFinder.setPixel(new Pixel(0L));
        return pixelFinder;
    }

    private List<XCoordinate> createXAxis(int n) {
        List<XCoordinate> xAxis = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            XCoordinate x = new XCoordinate(i);
            xAxis.add(x);
        }
        return xAxis;
    }

    private List<YCoordinate> createYAxis(int n) {
        List<YCoordinate> yAxis = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            YCoordinate y = new YCoordinate(i);
            yAxis.add(y);
        }
        return yAxis;
    }

}
