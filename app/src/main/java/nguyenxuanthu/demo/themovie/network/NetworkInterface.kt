package nguyenxuanthu.demo.themovie.network

import nguyenxuanthu.demo.themovie.model.movie.MovieCredits
import nguyenxuanthu.demo.themovie.model.movie.MovieDetails
import nguyenxuanthu.demo.themovie.model.movie.MovieImages
import nguyenxuanthu.demo.themovie.model.movie.MovieResponse
import nguyenxuanthu.demo.themovie.model.person.PersonDetails
import nguyenxuanthu.demo.themovie.model.person.PersonImages
import nguyenxuanthu.demo.themovie.model.person.PersonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkInterface {

    @GET("search/movie")
    suspend fun getMovieByQuery(@Query("api_key") api_key:String, @Query("query") query:String): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetailByQuery(@Path("movie_id") movie_id:Int, @Query("api_key") api_key:String): Response<MovieDetails>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCreditsByQuery(@Path("movie_id") movie_id:Int, @Query("api_key") api_key:String): Response<MovieCredits>

    @GET("movie/{movie_id}/images")
    suspend fun getMovieImagesByQuery(@Path("movie_id") movie_id:Int, @Query("api_key") api_key:String): Response<MovieImages>

    @GET("search/person")
    suspend fun getPersonsByQuery(@Query("api_key") api_key:String, @Query("query") query:String): Response<PersonResponse>

    @GET("person/{person_id}")
    suspend fun getPersonDetailByQuery(@Path("person_id") person_id:Int, @Query("api_key") api_key:String): Response<PersonDetails>

    @GET("person/{person_id}/images")
    suspend fun getPersonImageByQuery(@Path("person_id") person_id:Int, @Query("api_key") api_key:String): Response<PersonImages>

}