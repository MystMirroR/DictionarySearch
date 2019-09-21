package ru.skyeng.dictionary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.skyeng.dictionary.R
import ru.skyeng.dictionary.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    SearchFragment.newInstance()
                )
                .commitNow()
        }
    }
}
