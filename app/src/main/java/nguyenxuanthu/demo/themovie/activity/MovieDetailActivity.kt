package nguyenxuanthu.demo.themovie.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nguyenxuanthu.demo.themovie.R
import nguyenxuanthu.demo.themovie.adapter.MovieCreditsCastAdapter
import nguyenxuanthu.demo.themovie.adapter.MovieCreditsCrewAdapter
import nguyenxuanthu.demo.themovie.adapter.MovieImagesBackDropsAndPostersAdapter
import nguyenxuanthu.demo.themovie.adapter.MovieProductionCompaniesAdapter
import nguyenxuanthu.demo.themovie.databinding.ActivityMovieDetailBinding
import nguyenxuanthu.demo.themovie.model.movie.MovieCredits
import nguyenxuanthu.demo.themovie.model.movie.MovieDetails
import nguyenxuanthu.demo.themovie.model.movie.MovieImages
import nguyenxuanthu.demo.themovie.model.movie.MovieImagesBackDropsAndPosters
import nguyenxuanthu.demo.themovie.viewmodel.MovieViewModel

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private val movieViewModel:MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.movieDetailCastRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.movieDetailCrewRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.movieDetailProductionCompanyRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.movieDetailImagesRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        if(intent!=null && intent.extras!=null) {
            if (intent!!.getIntExtra("id", -1) != -1) {
                val id = intent!!.getIntExtra("id", -1)
                setDataToUI(id)
                setUpCreditsRecyclerView(id)
                setUpImagesRecyclerView(id)

            }
        }
    }

    private fun setDataToUI(id: Int) {
        var movieDetailResponse: MovieDetails?
        CoroutineScope(Dispatchers.Main).launch {
            movieDetailResponse = movieViewModel.getMovieDetail(id)
            if(movieDetailResponse!=null){
                // set data to UI
                Picasso.with(this@MovieDetailActivity).load(movieDetailResponse!!.getThePosterPath()).into(binding.movieDetailPosterImageView)
                Picasso.with(this@MovieDetailActivity).load(movieDetailResponse!!.getTheBackdropPath()).into(binding.movieDetailBackDropPosterCircleImageView)
                binding.movieDetailTitle.text = movieDetailResponse!!.title

                val rating = movieDetailResponse!!.voteAverage!!.toInt()
                binding.movieDetailRatingProgressBar.progress = rating
                if(movieDetailResponse!!.originalTitle!=null){
                    binding.movieDetailOriginalTitle.text = movieDetailResponse!!.originalTitle
                    binding.movieDetailOriginalTitleLayout.visibility = View.VISIBLE
                }else {binding.movieDetailOriginalTitleLayout.visibility = View.GONE}

                if(movieDetailResponse!!.originalLanguage!=null){
                    binding.movieDetailOriginalLanguage.text = movieDetailResponse!!.originalLanguage
                    binding.movieDetailOriginalLanguageLayout.visibility = View.VISIBLE
                }else {binding.movieDetailOriginalLanguageLayout.visibility = View.GONE}

                if(movieDetailResponse!!.adult!!){
                    binding.movieDetailAdult.text = "Yes"
                    binding.movieDetailAdultLayout.visibility = View.VISIBLE
                }else {
                    binding.movieDetailAdult.text = "No"
                    binding.movieDetailAdultLayout.visibility = View.VISIBLE}

                if(movieDetailResponse!!.status!=null){
                    binding.movieDetailStatus.text = movieDetailResponse!!.status
                    binding.movieDetailStatusLayout.visibility = View.VISIBLE
                }else {binding.movieDetailStatusLayout.visibility = View.GONE}

                if(movieDetailResponse!!.runtime!=null && movieDetailResponse!!.runtime!=0){
                    binding.movieDetailRuntime.text = movieDetailResponse!!.runtime.toString()
                    binding.movieDetailRuntimeLayout.visibility = View.VISIBLE
                }else {binding.movieDetailOriginalTitleLayout.visibility = View.GONE}

                if(movieDetailResponse!!.budget!=null && movieDetailResponse!!.budget!=0){
                    binding.movieDetailBudget.text = movieDetailResponse!!.budget.toString()
                    binding.movieDetailBudgetLayout.visibility = View.VISIBLE
                }else {binding.movieDetailOriginalTitleLayout.visibility = View.GONE}

                if(movieDetailResponse!!.revenue!=null && movieDetailResponse!!.revenue!=0){
                    binding.movieDetailRevenue.text = movieDetailResponse!!.revenue.toString()
                    binding.movieDetailRevenueLayout.visibility = View.VISIBLE
                }else {binding.movieDetailOriginalTitleLayout.visibility = View.GONE}

                if(movieDetailResponse!!.genres.size >0){
                    binding.movieDetailGenreLayout.visibility = View.VISIBLE
                    var genres = ""
                    val sizeOfGenre = movieDetailResponse!!.genres.size - 1
                    if (sizeOfGenre > 0) {
                        for (i in 0..sizeOfGenre) {
                            genres += movieDetailResponse!!.genres[i].name
                            genres += if(i==sizeOfGenre){
                                ". "
                            }else{
                                ", "
                            }
                        }
                        binding.movieDetailGenre.text = genres
                    }
                }else {binding.movieDetailOriginalTitleLayout.visibility = View.GONE}

                if(movieDetailResponse!!.productionCountries.size >0){
                    binding.movieDetailProductionCountriesLayout.visibility = View.VISIBLE
                    var productionCountries = ""
                    val sizeOfProductionCountries = movieDetailResponse!!.productionCountries.size - 1
                    if (sizeOfProductionCountries > 0) {
                        for (i in 0..sizeOfProductionCountries) {
                            productionCountries += movieDetailResponse!!.productionCountries[i].name
                            productionCountries += if(i==sizeOfProductionCountries){
                                ". "
                            }else{
                                ", "
                            }
                        }
                        binding.movieDetailProductionCountries.text = productionCountries
                    }
                }else {binding.movieDetailProductionCountriesLayout.visibility = View.GONE}

                if(movieDetailResponse!!.releaseDate!=null){
                    binding.movieDetailReleaseDate.text = movieDetailResponse!!.releaseDate
                    binding.movieDetailReleaseDateLayout.visibility = View.VISIBLE
                }else {binding.movieDetailReleaseDateLayout.visibility = View.GONE}

                if(movieDetailResponse!!.homepage!=null){
                    binding.movieDetailHomepage.text = movieDetailResponse!!.homepage
                    binding.movieDetailHomepageLayout.visibility = View.VISIBLE
                }else {binding.movieDetailHomepageLayout.visibility = View.GONE}

                if(movieDetailResponse!!.overview!=null){
                    binding.movieDetailOverview.text = movieDetailResponse!!.overview
                    binding.movieDetailOverviewLayout.visibility = View.VISIBLE
                }else {binding.movieDetailOverviewLayout.visibility = View.GONE}

                val movieProductionCompanyList = movieDetailResponse!!.productionCompanies
                if (movieProductionCompanyList.size>0){
                    binding.movieDetailProductionCompanyRecyclerViewLayout.visibility = View.VISIBLE
                    val movieProductionCompanyAdapter= MovieProductionCompaniesAdapter(movieProductionCompanyList,this@MovieDetailActivity)
                    binding.movieDetailProductionCompanyRecyclerView.adapter = movieProductionCompanyAdapter

                    // animation
                    val controller = AnimationUtils.loadLayoutAnimation(this@MovieDetailActivity,
                        R.anim.layout_slide_right
                    )
                    binding.movieDetailProductionCompanyRecyclerView.layoutAnimation = controller
                    binding.movieDetailProductionCompanyRecyclerView.scheduleLayoutAnimation()
                }else{binding.movieDetailProductionCompanyRecyclerViewLayout.visibility = View.GONE}
            }
        }
    }


    private fun setUpCreditsRecyclerView(id: Int) {
        var movieCreditsResponse: MovieCredits?
        CoroutineScope(Dispatchers.Main).launch {
            movieCreditsResponse = movieViewModel.getMovieCredits(id)
            if (movieCreditsResponse != null) {
                val movieCreditsCastList = movieCreditsResponse!!.cast
                if (movieCreditsCastList.size>0){
                    binding.movieDetailCastRecyclerViewLayout.visibility = View.VISIBLE
                    val movieCreditsCastAdapter= MovieCreditsCastAdapter(movieCreditsCastList,this@MovieDetailActivity)
                    binding.movieDetailCastRecyclerView.adapter = movieCreditsCastAdapter

                    // animation
                    val controller = AnimationUtils.loadLayoutAnimation(this@MovieDetailActivity,
                        R.anim.layout_slide_right
                    )
                    binding.movieDetailCastRecyclerView.layoutAnimation = controller
                    binding.movieDetailCastRecyclerView.scheduleLayoutAnimation()
                }else{binding.movieDetailCastRecyclerViewLayout.visibility = View.GONE}

                val movieCreditsCrewList = movieCreditsResponse!!.crew
                if (movieCreditsCrewList.size>0){
                    binding.movieDetailCrewRecyclerViewLayout.visibility = View.VISIBLE
                    val movieCreditsCrewAdapter= MovieCreditsCrewAdapter(movieCreditsCrewList,this@MovieDetailActivity)
                    binding.movieDetailCrewRecyclerView.adapter = movieCreditsCrewAdapter

                    // animation
                    val controller = AnimationUtils.loadLayoutAnimation(this@MovieDetailActivity,
                        R.anim.layout_slide_right
                    )
                    binding.movieDetailCrewRecyclerView.layoutAnimation = controller
                    binding.movieDetailCrewRecyclerView.scheduleLayoutAnimation()
                }else{binding.movieDetailCrewRecyclerViewLayout.visibility = View.GONE}
            }
        }
    }

    private fun setUpImagesRecyclerView(id: Int) {
        var movieImagesResponse: MovieImages?
        CoroutineScope(Dispatchers.Main).launch {
            movieImagesResponse = movieViewModel.getMovieImages(id)
            if (movieImagesResponse != null) {
                val movieImagesBackDropsList = movieImagesResponse!!.backdrops
                val movieImagesPosterList = movieImagesResponse!!.posters
                val movieImagesBackDropsAndPosters = ArrayList<MovieImagesBackDropsAndPosters>()
                movieImagesBackDropsAndPosters.addAll(movieImagesBackDropsList)
                movieImagesBackDropsAndPosters.addAll(movieImagesPosterList)
                binding.movieDetailImagesRecyclerViewLayout.visibility = View.VISIBLE
                val movieDetailImagesAdapter = MovieImagesBackDropsAndPostersAdapter(movieImagesBackDropsAndPosters,this@MovieDetailActivity)
                binding.movieDetailImagesRecyclerView.adapter = movieDetailImagesAdapter

                // animation
                val controller = AnimationUtils.loadLayoutAnimation(this@MovieDetailActivity,
                    R.anim.layout_slide_right
                )
                binding.movieDetailImagesRecyclerView.layoutAnimation = controller
                binding.movieDetailImagesRecyclerView.scheduleLayoutAnimation()
            }
        }
    }

    // set animation for back to MainActivity
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}