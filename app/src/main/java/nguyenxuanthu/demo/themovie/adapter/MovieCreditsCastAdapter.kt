package nguyenxuanthu.demo.themovie.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import nguyenxuanthu.demo.themovie.activity.PersonDetailActivity
import nguyenxuanthu.demo.themovie.R
import nguyenxuanthu.demo.themovie.databinding.MovieCreditsLayoutBinding
import nguyenxuanthu.demo.themovie.model.movie.MovieCreditsCast

class MovieCreditsCastAdapter(private var movieCreditsCastList: List<MovieCreditsCast>,
                              private val activity: Activity): RecyclerView.Adapter<MovieCreditsCastAdapter.MovieCreditsCastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCreditsCastViewHolder {
        return MovieCreditsCastViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_credits_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieCreditsCastViewHolder, position: Int) {
        holder.bind(activity,movieCreditsCastList[position].getTheProfilePath(),movieCreditsCastList[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(activity, PersonDetailActivity::class.java).apply {
                putExtra("id", movieCreditsCastList[position].id)
            }
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movieCreditsCastList.size
    }

    class MovieCreditsCastViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var binding:MovieCreditsLayoutBinding = MovieCreditsLayoutBinding.bind(itemView)
        fun bind(activity: Activity, profilePath:String, movieCreditsCast: MovieCreditsCast){
            binding.movieCreditsName.text = movieCreditsCast.name
            binding.movieCreditsCharacter.text = "Character : ${movieCreditsCast.character}"
            Picasso.with(activity).load(profilePath).into(binding.movieCreditsImageView)
        }
    }
}