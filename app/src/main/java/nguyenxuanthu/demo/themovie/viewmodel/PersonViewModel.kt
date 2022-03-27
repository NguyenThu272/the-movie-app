package nguyenxuanthu.demo.themovie.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import nguyenxuanthu.demo.themovie.model.person.PersonDetails
import nguyenxuanthu.demo.themovie.model.person.PersonImages
import nguyenxuanthu.demo.themovie.model.person.PersonResponse
import nguyenxuanthu.demo.themovie.repository.PersonRepository
import javax.inject.Inject

@HiltViewModel
class PersonViewModel
@Inject
constructor(private val repository: PersonRepository) : ViewModel() {

//    private val _responseAllPeople = MutableLiveData<List<MovieResponse>>()
//    val responsePeople: LiveData<List<MovieResponse>>
//        get() = _responseAllPeople
//
//    init {
//        //getAllMovies()
//    }

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

    suspend fun getPersons(finalQuery:String): PersonResponse?{
        var responseAPerson : PersonResponse?=null
        val job = CoroutineScope(Dispatchers.IO).async {
                repository.getPersons(finalQuery).let {response ->
                    if (response.isSuccessful){
                        responseAPerson=response.body()
                    }else{
                        Log.e("tag", "getPersons Error: ${response.code()}")
                    }
                }
            }
        job.await()
        return responseAPerson
    }

    suspend fun getPersonDetail(id:Int): PersonDetails?{
        var responseAPersonDetail : PersonDetails?=null
        val job = CoroutineScope(Dispatchers.IO).async {
            repository.getPersonDetail(id).let {response ->
                if (response.isSuccessful){
                    responseAPersonDetail=response.body()
                }else{
                    Log.e("tag", "getPersonDetail Error: ${response.code()}")
                }
            }
        }
        job.await()
        return responseAPersonDetail
    }

    suspend fun getPersonImages(id:Int): PersonImages?{
        var responseAPersonImages : PersonImages?=null
        val job = CoroutineScope(Dispatchers.IO).async {
            repository.getPersonImages(id).let {response ->
                if (response.isSuccessful){
                    responseAPersonImages=response.body()
                }else{
                    Log.e("tag", "getPersonImages Error: ${response.code()}")
                }
            }
        }
        job.await()
        return responseAPersonImages
    }



}













