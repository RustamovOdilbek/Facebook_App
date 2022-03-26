package com.example.facebook.model

class Feed {
    var isHeader: Boolean = false
    var post: Post? = null
    var stories: ArrayList<Story> = ArrayList()
    var link: Link? = null
    var linkNum:Int? = null

    constructor(){
        this.isHeader = true
    }

    constructor(post: Post){
        this.post = post
        this.isHeader = false
    }

    constructor(stories: ArrayList<Story>){
        this.stories = stories
        this.isHeader = false
    }
    constructor(link: Link, linkNum: Int){
        this.link = link
        this.isHeader = false
        this.linkNum = linkNum
    }
}