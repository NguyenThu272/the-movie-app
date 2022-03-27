package nguyenxuanthu.demo.themovie.model.movie

import com.google.gson.annotations.SerializedName

class MovieCredits (

    @SerializedName("id"   ) var id   : Int?            = null,
    @SerializedName("cast" ) var cast : ArrayList<MovieCreditsCast> = arrayListOf(),
    @SerializedName("crew" ) var crew : ArrayList<MovieCreditsCrew> = arrayListOf()

){
}