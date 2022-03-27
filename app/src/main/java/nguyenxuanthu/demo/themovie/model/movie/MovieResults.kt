package nguyenxuanthu.demo.themovie.model.movie

import android.util.Log
import com.google.gson.annotations.SerializedName

class MovieResults (
    @SerializedName("adult"             ) var adult            : Boolean,
    @SerializedName("backdrop_path"     ) var backdropPath     : String,
    @SerializedName("genre_ids"         ) var genreIds         : MutableList<Int> ,
    @SerializedName("id"                ) var id               : Int,
    @SerializedName("original_language" ) var originalLanguage : String,
    @SerializedName("original_title"    ) var originalTitle    : String,
    @SerializedName("overview"          ) var overview         : String,
    @SerializedName("popularity"        ) var popularity       : Double,
    @SerializedName("poster_path"       ) var posterPath       : String,
    @SerializedName("release_date"      ) var releaseDate      : String,
    @SerializedName("title"             ) var title            : String? = null,
    @SerializedName("video"             ) var video            : Boolean,
    @SerializedName("vote_average"      ) var voteAverage      : Double,
    @SerializedName("vote_count"        ) var voteCount        : Long
){
    fun getThePosterPath():String{
        return "https://image.tmdb.org/t/p/w600_and_h900_bestv2$posterPath"
    }
}