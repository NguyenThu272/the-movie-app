package nguyenxuanthu.demo.themovie.model.person

import com.google.gson.annotations.SerializedName

class PersonResultsKnownFor (
    @SerializedName("adult"             ) var adult            : Boolean,
    @SerializedName("genre_ids"         ) var genreIds         : MutableList<Int>,
    @SerializedName("id"                ) var id               : Int,
    @SerializedName("media_type"        ) var mediaType        : String,
    @SerializedName("original_language" ) var originalLanguage : String,
    @SerializedName("original_title"    ) var originalTitle    : String,
    @SerializedName("overview"          ) var overview         : String,
    @SerializedName("poster_path"       ) var posterPath       : String,
    @SerializedName("release_date"      ) var releaseDate      : String,
    @SerializedName("title"             ) var title            : String,
    @SerializedName("video"             ) var video            : Boolean,
    @SerializedName("vote_average"      ) var voteAverage      : Float,
    @SerializedName("vote_count"        ) var voteCount        : Int
){
    fun getThePosterPath():String{
        return "https://image.tmdb.org/t/p/w600_and_h900_bestv2$posterPath"
    }
}