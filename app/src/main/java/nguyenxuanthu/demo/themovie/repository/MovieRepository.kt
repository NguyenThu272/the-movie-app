package nguyenxuanthu.demo.themovie.repository

import nguyenxuanthu.demo.themovie.config.Config
import nguyenxuanthu.demo.themovie.network.NetworkInterface
import javax.inject.Inject


class MovieRepository
@Inject
constructor(private val networkInterface: NetworkInterface) {
    suspend fun getMovie(finalQuery:String) = networkInterface.getMovieByQuery(Config.THE_MOVIE_DB_API_KEY,finalQuery)

    suspend fun getMovieDetail(id:Int) = networkInterface.getMovieDetailByQuery(id,Config.THE_MOVIE_DB_API_KEY)

    suspend fun getMovieCredits(id:Int) = networkInterface.getMovieCreditsByQuery(id,Config.THE_MOVIE_DB_API_KEY)

    suspend fun getMovieImages(id:Int) = networkInterface.getMovieImagesByQuery(id,Config.THE_MOVIE_DB_API_KEY)

}