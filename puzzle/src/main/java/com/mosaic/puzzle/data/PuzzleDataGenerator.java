package com.mosaic.puzzle.data;

import com.mosaic.puzzle.domain.*;

import java.util.ArrayList;
import java.util.List;

public class PuzzleDataGenerator {

    // There is only one problem instance, so there is only one id
    public static final Long SINGLETON_ID = 1L;
    public static final Integer MAX_CELL_VALUE = 1;

  //  private static Logger logger = LoggerFactory.getLogger(MagicMosaicApplication.class);

    public PuzzleSolution createRandomPosSolution(int mapSize) {
        PuzzleSolution posSolution = new PuzzleSolution(SINGLETON_ID);
        posSolution.setMapSize(mapSize);
        posSolution.setXAxis(createXAxis(posSolution.getMapSize()));
        posSolution.setYAxis(createYAxis(posSolution.getMapSize()));
        posSolution.setCanvas(createRandomCanvas(mapSize));
        posSolution.setTileList(createRandomTiles(mapSize * mapSize, 9));
        return posSolution;
    }

    public PuzzleSolution createColumnValuePosSolution(int mapSize) {
        PuzzleSolution posSolution = new PuzzleSolution(SINGLETON_ID);
        posSolution.setMapSize(mapSize);
        posSolution.setXAxis(createXAxis(posSolution.getMapSize()));
        posSolution.setYAxis(createYAxis(posSolution.getMapSize()));
        posSolution.setCanvas(createColumnValueCanvas(mapSize));
        posSolution.setTileList(createColumnValueTiles(mapSize, mapSize));
        return posSolution;
    }

    private List<PuzzleXCoordinate> createXAxis(int n) {
        List<PuzzleXCoordinate> xAxis = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            PuzzleXCoordinate x = new PuzzleXCoordinate(i);
            xAxis.add(x);
        }
        return xAxis;
    }

    private List<PuzzleYCoordinate> createYAxis(int n) {
        List<PuzzleYCoordinate> yAxis = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            PuzzleYCoordinate y = new PuzzleYCoordinate(i);
            yAxis.add(y);
        }
        return yAxis;
    }

    private PuzzleCanvas createRandomCanvas(int size) {
        int[][] valueMap = new int[size][size];
        for(int i=0; i< size; i++) {
            for(int j=0; j< size; j++)
                valueMap[i][j] = (int) (Math.random() *10);
        }
        return new PuzzleCanvas(size, valueMap);
    }

    private List<PuzzleTile> createRandomTiles(int n, int maxValue) {
        List<PuzzleTile> tiles = new ArrayList<>();
        for(int i=0; i<n; i++) {
            tiles.add(new PuzzleTile((long) i, (int) (Math.random() * maxValue)));
        }
        return tiles;
    }

    private PuzzleCanvas createColumnValueCanvas(int size) {
        int[][] valueMap = new int[size][size];
        for(int i=0; i< size; i++) {
            for(int j=0; j< size; j++)
                valueMap[i][j] = j;
        }
        return new PuzzleCanvas(size, valueMap);
    }

    private List<PuzzleTile> createColumnValueTiles(int rows, int columns) {
        long id = 0L;
        List<PuzzleTile> tiles = new ArrayList<>();
        for(int i=0; i<rows; i++)
            for(int j=0; j<columns; j++) {
                tiles.add(new PuzzleTile(id++, j));
        }
        return tiles;
    }


}
