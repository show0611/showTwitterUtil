package com.github.yossy0610.stu.listener

import com.github.yossy0610.replayLiker.core.STUCore
import com.github.yossy0610.stu.constructor.TodoManage
import twitter4j.Status
import twitter4j.TwitterException
import java.io.IOException
import java.util.*

/**
 * Created by yossy.0610 on 2016/06/12.
 */
object TwitterMyCommandListener {
    val run = Runtime.getRuntime()
    var gtwitter = STUCore.twitter!!
    var twitter = gtwitter.twitter
    var todoList = ArrayList<TodoManage>()

    @Throws(IOException::class, TwitterException::class)
    fun onStatus(status: Status) {
        var args: Array<String> = createCmdArgs(status.text)!!
        if (createCmdArgs(status.text) == null) return
        if (status.user.screenName != twitter.screenName) return

        when (args[0]) {
            "/tweetCount" -> {
                when (args[1]) {
                    "get" -> {
                        gtwitter.tweet(
                                "@${twitter.screenName}\nThe current number of posts: " +
                                        "${TweetCountListener.tweetCount} (including RT: ${TweetCountListener.retweetCount})"
                        )
                    }

                    "set" -> {
                        if (args.size > 3) {
                            TweetCountListener.retweetCount = args[3].toInt()
                        }
                        TweetCountListener.tweetCount = args[2].toInt()

                        gtwitter.tweet(
                                "@${twitter.screenName}\nTweet count set was successfully!\n" +
                                        "The current number of posts: " +
                                        "${TweetCountListener.tweetCount} (including RT: ${TweetCountListener.retweetCount})"
                        )
                    }
                }
            }

            "/cmd" -> {
                when (args[1]) {
                    "shutdown" -> {
                        run.exec("shutdown -s -t 00")
                    }
                    "restart" -> {
                        run.exec("shutdown -r -t 00")
                    }
                    "execute" -> {
                        var array: Array<String>? = null
                        for (i in 2..args.size) {
                            array!![i - 2] = args[i]
                        }
                        run.exec(array!!)
                    }
                }
            }

            "/restart" -> {
                when (args[1]) {
                    "nogui" -> {
                        run.exec("start \"${STUCore.APP_BAT}\" nogui ${TweetCountListener.tweetCount} ${TweetCountListener.retweetCount}")
                    }
                    else -> {
                        run.exec("start \"${STUCore.APP_BAT}\" ${TweetCountListener.tweetCount} ${TweetCountListener.retweetCount}")
                    }
                }
                run.exit(-1)
                System.exit(-1)
            }

            "/todo" -> {
                when (args[1]) {
                }
            }
        }
    }

    fun createCmdArgs(str: String): Array<String>? {
        if (str.startsWith("/")) {
            var args = str.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return args
        }
        return null
    }
}
