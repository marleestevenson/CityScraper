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

    /**
     * Get Stanford NLP sentiment score based on tweet string
     * @param tweet string of tweet text
     * @return sentiment score (double)
     */
    public double getSentimentScore(String tweet) {
        nlp.init();
        return (double)nlp.findSentiment(tweet);
    }

    /**
     * Get average sentiment score based on the scraped tweets
     * @param tweets list of tweet sentiment scores
     * @return average sentiment score for list of tweets
     */
    public double getAverageScore(List<Double> tweets) {
        return tweets.stream().mapToDouble(a -> a).average().orElse(0);
    }

    /**
     * Processing tweets by adding their sentiment score to a list
     * @param tweets list of Status tweets
     */
    public void processTweets(List<Status> tweets){
        for (Status tweet : tweets) {
            String tweetText = tweet.getText();
            citySentimentScores.add(getSentimentScore(tweetText));
        }
    }

    /**
     * Log final average ranking for given city
     * @param cityName city name to print in log
     */
    public void getFinalRanking(String cityName) {
        logger.info("*Score for " + cityName + ": " + getAverageScore(citySentimentScores) + "*");
    }

    /**
     * Getter for list of sentiment scores per city
     * @return list of sentiment scores
     */
    public List<Double> getCitySentimentScores() {
        return citySentimentScores;
    }

}
