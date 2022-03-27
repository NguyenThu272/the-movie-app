package nguyenxuanthu.demo.themovie.model.person

import com.google.gson.annotations.SerializedName

class PersonResults (@SerializedName("adult"                ) var adult              : Boolean,
                     @SerializedName("gender"               ) var gender             : Int,
                     @SerializedName("id"                   ) var id                 : Int,
                     @SerializedName("known_for"            ) var knownFor           : MutableList<PersonResultsKnownFor>,
                     @SerializedName("known_for_department" ) var knownForDepartment : String,
                     @SerializedName("name"                 ) var name               : String,
                     @SerializedName("popularity"           ) var popularity         : Double,
                     @SerializedName("profile_path"         ) var profilePath        : String
){
    fun getTheProfilePath():String{
        return "https://image.tmdb.org/t/p/w600_and_h900_bestv2$profilePath"
    }
}