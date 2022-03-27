package nguyenxuanthu.demo.themovie.model.movie

import com.google.gson.annotations.SerializedName

class MovieImagesBackDropsAndPosters (

    @SerializedName("aspect_ratio" ) var aspectRatio : Double? = null,
    @SerializedName("height"       ) var height      : Int?    = null,
    @SerializedName("iso_639_1"    ) var iso6391     : String? = null,
    @SerializedName("file_path"    ) var filePath    : String? = null,
    @SerializedName("vote_average" ) var voteAverage : Double?    = null,
    @SerializedName("vote_count"   ) var voteCount   : Int?    = null,
    @SerializedName("width"        ) var width       : Int?    = null

){
    fun getTheFilePath():String{
        return "https://image.tmdb.org/t/p/w600_and_h900_bestv2$filePath"
    }
}