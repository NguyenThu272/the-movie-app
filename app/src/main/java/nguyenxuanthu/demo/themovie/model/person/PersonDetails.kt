package nguyenxuanthu.demo.themovie.model.person

import com.google.gson.annotations.SerializedName

class PersonDetails(
    @SerializedName("adult"                ) var adult              : Boolean?          = null,
    @SerializedName("also_known_as"        ) var alsoKnownAs        : ArrayList<String> = arrayListOf(),
    @SerializedName("biography"            ) var biography          : String?           = null,
    @SerializedName("birthday"             ) var birthday           : String?           = null,
    @SerializedName("deathday"             ) var deathday           : String?           = null,
    @SerializedName("gender"               ) var gender             : Int?              = null,// 2 means male, 1 means female
    @SerializedName("homepage"             ) var homepage           : String?           = null,
    @SerializedName("id"                   ) var id                 : Int?              = null,
    @SerializedName("imdb_id"              ) var imdbId             : String?           = null,
    @SerializedName("known_for_department" ) var knownForDepartment : String?           = null,
    @SerializedName("name"                 ) var name               : String?           = null,
    @SerializedName("place_of_birth"       ) var placeOfBirth       : String?           = null,
    @SerializedName("popularity"           ) var popularity         : Double?           = null,
    @SerializedName("profile_path"         ) var profilePath        : String?           = null
){
    fun getTheProfilePath():String{
        return "https://image.tmdb.org/t/p/w600_and_h900_bestv2$profilePath"
    }
}