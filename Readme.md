#City Scraper Project#

##Prerequisites:##
You will need to get a Twitter consumer key / consumer secret to run program.

Within Main.java, please input your consumer key and consumer secret:

* private static final String CONSUMER_KEY = "xxx";
* private static final String CONSUMER_SECRET = "xxx";

##To run:##
Unzip program and go to directory
>`unzip MarleeStevensonCityScraper.zip`
>`cd CityScraper`

To build
>`mvn clean install`

To run program
>`mvn exec:java -Dexec.mainClass="com.condenast.Main" -Dexec.cleanupDaemonThreads=false`

The program will output scores for each of the selected cities in the format of:
>`*Score for Phoenix, AZ: 1.46*`