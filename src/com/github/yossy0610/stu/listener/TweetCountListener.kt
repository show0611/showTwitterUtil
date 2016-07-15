package com.github.yossy0610.stu.listener

import com.github.yossy0610.replayLiker.core.STUCore
import twitter4j.Status
import twitter4j.StatusDeletionNotice
import twitter4j.UserStreamAdapter

/**
 * Created by yossy.0610 on 2016/06/12.
 */
class TweetCountListener : UserStreamAdapter() {
    var twitter = STUCore.twitter!!.twitter

    override fun onStatus(status: Status) {
        if (status.user.screenName == twitter.screenName) {
            tweetCount++
            if (status.isRetweet) retweetCount++
        }
        TwitterMyCommandListener.onStatus(status)
    }

    override fun onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {
        if (statusDeletionNotice.userId == twitter.verifyCredentials().id) {
            tweetCount--
        }
    }

    companion object {
        var tweetCount = 0
        var retweetCount = 0
    }
}