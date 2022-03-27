package nguyenxuanthu.demo.themovie.model.movie

import com.google.gson.annotations.SerializedName

class MovieDetailsGenres (

    @SerializedName("id"   ) var id   : Int?    = null,
    @SerializedName("name" ) var name : String? = null

){
}