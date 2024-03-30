package com.scrap.scrap2024

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.scrap.scrap2024.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.textLogin.text = "로그인"
        binding.textLogin.textSize = 18f
        binding.textLogin.setTextColor(Color.Black.hashCode())
        binding.textDescription.setText(R.string.catchphrase)
        binding.textDescription.textSize = 22f
        binding.textDescription.setTextColor(Color.Black.hashCode())
        binding.textDescription.typeface = Typeface.DEFAULT_BOLD

        binding.buttonLoginNaver.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
        }
        binding.buttonLoginGoogle.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
        }
    }
}