package nguyenxuanthu.demo.themovie.model.movie

import com.google.gson.annotations.SerializedName

class MovieDetailsProductionCompanies(

    @SerializedName("id"             ) var id            : Int?    = null,
    @SerializedName("logo_path"      ) var logoPath      : String? = null,
    @SerializedName("name"           ) var name          : String? = null,
    @SerializedName("origin_country" ) var originCountry : String? = null

){
    fun getTheLogoPath():String{
        return "https://image.tmdb.org/t/p/w600_and_h900_bestv2$logoPath"
    }
}