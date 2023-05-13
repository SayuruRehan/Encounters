package com.mad.mad_encounters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class BeginingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.begining)
    }

    fun navigatefirstpage(view: View) {
        val intent = Intent(this, FirstpageActivity::class.java)
        startActivity(intent)
    }
}