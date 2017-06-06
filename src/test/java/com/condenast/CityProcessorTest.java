package com.condenast;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


/**
 * Unit tests for CityProcessor class
 */
public class CityProcessorTest {
    private static CityProcessor cp;

    @BeforeClass
    public static void initCalculator() {
        cp = new CityProcessor();
    }

    @Test
    public void testGetAverageScore() {
        List<Double> tweetScores = Arrays.asList(1.25,3.43,0.24,-8.2,-4.1);
        double result = cp.getAverageScore(tweetScores);
        assertEquals(-1.476, result, 0.001);
    }

    @Test
    public void testGetSentimentScore() {
        String tweet = "I really love dogs";
        double score = cp.getSentimentScore(tweet);
        assertEquals(3.0,score,0.1);
    }
}
