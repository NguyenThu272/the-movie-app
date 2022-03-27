package nguyenxuanthu.demo.themovie.model.person

import com.google.gson.annotations.SerializedName

class PersonImages (
    @SerializedName("id"       ) var id       : Int?                = null,
    @SerializedName("profiles" ) var profiles : ArrayList<PersonImagesProfile> = arrayListOf()
)