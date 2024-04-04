package com.example.broadcastrcvtesting

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.broadcastrcvtesting.databinding.ActivityMainBinding
import com.example.broadcastrcvtesting.ui.DetailActivity

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.run {
            btn.setOnClickListener{
                val username = editText.text.toString().run {
                    trim()
                }
                if(username != ""){
                    val intent = Intent(this@MainActivity,DetailActivity::class.java).apply {
                        putExtra("username",username)
                    }
                    startActivity(intent)
                }else{
                    Toast.makeText(applicationContext, "Enter username", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}