package com.sean.chickensoupforthesoul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sean.chickensoupforthesoul.api.ApiUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_request.setOnClickListener {
            ApiUtil.requestChickenSoul()
        }
    }
}
