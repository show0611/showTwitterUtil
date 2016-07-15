package com.github.yossy0610.stu.constructor

import java.util.*

/**
 * Created by yossy.0610 on 2016/06/13.
 */
class TodoManage {
    var comment: String? = null
    var limit: Date? = null

    constructor(comment: String, limit: Date) {
        this.comment = comment
        this.limit = limit
    }
}