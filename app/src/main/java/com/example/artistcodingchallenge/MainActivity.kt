package com.example.artistcodingchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artistcodingchallenge.adapters.ArtistRVAdapter
import com.example.artistcodingchallenge.databinding.ActivityMainBinding
import com.example.artistcodingchallenge.viewmodels.ArtistViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel : ArtistViewModel by viewModels()
    private lateinit var trackAdapter : ArtistRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("Track Searcher")

        trackAdapter = ArtistRVAdapter()

        viewModel.getTracks().observe(this, Observer { trackList ->
            binding.progressBar.visibility = View.INVISIBLE
            trackAdapter.updateList(trackList)
        })

        with(binding) {
            search_btn.setOnClickListener{
                Log.d("_WORK", "Search Initiated")
                if (artistSearchBox.text.toString().isNotEmpty()) {
                    progressBar.visibility = View.VISIBLE
                    viewModel.loadTracks(artistSearchBox.text.toString())
                } else {
                    Toast.makeText(applicationContext, "You Forgot to add a Search Term!", Toast.LENGTH_LONG).show()
                }
            }

            trackRv.apply {
                layoutManager = LinearLayoutManager(
                    context
                )
                adapter = trackAdapter
            }
        }


    }
}