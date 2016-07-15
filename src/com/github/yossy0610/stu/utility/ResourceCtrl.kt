package com.github.yossy0610.replayLiker.utility

import com.github.yossy0610.replayLiker.core.STUCore
import org.yaml.snakeyaml.Yaml
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileWriter
import java.io.InputStreamReader
import java.net.URL
import java.net.URLClassLoader
import java.util.stream.Stream

/**
 * Created by yossy.0610 on 2016/05/28.
 */

class ResourceCtrl {
    var file: File? = null
    var array: Array<URL>? = null
    var fw: FileWriter? = null
    var br: BufferedReader? = null
    var strs: Stream<String>? = null
    var sb: StringBuilder = StringBuilder()

    fun resourceDump(fromPath: String, toPath: String) {
        try {
            file = File(toPath)
            if (!file!!.exists()) {
                file!!.createNewFile()
            }
            fw = FileWriter(file)
            br = BufferedReader(InputStreamReader(this.javaClass.classLoader.getResourceAsStream(fromPath)))
            strs = br!!.lines()
            for (str in strs!!.toArray()) {
                sb.append("$str\n")
            }

            fw!!.write(sb.toString())
            fw!!.close()
            br!!.close()

            array!!.set(0, file!!.toURI().toURL());
            STUCore.ver = Yaml().load(URLClassLoader.newInstance(array).getResourceAsStream(file!!.getName())) as String
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}