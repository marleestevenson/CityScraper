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

    public TwitterScraper(String consumerKey, String consumerSecret) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
    }

    public void search(String cityName, Query query) throws TwitterException {
        CityProcessor cp = new CityProcessor();
        authenticate();
        findTweets(cp, query);
        cp.getFinalRanking(cityName);
    }

    private void authenticate() throws TwitterException {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setApplicationOnlyAuthEnabled(true);
        twitter = new TwitterFactory(builder.build()).getInstance();

        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        token = twitter.getOAuth2Token();
    }

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
