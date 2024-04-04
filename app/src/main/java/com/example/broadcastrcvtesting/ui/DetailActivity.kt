package com.example.broadcastrcvtesting.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.broadcastrcvtesting.R
import com.example.broadcastrcvtesting.databinding.DetailActivityBinding

class DetailActivity: AppCompatActivity() {
    private val binding by lazy {
        DetailActivityBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        fragment()

    }

    private fun fragment(){
        supportFragmentManager.beginTransaction().add(R.id.rootLayout, UserDetailFragment(intent?.getStringExtra("username") ?: "zzmitzz")).commit()
    }
    companion object {
        const val TAG = "DetailActivity"
    }
}