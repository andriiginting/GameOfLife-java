package com.gojek.GojekGol;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CellTest {

    @Test
    public void calculateNeigborTest(){
        Cell cells = new Cell();

        int[][] blocks = {
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,1,1,1,0},
                {0,0,0,0,0},
                {0,0,0,0,0}};

        assertEquals(1,cells.calculateNeighbors(blocks,2,1));
    }

    @Test
    public void newGeneration(){
        Cell cells = new Cell();
        int[][] blocks = {
                {0,0,0,0,0},
                {0,0,1,0,0},
                {0,0,1,0,0},
                {0,0,1,0,0},
                {0,0,0,0,0}};

        assertEquals(0,cells.reproduce(blocks,0,    0));
    }

    @Test
    public void testReproduce(){
        Cell cells = new Cell();
        int[][] blocks = {
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,1,1,1,0},
                {0,0,0,0,0},
                {0,0,0,0,0}};

        assertEquals(0, cells.reproduce(blocks,0,0));
        assertEquals(0, cells.reproduce(blocks,0,1));
        assertEquals(0, cells.reproduce(blocks,0,2));
    }
}
