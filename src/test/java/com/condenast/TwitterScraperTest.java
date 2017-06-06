package com.condenast;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.TwitterException;


/**
 * Unit tests for TwitterScraper class
 */
public class TwitterScraperTest {
    private static TwitterScraper ts;
    private static final String CONSUMER_KEY = "xxx";
    private static final String CONSUMER_SECRET = "xxx";

    @BeforeClass
    public static void initCalculator() {
        ts = new TwitterScraper(CONSUMER_KEY, CONSUMER_SECRET);
    }

    @Test
    public void testMaxTweets() {
        int maxTweets = ts.getMaxTweets();
        assertEquals(200,maxTweets);
    }

    @Test(expected = TwitterException.class)
    public void testSearchBadAuthentication() throws TwitterException{
        Query NEWYORK_NY = new Query();
        NEWYORK_NY.setQuery("dog OR subway OR bus");
        NEWYORK_NY.setResultType(Query.ResultType.recent);
        NEWYORK_NY.setCount(100); // max possible results for one query
        NEWYORK_NY.setGeoCode(new GeoLocation(40.712784, -74.005941), 10.0, Query.Unit.mi);

        ts.search("New York, NY",NEWYORK_NY);
    }
}
