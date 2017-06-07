package com.condenast;

import twitter4j.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * This program scrapes Twitter for 5 chosen cities
 * It then ranks these cities based on their quailty of
 * public transit and dog friendliness.
 */

public class Main
{
    private static final String CONSUMER_KEY = "xxx";
    private static final String CONSUMER_SECRET = "xxx";
    private static Query NEWYORK_NY,SEATTLE_WA,HOUSTON_TX,CHICAGO_IL,PHOENIX_AZ;
    private static HashMap<String, Query> citiesQueries;
    final static Logger logger = Logger.getLogger(Main.class);


    public static void main(String[] args) {

        // initialize queries for each selected city
        initializeCitiesQueries();

        try {
            // initialize twitter scraper class with key/secret
            final TwitterScraper twitterScraper = new TwitterScraper(CONSUMER_KEY, CONSUMER_SECRET);

            for(Map.Entry<String, Query> entry : citiesQueries.entrySet()){
                // for each query, scrape twitter and find average sentiment score
                logger.info("Scraping Twitter for " + entry.getKey());
                twitterScraper.search(entry.getKey(), entry.getValue());
            }

        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

    /**
     * initialize queries for each selected city
     */
    public static void initializeCitiesQueries() {
        NEWYORK_NY = new Query();
        NEWYORK_NY.setQuery("dog OR subway OR bus"); // dog friendliness and public transit
        NEWYORK_NY.setResultType(Query.ResultType.recent); // query most recent tweets
        NEWYORK_NY.setCount(100); // max possible results for one query
        NEWYORK_NY.setGeoCode(new GeoLocation(40.712784,-74.005941), 10.0, Query.Unit.mi); // city coordinate + 10 mile radius

        SEATTLE_WA = new Query();
        SEATTLE_WA.setQuery("dog OR subway OR bus"); // dog friendliness and public transit
        SEATTLE_WA.setResultType(Query.ResultType.recent); // query most recent tweets
        SEATTLE_WA.setCount(100); // max possible results for one query
        SEATTLE_WA.setGeoCode(new GeoLocation(47.606209,-122.332071), 10.0, Query.Unit.mi); // city coordinate + 10 mile radius

        HOUSTON_TX = new Query();
        HOUSTON_TX.setQuery("dog OR subway OR bus"); // dog friendliness and public transit
        HOUSTON_TX.setResultType(Query.ResultType.recent); // query most recent tweets
        HOUSTON_TX.setCount(100); // max possible results for one query
        HOUSTON_TX.setGeoCode(new GeoLocation(29.760427,-95.369803), 10.0, Query.Unit.mi); // city coordinate + 10 mile radius

        CHICAGO_IL = new Query();
        CHICAGO_IL.setQuery("dog OR subway OR bus"); // dog friendliness and public transit
        CHICAGO_IL.setResultType(Query.ResultType.recent); // query most recent tweets
        CHICAGO_IL.setCount(100); // max possible results for one query
        CHICAGO_IL.setGeoCode(new GeoLocation(41.878114,-87.629798), 10.0, Query.Unit.mi); // city coordinate + 10 mile radius

        PHOENIX_AZ = new Query();
        PHOENIX_AZ.setQuery("dog OR subway OR bus"); // dog friendliness and public transit
        PHOENIX_AZ.setResultType(Query.ResultType.recent); // query most recent tweets
        PHOENIX_AZ.setCount(100); // max possible results for one query
        PHOENIX_AZ.setGeoCode(new GeoLocation(33.448377,-112.074037), 10.0, Query.Unit.mi); // city coordinate + 10 mile radius

        // add all queries to a hashmap with their corresponding city name
        citiesQueries = new HashMap<String,Query>();
        citiesQueries.put("New York, NY",NEWYORK_NY);
        citiesQueries.put("Seattle, WA",SEATTLE_WA);
        citiesQueries.put("Houston, TX",HOUSTON_TX);
        citiesQueries.put("Chicago, IL",CHICAGO_IL);
        citiesQueries.put("Phoenix, AZ",PHOENIX_AZ);
    }
}
