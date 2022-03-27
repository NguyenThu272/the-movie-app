package nguyenxuanthu.demo.themovie.model.person

import com.google.gson.annotations.SerializedName

class PersonResponse (
    @SerializedName("page"          ) var page         : Int,
    @SerializedName("results"       ) var results      : MutableList<PersonResults>,
    @SerializedName("total_pages"   ) var totalPages   : Int,
    @SerializedName("total_results" ) var totalResults : Int
)