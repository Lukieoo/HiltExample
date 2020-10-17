package com.lukieoo.hiltexample.intent

sealed class Intent {
    object GetBlogsEvent: Intent()

    object None: Intent()
}