package nguyenxuanthu.demo.themovie.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.flaviofaria.kenburnsview.RandomTransitionGenerator
import com.squareup.picasso.Picasso
import nguyenxuanthu.demo.themovie.activity.MovieDetailActivity
import nguyenxuanthu.demo.themovie.R
import nguyenxuanthu.demo.themovie.databinding.SearchLayoutItemBinding
import nguyenxuanthu.demo.themovie.model.movie.MovieResults

class MovieSearchAdapter (private var movieResultsList: List<MovieResults>,
                          private val activity: Activity)
    : RecyclerView.Adapter<MovieSearchAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_layout_item,parent,false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieResult = movieResultsList[position]
        holder.bind(movieResult)
        holder.setPosterImageView(activity, movieResult.getThePosterPath())
        holder.itemView.setOnClickListener {
            val intent = Intent(activity, MovieDetailActivity::class.java).apply {
                putExtra("id",movieResult.id)
            }
            activity.startActivity(intent)

            // create some animation to open the new activity
            activity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
        }
    }

    override fun getItemCount(): Int {
        return movieResultsList.size
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding:SearchLayoutItemBinding = SearchLayoutItemBinding.bind(itemView)

        fun bind(movieResults: MovieResults){
            // tạo hiệu ứng animation cho poster với KenBurnsView
            binding.posterImageView.setTransitionGenerator(RandomTransitionGenerator(1000,
                DecelerateInterpolator()))
            if(movieResults.title !=null){
                binding.posterTitle.visibility = View.VISIBLE
                binding.posterTitle.text = movieResults.title
            }else{
                binding.posterTitle.visibility = View.GONE
            }
        }

        fun setPosterImageView(context: Context,posterUrl:String){
            Picasso.with(context).load(posterUrl).into(binding.posterImageView)
        }

    }

}