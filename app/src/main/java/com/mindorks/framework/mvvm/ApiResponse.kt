package com.mindorks.framework.mvvm

class ApiResponse {
    var posts: StackModel?
    var error: Throwable?

    constructor(posts: StackModel?) {
        this.posts = posts
        error = null
    }

    constructor(error: Throwable?) {
        this.error = error
        posts = null
    }
}