package com.condenast;

import org.apache.log4j.Logger;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marlee.stevenson on 6/5/17.
 * Processes each city based on list of tweets
 */
public class CityProcessor {
    private final List<Double> citySentimentScores = new ArrayList<>();
    private static final NLPComponent nlp = new NLPComponent();
    final static Logger logger = Logger.getLogger(CityProcessor.class);

    public double getSentimentScore(String tweet) {
        nlp.init();
        return (double)nlp.findSentiment(tweet);
    }

    public double getAverageScore(List<Double> tweets) {
        return tweets.stream().mapToDouble(a -> a).average().orElse(0);
    }

    public void processTweets(List<Status> tweets){
        for (Status tweet : tweets) {
            String tweetText = tweet.getText();
            citySentimentScores.add(getSentimentScore(tweetText));
        }

    }

    public void getFinalRanking(String cityName) {
        logger.info("*Score for " + cityName + ": " + getAverageScore(citySentimentScores) + "*");
    }

    public List<Double> getCitySentimentScores() {
        return citySentimentScores;
    }

}
