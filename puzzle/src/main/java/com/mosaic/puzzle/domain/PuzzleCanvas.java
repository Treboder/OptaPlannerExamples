package com.mosaic.puzzle.domain;

public class PuzzleCanvas {

    private int size;
    private int[][] valueMap;

    // OptaPlanner needs no-arg constructor to create a planning clone
    public PuzzleCanvas(){}

    public PuzzleCanvas(int size) {
        this.size = size;
        this.valueMap = new int[size][size];
    }

    public PuzzleCanvas(int size, int[][] valueMap) {
        this.size = size;
        this.valueMap = valueMap;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[][] getValueMap() {
        return valueMap;
    }

    public void setValueMap(int[][] valueMap) {
        this.valueMap = valueMap;
    }

    public String toLoggerCanvas() {
        StringBuilder sb = new StringBuilder();
        int maxFixedDigits = 2;

        // print header
        sb.append("  | ");
        for(int i=0; i< this.size; i++) {
            String appendNext = fixedLengthString(String.valueOf(i), maxFixedDigits);
            sb.append(appendNext).append(" ");
        }
        sb.append("\n");

        // print header separating line
        int width = sb.length();
        sb.append("--|");
        sb.append("-".repeat(Math.max(0, width)));
        sb.append("\n");

        // print line by line
        for(int i=0; i< this.size; i++) {
            String rowIndex = fixedLengthString(String.valueOf(i), maxFixedDigits);
            sb.append(rowIndex).append("| ");
            for(int j=0; j< this.size; j++) {
                String appendNext = fixedLengthString(String.valueOf(valueMap[i][j]), maxFixedDigits);
                sb.append(appendNext).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static String fixedLengthString(String toPad, int length) {
        char fill = '0';
        return new String(new char[length - toPad.length()]).replace('\0', fill) + toPad;
    }

}
