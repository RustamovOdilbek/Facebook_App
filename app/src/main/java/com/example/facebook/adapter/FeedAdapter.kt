package com.example.facebook.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.facebook.App
import com.example.facebook.PostActivity
import com.example.facebook.R
import com.example.facebook.model.Feed
import com.example.facebook.model.Story
import com.google.android.material.imageview.ShapeableImageView

class FeedAdapter(var context: Context, var items: ArrayList<Feed>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_ITEM_HEAD = 0
    private val TYPE_ITEM_STORY = 1
    private val TYPE_ITEM_POST = 2
    private val TYPE_ITEM_LINK = 3

    var myonclick:(() -> Unit)?=null

    override fun getItemViewType(position: Int): Int {
        var feed = items[position]

        if (feed.isHeader)
            return TYPE_ITEM_HEAD
        else if (feed.stories.size > 0)
            return TYPE_ITEM_STORY
        else if (feed.linkNum == 100){
            return TYPE_ITEM_LINK
        }
        return TYPE_ITEM_POST
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ITEM_HEAD){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_header_view, parent, false)
            return HeadViewHolder(context, view)
        }else if (viewType == TYPE_ITEM_STORY){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed_story, parent, false)
            return StoryViewHolder(context, view)
        }else if (viewType == TYPE_ITEM_LINK){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post_link, parent, false)
            return LinkViewHolder(view)
        }
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is HeadViewHolder){
            var tv_mimd = holder.tv_mind

            tv_mimd.setOnClickListener(View.OnClickListener {
                myonclick?.invoke()
            })
        }
        if (holder is StoryViewHolder){
            holder.apply {
                if (adapter == null){
                    adapter = StoryAdapter(context, item.stories)
                    recyclerView.adapter = adapter
                }
            }
        }
        if (holder is PostViewHolder){
            var iv_profile = holder.iv_profile
            var iv_photo = holder.iv_photo
            var tv_fullname = holder.tv_fullname

            iv_profile.setImageResource(item.post!!.profile)
            iv_photo.setImageResource(item.post!!.post)
            tv_fullname.text = item.post!!.fullname
            tv_fullname.text = position.toString()
        }

        if (holder is LinkViewHolder){
            var item = items[position]

            var tv_link = holder.tv_link
            var iv_link_image = holder.iv_link_image
            var tv_link_name = holder.tv_link_name
            var tv_link_about = holder.tv_link_about

            tv_link.text = item.link!!.tv_link
            Glide.with(context).load(item.link?.iv_link_image).into(iv_link_image)
            tv_link_name.text = item.link?.tv_link_name
            tv_link_about.text = item.link?.tv_link_about
        }
    }

    private fun refreshAdapter(items: ArrayList<Story>, recyclerView: RecyclerView) {
        val adapter = StoryAdapter(App.instance, items)
        recyclerView.adapter = adapter
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class LinkViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_link: TextView
        var iv_link_image: ImageView
        var tv_link_name: TextView
        var tv_link_about: TextView

        init {
            tv_link = view.findViewById(R.id.tv_link)
            iv_link_image = view.findViewById(R.id.iv_link_image)
            tv_link_name = view.findViewById(R.id.tv_link_name)
            tv_link_about = view.findViewById(R.id.tv_link_ebout)
        }
    }

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_profile: ShapeableImageView
        var iv_photo: ImageView
        var tv_fullname: TextView

        init {
            iv_profile = view.findViewById(R.id.iv_profile)
            iv_photo = view.findViewById(R.id.iv_profile)
            tv_fullname = view.findViewById(R.id.tv_fullname)
        }
    }

    class StoryViewHolder(context: Context, view: View) : RecyclerView.ViewHolder(view) {
        var recyclerView: RecyclerView
        var adapter: StoryAdapter? = null
        init {
            recyclerView = view.findViewById(R.id.recyclerView)
            val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.setLayoutManager(manager)
        }
    }

    class HeadViewHolder(context: Context, view: View) : RecyclerView.ViewHolder(view) {
        var tv_mind: TextView

        init {
            tv_mind = view.findViewById(R.id.tv_mind)
        }
    }

}