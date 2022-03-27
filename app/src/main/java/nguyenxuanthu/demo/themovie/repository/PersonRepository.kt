package nguyenxuanthu.demo.themovie.repository

import nguyenxuanthu.demo.themovie.config.Config
import nguyenxuanthu.demo.themovie.network.NetworkInterface
import javax.inject.Inject


class PersonRepository
@Inject
constructor(private val networkInterface: NetworkInterface) {
    suspend fun getPersons(finalQuery:String) = networkInterface.getPersonsByQuery(Config.THE_MOVIE_DB_API_KEY,finalQuery)

    suspend fun getPersonDetail(id:Int) = networkInterface.getPersonDetailByQuery(id,Config.THE_MOVIE_DB_API_KEY)

    suspend fun getPersonImages(id:Int) = networkInterface.getPersonImageByQuery(id,Config.THE_MOVIE_DB_API_KEY)
}