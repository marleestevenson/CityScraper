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
    private static final String CONSUMER_KEY = "NEp7ixVGJo2NPWhBXx7EJjT5e";
    private static final String CONSUMER_SECRET = "wlqXgHB1QSys8Zpf0yheOtjahpukr59cCtjDF3fCxxiflVWJXD";
    private static Query NEWYORK_NY,SEATTLE_WA,HOUSTON_TX,CHICAGO_IL,PHOENIX_AZ;
    private static HashMap<String, Query> citiesQueries;
    final static Logger logger = Logger.getLogger(Main.class);


    public static void main(String[] args) {

        initializeCitiesQueries();

        try {
            final TwitterScraper twitterScraper = new TwitterScraper(CONSUMER_KEY, CONSUMER_SECRET);

            for(Map.Entry<String, Query> entry : citiesQueries.entrySet()){
                logger.info("Scraping Twitter for " + entry.getKey());
                twitterScraper.search(entry.getKey(), entry.getValue());
            }

        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

    public static void initializeCitiesQueries() {
        NEWYORK_NY = new Query();
        NEWYORK_NY.setQuery("dog OR subway OR bus");
        NEWYORK_NY.setResultType(Query.ResultType.recent);
        NEWYORK_NY.setCount(100); // max possible results for one query
        NEWYORK_NY.setGeoCode(new GeoLocation(40.712784,-74.005941), 10.0, Query.Unit.mi);

        SEATTLE_WA = new Query();
        SEATTLE_WA.setQuery("dog OR subway OR bus");
        SEATTLE_WA.setResultType(Query.ResultType.recent);
        SEATTLE_WA.setCount(100); // max possible results for one query
        SEATTLE_WA.setGeoCode(new GeoLocation(47.606209,-122.332071), 10.0, Query.Unit.mi);

        HOUSTON_TX = new Query();
        HOUSTON_TX.setQuery("dog OR subway OR bus");
        HOUSTON_TX.setResultType(Query.ResultType.recent);
        HOUSTON_TX.setCount(100); // max possible results for one query
        HOUSTON_TX.setGeoCode(new GeoLocation(29.760427,-95.369803), 10.0, Query.Unit.mi);

        CHICAGO_IL = new Query();
        CHICAGO_IL.setQuery("dog OR subway OR bus");
        CHICAGO_IL.setResultType(Query.ResultType.recent);
        CHICAGO_IL.setCount(100); // max possible results for one query
        CHICAGO_IL.setGeoCode(new GeoLocation(41.878114,-87.629798), 10.0, Query.Unit.mi);

        PHOENIX_AZ = new Query();
        PHOENIX_AZ.setQuery("dog OR subway OR bus");
        PHOENIX_AZ.setResultType(Query.ResultType.recent);
        PHOENIX_AZ.setCount(100); // max possible results for one query
        PHOENIX_AZ.setGeoCode(new GeoLocation(33.448377,-112.074037), 10.0, Query.Unit.mi);

        citiesQueries = new HashMap<String,Query>();
        citiesQueries.put("New York, NY",NEWYORK_NY);
        citiesQueries.put("Seattle, WA",SEATTLE_WA);
        citiesQueries.put("Houston, TX",HOUSTON_TX);
        citiesQueries.put("Chicago, IL",CHICAGO_IL);
        citiesQueries.put("Phoenix, AZ",PHOENIX_AZ);
    }
}
