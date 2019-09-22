package ru.skyeng.dictionary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.skyeng.dictionary.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startActivity(MainActivity.newIntent(this))
        finish()
    }
}
