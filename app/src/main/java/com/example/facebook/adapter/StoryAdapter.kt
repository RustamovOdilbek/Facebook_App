package com.example.facebook.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.facebook.R
import com.example.facebook.model.Story

class StoryAdapter(var context: Context, var items: ArrayList<Story>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_story_view, parent, false)
        return StoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var item = items[position]
        if (holder is StoryViewHolder){
            var iv_background = holder.iv_background
            var iv_profile = holder.iv_profile
            var tv_fullname = holder.tv_fullname

            iv_background.setImageResource(item.profile)
            iv_profile.setImageResource(item.profile)
            tv_fullname.text = item.fullname
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class StoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_background: ImageView
        var iv_profile: ImageView
        var tv_fullname: TextView

        init {
            iv_background = view.findViewById(R.id.iv_background)
            iv_profile = view.findViewById(R.id.iv_profile)
            tv_fullname = view.findViewById(R.id.tv_fullname)
        }
    }
}