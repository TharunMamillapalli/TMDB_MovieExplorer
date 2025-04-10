package com.example.tmdbclient.presentation.artist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.ActivityArtistBinding
import com.example.tmdbclient.databinding.ActivityMovieBinding
import com.example.tmdbclient.presentation.di.Injector
import com.example.tmdbclient.presentation.movie.ArtistAdapter
import com.example.tmdbclient.presentation.movie.MovieViewModel
import com.example.tmdbclient.presentation.movie.MovieViewModelFactory
import javax.inject.Inject

class ArtistActivity : AppCompatActivity() {
    private lateinit var binding:ActivityArtistBinding
    @Inject
    lateinit var factory: ArtistViewModelFactory
    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var adapter: ArtistAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=DataBindingUtil.setContentView(this,R.layout.activity_artist)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        (application as Injector).createArtistSubComponent()
            .inject(this)
        artistViewModel= ViewModelProvider(this,factory)
            .get(ArtistViewModel::class.java)
        initRecyclerView()
    }
    private fun initRecyclerView(){
        binding.artistRecyclerView.layoutManager= LinearLayoutManager(this)
        adapter=ArtistAdapter()
        binding.artistRecyclerView.adapter=adapter
        displayPopularMovies()
    }

    private fun displayPopularMovies(){
        binding.artistProgressBar.visibility= View.VISIBLE
        val responseLiveData=artistViewModel.getArtists()
        responseLiveData.observe(this, Observer {
            if (it!=null){
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.artistProgressBar.visibility= View.GONE
            }else{
                binding.artistProgressBar.visibility= View.GONE
                Toast.makeText(applicationContext,"No data avaialable", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate: MenuInflater =menuInflater
        inflate.inflate(R.menu.update,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_update->{
                updateMovies()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateMovies(){
        binding.artistProgressBar.visibility= View.VISIBLE
        val response=artistViewModel.updateArtists()
        response.observe(this, Observer {
            if(it!=null){
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.artistProgressBar.visibility= View.GONE
            }else{
                binding.artistProgressBar.visibility= View.GONE

            }
        })
    }
}