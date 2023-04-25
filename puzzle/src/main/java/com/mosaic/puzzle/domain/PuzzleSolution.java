package com.mosaic.puzzle.domain;

import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.solution.*;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@PlanningSolution
public class PuzzleSolution {

    @PlanningId         // OptaPlanner
    @Id @GeneratedValue // Hibernate
    Long id;

    private int mapSize;

    private PuzzleCanvas canvas;

    public PuzzleCanvas getCanvas() {
        return canvas;
    }

    public void setCanvas(PuzzleCanvas canvas) {
        this.canvas = canvas;
    }

    @ValueRangeProvider
    @ProblemFactCollectionProperty
    private List<PuzzleXCoordinate> xAxis;

    @ValueRangeProvider
    @ProblemFactCollectionProperty
    private List<PuzzleYCoordinate> yAxis;

    @PlanningEntityCollectionProperty
    List<PuzzleTile> tileList;

    @PlanningScore
    private HardSoftScore score;

    // OptaPlanner needs no-arg constructor to create a planning clone
    public PuzzleSolution() {
    }

    public PuzzleSolution(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public List<PuzzleXCoordinate> getXAxis() {
        return xAxis;
    }

    public void setXAxis(List<PuzzleXCoordinate> xAxis) {
        this.xAxis = xAxis;
    }

    public List<PuzzleYCoordinate> getYAxis() {
        return yAxis;
    }

    public void setYAxis(List<PuzzleYCoordinate> yAxis) {
        this.yAxis = yAxis;
    }

    public List<PuzzleTile> getTileList() {
        return tileList;
    }

    public void setTileList(List<PuzzleTile> tileList) {
        this.tileList = tileList;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "#" + this.tileList.size() + " tiles";
    }

    public String toLoggerTiles() {
        StringBuilder sb = new StringBuilder();
        int maxFixedDigits = 2;

        // print header
        sb.append("  | ");
        for(int i=0; i< this.mapSize; i++) {
            String appendNext = fixedLengthStringPadZero(String.valueOf(i), maxFixedDigits);
            sb.append(appendNext).append(" ");
        }
        sb.append("\n");

        // print header separating line
        int width = sb.length();
        sb.append("--|");
        sb.append("-".repeat(Math.max(0, width)));
        sb.append("\n");

        // print line by line while iterating over map and tiles
        for(int i=0; i< this.mapSize; i++) {
            String rowIndex = fixedLengthStringPadZero(String.valueOf(i), maxFixedDigits);
            sb.append(rowIndex).append("| ");
            for(int j=0; j< this.mapSize; j++) {
                boolean cellFilled = false;
                for (PuzzleTile myTile : this.tileList ) {
                    if(myTile.getXValue() == i && myTile.getYValue() == j) {
                        String appendNext = fixedLengthStringPadZero(String.valueOf(myTile.getValue()), maxFixedDigits);
                        sb.append(appendNext).append(" ");
                        cellFilled = true;
                        break;
                    }
                }
                if(!cellFilled)
                    sb.append(fixedLengthStringPadSpace("-", maxFixedDigits)).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public String toLoggerDeltas() {
        StringBuilder sb = new StringBuilder();
        int maxFixedDigits = 2;

        // print header
        sb.append("  | ");
        for(int i=0; i< this.mapSize; i++) {
            String appendNext = fixedLengthStringPadZero(String.valueOf(i), maxFixedDigits);
            sb.append(appendNext).append(" ");
        }
        sb.append("\n");

        // print header separating line
        int width = sb.length();
        sb.append("--|");
        sb.append("-".repeat(Math.max(0, width)));
        sb.append("\n");

        // print line by line while iterating over map and tiles
        for(int i=0; i< this.mapSize; i++) {
            String rowIndex = fixedLengthStringPadZero(String.valueOf(i), maxFixedDigits);
            sb.append(rowIndex).append("| ");
            for(int j=0; j< this.mapSize; j++) {
                boolean cellFilled = false;
                for (PuzzleTile myTile : this.tileList ) {
                    if(myTile.getXValue() == i && myTile.getYValue() == j) {
                        int delta = Math.abs(myTile.getValue() - this.getCanvas().getValueMap()[i][j]);
                        String appendNext = fixedLengthStringPadZero(String.valueOf(delta), maxFixedDigits);
                        sb.append(appendNext).append(" ");
                        cellFilled = true;
                        break;
                    }
                }
                if(!cellFilled)
                    sb.append(fixedLengthStringPadSpace("-", maxFixedDigits)).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static String fixedLengthStringPadZero(String toPad, int length) {
        char fill = '0';
        return new String(new char[length - toPad.length()]).replace('\0', fill) + toPad;
    }

    public static String fixedLengthStringPadSpace(String toPad, int length) {
        char fill = ' ';
        return new String(new char[length - toPad.length()]).replace('\0', fill) + toPad;
    }

}
