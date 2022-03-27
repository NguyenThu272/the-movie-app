package nguyenxuanthu.demo.themovie.model.movie

import com.google.gson.annotations.SerializedName

 class MovieResponse (
     @SerializedName("page"          ) var page         : Int,
     @SerializedName("results"       ) var results      : MutableList<MovieResults>,
     @SerializedName("total_pages"   ) var totalPages   : Int,
     @SerializedName("total_results" ) var totalResults : Int,
)