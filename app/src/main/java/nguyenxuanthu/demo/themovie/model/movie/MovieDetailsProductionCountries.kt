package nguyenxuanthu.demo.themovie.model.movie

import com.google.gson.annotations.SerializedName

class MovieDetailsProductionCountries(

    @SerializedName("iso_3166_1" ) var iso31661 : String? = null,
    @SerializedName("name"       ) var name     : String? = null

) {
}