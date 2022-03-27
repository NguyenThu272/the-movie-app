package nguyenxuanthu.demo.themovie.model.movie

import com.google.gson.annotations.SerializedName

class MovieImages (

    @SerializedName("backdrops" ) var backdrops : ArrayList<MovieImagesBackDropsAndPosters> = arrayListOf(),
    @SerializedName("id"        ) var id        : Int?                 = null,
    @SerializedName("posters"   ) var posters   : ArrayList<MovieImagesBackDropsAndPosters>   = arrayListOf()

){
}