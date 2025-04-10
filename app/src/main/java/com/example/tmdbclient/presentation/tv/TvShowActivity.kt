package com.example.tmdbclient.presentation.tv

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
import com.example.tmdbclient.databinding.ActivityMovieBinding
import com.example.tmdbclient.databinding.ActivityTvShowBinding
import com.example.tmdbclient.presentation.di.Injector

import com.example.tmdbclient.presentation.movie.MovieViewModel
import com.example.tmdbclient.presentation.movie.MovieViewModelFactory
import javax.inject.Inject

class TvShowActivity : AppCompatActivity() {
    private lateinit var binding:ActivityTvShowBinding
    @Inject
    lateinit var factory: TvShowViewModelFactory
    private lateinit var tvShowViewModel: TvShowViewModel

    private lateinit var adapter: TvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=DataBindingUtil.setContentView(this,R.layout.activity_tv_show)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        (application as Injector).createTvShowSubComponet()
            .inject(this)
        tvShowViewModel= ViewModelProvider(this,factory)
            .get(TvShowViewModel::class.java)
        initRecyclerView()
    }
    private fun initRecyclerView(){
        binding.tvRecyclerView.layoutManager= LinearLayoutManager(this)
        adapter=TvAdapter()
        binding.tvRecyclerView.adapter=adapter
        displayPopularTvShows()
    }

    private fun displayPopularTvShows(){
        binding.tvProgressBar.visibility= View.VISIBLE
        val responseLiveData=tvShowViewModel.getTvShows()
        responseLiveData.observe(this, Observer {
            if (it!=null){
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.tvProgressBar.visibility= View.GONE
            }else{
                binding.tvProgressBar.visibility= View.GONE
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
                updateTvShows()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateTvShows(){
        binding.tvProgressBar.visibility= View.VISIBLE
        val response=tvShowViewModel.updatTvShows()
        response.observe(this, Observer {
            if(it!=null){
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.tvProgressBar.visibility= View.GONE
            }else{
                binding.tvProgressBar.visibility= View.GONE

            }
        })
    }
}