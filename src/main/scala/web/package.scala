package object web {

  import util.Properties
  import twitter4j._

  def tweet(wordMap: oracle.WordMap) {
    val twitterConfig = new twitter4j.conf.ConfigurationBuilder()
      .setOAuthConsumerKey("9JqqFKjWiA435oCGU9yuuM1MY")
      .setOAuthConsumerSecret(Properties.envOrElse("API_SECRET", ""))
      .setOAuthAccessToken("3012148274-YO67BWOFQHyQT0ff7DXPrsLtaemFsxSCfF8hmci")
      .setOAuthAccessTokenSecret(Properties.envOrElse("ACCESS_TOKEN_SECRET", ""))
      .build()

    val twitter = new TwitterFactory(twitterConfig).getInstance()
    twitter.updateStatus(oracle.tellFortune(wordMap).take(140))
  }
}
