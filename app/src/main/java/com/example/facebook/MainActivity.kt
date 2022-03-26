package com.example.facebook

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facebook.adapter.FeedAdapter
import com.example.facebook.model.Feed
import com.example.facebook.model.Link
import com.example.facebook.model.Post
import com.example.facebook.model.Story

class MainActivity : AppCompatActivity() {
     var image:String?=null
     var about:String?=null
    private lateinit var adapter:FeedAdapter
     var title:String?=null
     var link:String?=null
    lateinit var recyclerView: RecyclerView
    private lateinit var feeds:ArrayList<Feed>

    var links = ArrayList<Link>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    fun initViews(){
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 1)

        refreshAdapter(getAllFeeds())
    }

    private fun refreshAdapter(items: ArrayList<Feed>) {
        adapter =  FeedAdapter(this, items)
        recyclerView.adapter = adapter
        adapter.myonclick={
            openPostActivity()
        }
    }

    var postActivity=registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){result->
        if (result.resultCode==Activity.RESULT_OK){
            val data=result.data
             image=data!!.getStringExtra("image")!!
             title = data.getStringExtra("title")!!
             about = data.getStringExtra("about")!!
             link = data.getStringExtra("link")!!
            feeds.add(2,Feed(Link(link!!,image!!,title!!,about!!),100))
            adapter.notifyDataSetChanged()

        }
    }

    private fun openPostActivity() {
        val intent=Intent(this,PostActivity::class.java)
        postActivity.launch(intent)
    }

    private fun getAllFeeds(): ArrayList<Feed> {
        val stories = ArrayList<Story>()
        stories.add(Story(R.drawable.im_user_1, "Rustamov Odilbek"))
        stories.add(Story(R.drawable.im_user_1, "Rustamov Odilbek"))
        stories.add(Story(R.drawable.im_user_1, "Rustamov Odilbek"))
        stories.add(Story(R.drawable.im_user_1, "Rustamov Odilbek"))
        stories.add(Story(R.drawable.im_user_1, "Rustamov Odilbek"))
        stories.add(Story(R.drawable.im_user_1, "Rustamov Odilbek"))
        stories.add(Story(R.drawable.im_user_1, "Rustamov Odilbek"))

        feeds = ArrayList<Feed>()
        //head
        feeds.add(Feed())
        //story
        feeds.add(Feed(stories))
        //post


//        for (i in 0..links.size-1){
//            feeds.add(Feed(links[i], 100))
//        }


//        var image = intent.getStringExtra("image")
//        var title = intent.getStringExtra("title")
//        var about = intent.getStringExtra("about")
//        var link = intent.getStringExtra("link")


        feeds.add(Feed(Post(R.drawable.im_user_1, "Odilbek", R.drawable.im_post_1)))
        feeds.add(Feed(Post(R.drawable.im_user_1, "Odilbek", R.drawable.im_post_2)))
        feeds.add(Feed(Post(R.drawable.im_user_1, "Odilbek", R.drawable.im_post_3)))
        feeds.add(Feed(Post(R.drawable.im_user_1, "Odilbek", R.drawable.im_post_4)))
        feeds.add(Feed(Post(R.drawable.im_user_1, "Odilbek", R.drawable.im_post_5)))
        feeds.add(Feed(Post(R.drawable.im_user_1, "Odilbek", R.drawable.im_post_1)))
        feeds.add(Feed(Post(R.drawable.im_user_1, "Odilbek", R.drawable.im_post_2)))

        return feeds
    }
}