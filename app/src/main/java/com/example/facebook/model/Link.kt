package com.example.facebook.model

import java.io.Serializable

class Link: Serializable{
    var tv_link: String? = null
    var iv_link_image: String? = null
    var tv_link_name: String? = null
    var tv_link_about: String? = null

    constructor(tv_link: String, iv_link_image: String, tv_link_name: String, tv_link_about: String){
        this.tv_link = tv_link
        this.iv_link_image = iv_link_image
        this.tv_link_name = tv_link_name
        this.tv_link_about = tv_link_about
    }
    override fun toString(): String {
        return tv_link + " : " + iv_link_image + " : " + tv_link_name + " : " + tv_link_about
    }
}
