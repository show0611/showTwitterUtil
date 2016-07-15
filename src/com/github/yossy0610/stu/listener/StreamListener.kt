package com.github.yossy0610.stu.listener

import com.github.yossy0610.gdutil.constructors.GDTwitter
import com.github.yossy0610.replayLiker.core.STUCore
import java.io.IOException
import twitter4j.*

/**
 * Created by yossy.0610 on 2016/06/02.
 */
class StreamListener @Throws(TwitterException::class)
constructor(private val twitter: Twitter) : UserStreamAdapter() {
    override fun onStatus(status: Status) {
        try {
            if (!status.isFavorited && status.user.screenName != twitter.screenName) {
                if (status.text.contains(twitter.screenName)) {
                    twitter.createFavorite(status.id)
                }

                if (status.quotedStatus != null && status.quotedStatus.user.screenName.contains(twitter.screenName)) {
                    twitter.createFavorite(status.id)
                }
            }
            GDTwitter.print("[" + status.user.screenName + "] " + status.text)
        } catch (e: TwitterException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    override fun onQuotedTweet(source: User, target: User, quotingTweet: Status) {
        try {
            if (target.screenName == twitter.screenName && source.screenName != twitter.screenName) {
                twitter.createFavorite(quotingTweet.id)
            }
        } catch (e: TwitterException) {
            e.printStackTrace()
        }
    }
}
