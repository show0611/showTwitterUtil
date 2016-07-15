package com.github.yossy0610.stu.Scheduler

import com.github.yossy0610.gdutil.constructors.GDSchedulerTask
import com.github.yossy0610.replayLiker.core.STUCore
import com.github.yossy0610.stu.listener.TweetCountListener
import twitter4j.TwitterException
import java.util.*

/**
 * Created by yossy.0610 on 2016/06/12.
 */
class TweetCountSchedule : GDSchedulerTask() {
    var twitter = STUCore.twitter!!
    var dates = Date()
    var tweetCount = 0
    var retweetCount = 0

    override fun run() {
        try {
            synchronized (TweetCountListener.tweetCount as Any) {
                tweetCount = TweetCountListener.tweetCount
                TweetCountListener.tweetCount = 0
            }
            synchronized (TweetCountListener.retweetCount as Any) {
                retweetCount = TweetCountListener.retweetCount
                TweetCountListener.retweetCount = 0
            }

            twitter.tweet("@${twitter.twitter.screenName}\n" +
                    "Post the number of ${"${dates.getMonth() + 1}-${dates.getDate()}"}: " +
                    "$tweetCount (including RT: $retweetCount)")
        } catch (e: TwitterException) {
            e.printStackTrace()
        }

    }
}