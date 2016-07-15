package com.github.yossy0610.replayLiker.core

import com.github.yossy0610.gdutil.constructors.GDDailyIterator
import com.github.yossy0610.gdutil.constructors.GDScheduler
import com.github.yossy0610.gdutil.constructors.GDTwitter
import com.github.yossy0610.replayLiker.utility.ResourceCtrl
import com.github.yossy0610.stu.Scheduler.TweetCountSchedule
import com.github.yossy0610.stu.listener.StreamListener
import com.github.yossy0610.stu.listener.TweetCountListener
import com.github.yossy0610.stu.window.MainWindow
import java.util.*
import javax.swing.JTextArea

/**
 * Created by yossy.0610 on 2016/05/28.
 */
object STUCore {
    val C_SECRET = "nc9sfOk6sJYepTDO4eAwoz0ihmWtzUqKH93aQDHzWcRCm8t3ua"
    val C_KEY = "1ReN0pLFjj2vQnR8VHYmUtqw3"
    val APP_BAT = "C:/Users/yossy.0610/Documents/Programs/ReplyLiker/out/artifacts/ReplyLiker_jar/start.bat"
    var textArea: JTextArea? = null
    var twitter: GDTwitter? = null
    var main: MainWindow? = null
    var ver: String? = null
    var date = Date()
    var scheduler = GDScheduler()

    @JvmStatic fun main(args: Array<String>) {
        if (args.size != 0) {
            if (args[0].equals("nogui")) {
                twitter = GDTwitter(C_KEY, C_SECRET)
                if (args.size >= 2) TweetCountListener.tweetCount = args[1].toInt()
                if (args.size == 3) TweetCountListener.retweetCount = args[2].toInt()
            } else {
                main = MainWindow()
                textArea = main!!.textArea1
                twitter = GDTwitter(textArea, C_KEY, C_SECRET)
                if (args.size >= 1) TweetCountListener.tweetCount = args[0].toInt()
                if (args.size == 2) TweetCountListener.retweetCount = args[1].toInt()
            }
        } else {
            main = MainWindow()
            textArea = main!!.textArea1
            twitter = GDTwitter(textArea, C_KEY, C_SECRET)
        }

        twitter!!.checkProperties()
        scheduler.schedule(TweetCountSchedule(), GDDailyIterator(0, 0, 0))
        twitter!!.addUserStream(StreamListener(twitter!!.twitter), TweetCountListener())
        twitter!!.startRegularlyTweet("【定期】\n" +
                "#Discordはいいぞ！\n" +
                "SlackとSkypeを足して2で割った感じなんだけどさ\n" +
                "UI綺麗だし、グループ(Serverだけどね)に細かく権限設定も出来るらしいし\n" +
                "通話も音声Onlyなら出来るしねｗ\n\n" +
                "是非、Installして欲しいものだw", "00:00:01")
        twitter!!.startRegularlyTweet("【定期】\n" +
                "#IntelliJ_IDEAはいいぞ！\n" +
                "#Discordはいいぞ！\n" +
                "#Kotlinはいいぞ！\n" +
                "#Javaはいいぞ！", "00:00:15")
        twitter!!.startRegularlyTweet("【定期】\n" +
                "Discordでグル作ったのでフォロワーさんやそのお友達とかで是非いらしてくださいなｗ\n" +
                "URL: https://discord.gg/4ECnH", "00:00:02")
        twitter!!.startUserStream()
    }
}
