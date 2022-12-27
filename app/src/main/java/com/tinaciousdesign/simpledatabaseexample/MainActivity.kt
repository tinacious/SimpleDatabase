package com.tinaciousdesign.simpledatabaseexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.tinaciousdesign.simpledatabaseexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var keyValueStorage: KeyValueStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        initExample()
    }

    private fun initExample() {
        keyValueStorage = KeyValueStorage.getInstance(this)

        val lastLoaded = keyValueStorage.getString("last_loaded", "(none)")

        Log.d("MainActivity", "Last loaded (1) = $lastLoaded")

        keyValueStorage.putString("last_loaded", System.currentTimeMillis().toString())

        val nextLastLoaded = keyValueStorage.getString("last_loaded", "(none)")

        Log.d("MainActivity", "Last loaded (2) = $nextLastLoaded")
    }
}