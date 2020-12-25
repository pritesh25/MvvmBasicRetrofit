package com.mindorks.framework

import okhttp3.ResponseBody

class ApiResponse {
    var model: StackModel?
    var throwable: Throwable?
    var responseBody: ResponseBody?
    var statusCode: Int?

    constructor(model: StackModel?, status: Int?) {
        this.model = model
        this.statusCode = status
        this.throwable = null
        this.responseBody = null
    }

    constructor(responseBody: ResponseBody?, status: Int?) {
        this.responseBody = responseBody
        this.statusCode = status
        this.model = null
        this.throwable = null
    }

    constructor(error: Throwable?) {
        this.throwable = error
        this.model = null
        this.statusCode = null
        this.responseBody = null
    }
}