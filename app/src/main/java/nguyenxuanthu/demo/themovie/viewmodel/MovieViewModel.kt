package nguyenxuanthu.demo.themovie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import nguyenxuanthu.demo.themovie.model.movie.MovieCredits
import nguyenxuanthu.demo.themovie.model.movie.MovieDetails
import nguyenxuanthu.demo.themovie.model.movie.MovieImages
import nguyenxuanthu.demo.themovie.model.movie.MovieResponse
import nguyenxuanthu.demo.themovie.repository.MovieRepository
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
@Inject
constructor(private val repository: MovieRepository) : ViewModel() {

    private val _responseAllMovie = MutableLiveData<List<MovieResponse>>()
    val responseMovies: LiveData<List<MovieResponse>>
        get() = _responseAllMovie

    init {
        //getAllMovies()
    }

//    private fun getAllMovies() = viewModelScope.launch {
//        repository.getMovie().let {response ->
//
//            if (response.isSuccessful){
//                _response.postValue(response.body())
//            }else{
//                Log.d("tag", "getAllTvShows Error: ${response.code()}")
//            }
//        }
//    }

    suspend fun getMovie(finalQuery:String): MovieResponse?{
        var responseAMovie : MovieResponse?=null
        val job = CoroutineScope(Dispatchers.IO).async {
                repository.getMovie(finalQuery).let {response ->
                    if (response.isSuccessful){
                        responseAMovie=response.body()
                    }else{
                        Log.e("tag", "getMovie Error: ${response.code()}")
                    }
                }
            }
        job.await()
        return responseAMovie
    }

    suspend fun getMovieDetail(id:Int): MovieDetails?{
        var responseAMovieDetail : MovieDetails?=null
        val job = CoroutineScope(Dispatchers.IO).async {
            repository.getMovieDetail(id).let {response ->
                if (response.isSuccessful){
                    responseAMovieDetail=response.body()
                }else{
                    Log.e("tag", "getMovieDetail Error: ${response.code()}")
                }
            }
        }
        job.await()
        return responseAMovieDetail
    }

    suspend fun getMovieCredits(id:Int): MovieCredits?{
        var responseMovieCredit : MovieCredits?=null
        val job = CoroutineScope(Dispatchers.IO).async {
            repository.getMovieCredits(id).let {response ->
                if (response.isSuccessful){
                    responseMovieCredit=response.body()
                }else{
                    Log.e("tag", "getMovieCredits Error: ${response.code()}")
                }
            }
        }
        job.await()
        return responseMovieCredit
    }

    suspend fun getMovieImages(id:Int): MovieImages?{
        var responseMovieImages : MovieImages?=null
        val job = CoroutineScope(Dispatchers.IO).async {
            repository.getMovieImages(id).let {response ->
                if (response.isSuccessful){
                    responseMovieImages=response.body()
                }else{
                    Log.e("tag", "getMovieImages Error: ${response.code()}")
                }
            }
        }
        job.await()
        return responseMovieImages
    }

}













