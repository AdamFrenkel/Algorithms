package edu.yu.da;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleEquationTest {
    @Test
    public void testRunTournament() {
        SimpleEquation se = new SimpleEquation();
        System.out.println(se.solveIt( new GeneticAlgorithmConfig(30,100,13, GeneticAlgorithmConfig.SelectionType.TOURNAMENT,.05,.64)));
        // 13 is the max
    }
    @Test
    public void testRunRoulette() {
        SimpleEquation se = new SimpleEquation();
        System.out.println(se.solveIt( new GeneticAlgorithmConfig(30,100000,13, GeneticAlgorithmConfig.SelectionType.ROULETTE,.05,.84)));
        // 13 is the max
    }
}