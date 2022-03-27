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
import nguyenxuanthu.demo.themovie.model.movie.MovieCreditsCrew

class MovieCreditsCrewAdapter(private var movieCreditsCrewList: List<MovieCreditsCrew>,
                              private val activity: Activity): RecyclerView.Adapter<MovieCreditsCrewAdapter.MovieCreditsCrewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCreditsCrewViewHolder {
        return MovieCreditsCrewViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_credits_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieCreditsCrewViewHolder, position: Int) {
        holder.bind(activity,movieCreditsCrewList[position].getTheProfilePath(),movieCreditsCrewList[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(activity, PersonDetailActivity::class.java).apply {
                putExtra("id", movieCreditsCrewList[position].id)
            }
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movieCreditsCrewList.size
    }

    class MovieCreditsCrewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var binding:MovieCreditsLayoutBinding = MovieCreditsLayoutBinding.bind(itemView)
        fun bind(activity: Activity, profilePath:String, movieCreditsCrew: MovieCreditsCrew){
            binding.movieCreditsName.text = movieCreditsCrew.name
            binding.movieCreditsCharacter.text = "Department : ${movieCreditsCrew.department}"
            Picasso.with(activity).load(profilePath).into(binding.movieCreditsImageView)
        }
    }
}