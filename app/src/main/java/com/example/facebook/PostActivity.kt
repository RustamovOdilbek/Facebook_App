package com.example.facebook

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.URLUtil
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.bumptech.glide.Glide
import io.github.ponnamkarthik.richlinkpreview.MetaData
import io.github.ponnamkarthik.richlinkpreview.ResponseListener
import io.github.ponnamkarthik.richlinkpreview.RichPreview


class PostActivity : AppCompatActivity()  {
    var edit_link: EditText? = null
    var iv_check: ImageView? = null
    var iv_post: ImageView? = null
    var tv_link_name: TextView? = null
    var tv_link_ebout: TextView? = null
    var link_preview: LinearLayout? = null
    var isLink: Boolean = false
    var link: String = ""
    var image: String? = null
    var title: String? = null
    var about: String? = null
    var links: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        edit_link = findViewById(R.id.edit_link)
        iv_check = findViewById(R.id.iv_check)
        iv_post = findViewById(R.id.iv_post_link)
        tv_link_name = findViewById(R.id.tv_link_name_1)
        tv_link_ebout = findViewById(R.id.tv_link_ebout_1)
        link_preview = findViewById(R.id.link_preview)

        initLink()

        initFinish()
        initPostSend()
    }

    private fun initLink() {
        var data: MetaData?

        val richPreview = RichPreview(object : ResponseListener {
            override fun onData(metaData: MetaData) {
                data = metaData
                link_preview!!.visibility = View.VISIBLE
                if (data!!.imageurl == null) {
                    iv_post!!.visibility = View.GONE
                } else {
                    Glide.with(this@PostActivity).load(data!!.imageurl).into(iv_post!!)
                }
                tv_link_name!!.text = data!!.title
                tv_link_ebout!!.text = data!!.description

                image = data!!.imageurl
                title = data!!.title
                about = data!!.description
                links = edit_link?.text.toString()

            }

            override fun onError(e: Exception) {
            }
        })

        edit_link?.doAfterTextChanged {
            val text = edit_link!!.text.toString()

            val texts = text.split(" ")

            for (i in texts) {
                if (URLUtil.isValidUrl(i)) {
                    richPreview.getPreview(i)
                    isLink = true
                    link = i
                    link_preview?.setOnClickListener {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(i)))
                    }
                } else {
                    link_preview?.visibility = View.GONE
                    continue
                }
            }
        }
    }


    private fun initPostSend() {
        var edit_link = findViewById<EditText>(R.id.edit_link)
        var link = edit_link.toString()

        var btn_post = findViewById<Button>(R.id.btn_post)

        btn_post.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("image", "$image")
            intent.putExtra("title", "$title")
            intent.putExtra("about", "$about")
            intent.putExtra("link", "$links")
            setResult(RESULT_OK,intent)
            finish()
        }
    }

    private fun initFinish() {
        var iv_finish = findViewById<ImageView>(R.id.iv_finish)

        iv_finish.setOnClickListener( View.OnClickListener {
            intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        })
    }
}