package com.tinaciousdesign.simpledatabaseexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tinaciousdesign.simpledatabaseexample.databinding.ActivityMainBinding
import com.tinaciousdesign.simpledatabaseexample.di.AppContainer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dependencies: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dependencies = (applicationContext as ExampleApp).dependencies

        initExample()
    }

    private fun initExample() {
        // Get the last loaded value
        val lastLoaded = dependencies.keyValueStorage.getString("last_loaded", "(none)")
        Log.d("MainActivity", "Last loaded (1) = $lastLoaded")

        // Overwrite with new value
        dependencies.keyValueStorage.putString("last_loaded", System.currentTimeMillis().toString())
        val nextLastLoaded = dependencies.keyValueStorage.getString("last_loaded", "(none)")
        Log.d("MainActivity", "Last loaded (2) = $nextLastLoaded")

        // Set both the above values on the text view
        val message = "Last loaded (1) = $lastLoaded - Last loaded (2) = $nextLastLoaded"
        binding.outputTextView.text = message
    }
}