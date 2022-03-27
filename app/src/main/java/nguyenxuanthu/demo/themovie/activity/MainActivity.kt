package nguyenxuanthu.demo.themovie.activity

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.paperdb.Paper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nguyenxuanthu.demo.themovie.R
import nguyenxuanthu.demo.themovie.adapter.MovieSearchAdapter
import nguyenxuanthu.demo.themovie.adapter.PersonSearchAdapter
import nguyenxuanthu.demo.themovie.databinding.ActivityMainBinding
import nguyenxuanthu.demo.themovie.model.movie.MovieResponse
import nguyenxuanthu.demo.themovie.model.movie.MovieResults
import nguyenxuanthu.demo.themovie.model.person.PersonResponse
import nguyenxuanthu.demo.themovie.model.person.PersonResults
import nguyenxuanthu.demo.themovie.viewmodel.MovieViewModel
import nguyenxuanthu.demo.themovie.viewmodel.PersonViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val movieViewModel: MovieViewModel by viewModels()
    private val personViewModel: PersonViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // disable the keyword on start
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        binding.resultsRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        Paper.init(this)
        setUpSpinner()
        setUpRecyclerView()
    }

    private val category = arrayOf("Tìm kiếm tên phim","Tìm kiếm diễn viên")
    private lateinit var movieSearchAdapter : MovieSearchAdapter
    private lateinit var personSearchAdapter : PersonSearchAdapter
    private fun setUpSpinner() {

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,category)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.sourceSpinner.adapter = adapter
        binding.sourceSpinner.setSelection(0)
        binding.queryEditText.hint = "Tên phim ..."

        binding.sourceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position==0){
                    binding.queryEditText.hint = "Tên phim ..."
                }else{
                    binding.queryEditText.hint = "Tên diễn viên ..."
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        // execute the query when click button search
        binding.querySearchButton.setOnClickListener {
            if(binding.queryEditText.text != null){
                val query = binding.queryEditText.text!!.trim().toString()
                if(query != ""){
                    binding.queryEditText.setText("")
                    // should replace spaces in query by + to avoid no result
                    val finalQuery = query.replace(" ","+")
                    val categoryName = category.get(binding.sourceSpinner.selectedItemPosition)
                    if(categoryName == "Tìm kiếm tên phim"){
                        var movieResponse: MovieResponse?
                        CoroutineScope(Dispatchers.Main).launch {
                            movieResponse= movieViewModel.getMovie(finalQuery)
                            if (movieResponse != null) {
                                val movieResponseList: List<MovieResults> =movieResponse!!.results
                                movieSearchAdapter = MovieSearchAdapter(movieResponseList, this@MainActivity)
                                binding.resultsRecyclerView.adapter = movieSearchAdapter

                                // create some animation to recycler view item loading
                                val controller = AnimationUtils.loadLayoutAnimation(
                                    this@MainActivity,
                                    R.anim.layout_slide_right
                                )
                                binding.resultsRecyclerView.layoutAnimation = controller
                                binding.resultsRecyclerView.scheduleLayoutAnimation()
                            }
                        }

                    }else{
                        var personResponse: PersonResponse?
                        CoroutineScope(Dispatchers.Main).launch {
                            personResponse = personViewModel.getPersons(finalQuery)
                            if(personResponse!=null){
                                val personResponseList:List<PersonResults> = personResponse!!.results
                                personSearchAdapter = PersonSearchAdapter(personResponseList, this@MainActivity)
                                binding.resultsRecyclerView.adapter = personSearchAdapter

                                // create some animation to recycler view item loading
                                val controller = AnimationUtils.loadLayoutAnimation(this@MainActivity,
                                    R.anim.layout_slide_right
                                )
                                binding.resultsRecyclerView.layoutAnimation = controller
                                binding.resultsRecyclerView.scheduleLayoutAnimation()
                            }
                        }

                    }
                }else{
                    Toast.makeText(this, "Tìm kiếm không được để trống!", Toast.LENGTH_LONG).show()
                }
            }

        }
    }

    private fun setUpRecyclerView() {

    }

}