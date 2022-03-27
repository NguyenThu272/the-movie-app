package nguyenxuanthu.demo.themovie.model.movie

import com.google.gson.annotations.SerializedName

class MovieDetailsBelongsToCollection  (

    @SerializedName("id"            ) var id           : Int?    = null,
    @SerializedName("name"          ) var name         : String? = null,
    @SerializedName("poster_path"   ) var posterPath   : String? = null,
    @SerializedName("backdrop_path" ) var backdropPath : String? = null

){
    fun getThePosterPath():String{
        return "https://image.tmdb.org/t/p/w600_and_h900_bestv2$posterPath"
    }

    fun getTheBackdropPath():String{
        return "https://image.tmdb.org/t/p/w600_and_h900_bestv2$backdropPath"
    }
}