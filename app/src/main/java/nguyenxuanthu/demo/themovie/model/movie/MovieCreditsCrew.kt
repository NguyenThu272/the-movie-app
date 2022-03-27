package nguyenxuanthu.demo.themovie.model.movie

import com.google.gson.annotations.SerializedName

class MovieCreditsCrew (

    @SerializedName("adult"                ) var adult              : Boolean? = null,
    @SerializedName("gender"               ) var gender             : Int?     = null,
    @SerializedName("id"                   ) var id                 : Int?     = null,
    @SerializedName("known_for_department" ) var knownForDepartment : String?  = null,
    @SerializedName("name"                 ) var name               : String?  = null,
    @SerializedName("original_name"        ) var originalName       : String?  = null,
    @SerializedName("popularity"           ) var popularity         : Double?  = null,
    @SerializedName("profile_path"         ) var profilePath        : String?  = null,
    @SerializedName("credit_id"            ) var creditId           : String?  = null,
    @SerializedName("department"           ) var department         : String?  = null,
    @SerializedName("job"                  ) var job                : String?  = null

){
    fun getTheProfilePath():String{
        return "https://image.tmdb.org/t/p/w600_and_h900_bestv2$profilePath"
    }
}