package com.condenast;

import org.apache.log4j.Logger;
import twitter4j.*;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by marlee.stevenson on 6/5/17.
 * Scrapes twitter for [maxTweets] tweets per query
 */
public class TwitterScraper {

    private final String consumerKey;
    private final String consumerSecret;
    private OAuth2Token token;
    private Twitter twitter;
    private static final int maxTweets = 200;
    final static Logger logger = Logger.getLogger(TwitterScraper.class);

    /**
     * Initialize TwitterScraper class with required key/secret
     * @param consumerKey key for Twitter API
     * @param consumerSecret secret for Twitter API
     */
    public TwitterScraper(String consumerKey, String consumerSecret) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
    }

    /**
     * Authenticates, scrapes tweets, and gets ranking per city
     * @param cityName String of city name
     * @param query query for city
     * @throws TwitterException
     */
    public void search(String cityName, Query query) throws TwitterException {
        CityProcessor cp = new CityProcessor();
        authenticate();
        findTweets(cp, query);
        cp.getFinalRanking(cityName);
    }

    /**
     * Authenticate Twitter API key with OAuth
     * @throws TwitterException
     */
    private void authenticate() throws TwitterException {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setApplicationOnlyAuthEnabled(true);
        twitter = new TwitterFactory(builder.build()).getInstance();

        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        token = twitter.getOAuth2Token();
    }

    /**
     * Scrape twitter and process tweets
     * @param cp CityProcessor instance
     * @param query query for city
     * @throws TwitterException
     */
    private void findTweets(CityProcessor cp, Query query) throws TwitterException {
        int count = 0;

        QueryResult queryResult = twitter.search(query);
        count += queryResult.getCount();

        while (queryResult.hasNext() && count < maxTweets) {
            cp.processTweets(queryResult.getTweets());
            queryResult = twitter.search(queryResult.nextQuery());
            count += queryResult.getCount();
        }

        logger.info(String.format("Found %d tweets", count));
    }

    public int getMaxTweets() {
        return maxTweets;
    }
}
