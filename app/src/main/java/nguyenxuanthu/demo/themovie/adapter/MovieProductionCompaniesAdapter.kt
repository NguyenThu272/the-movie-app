package nguyenxuanthu.demo.themovie.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import nguyenxuanthu.demo.themovie.R
import nguyenxuanthu.demo.themovie.databinding.ProductionCompanyLayoutBinding
import nguyenxuanthu.demo.themovie.model.movie.MovieDetailsProductionCompanies

class MovieProductionCompaniesAdapter(private var movieProductionCompaniesList: List<MovieDetailsProductionCompanies>,
                                      private val activity: Activity): RecyclerView.Adapter<MovieProductionCompaniesAdapter.MovieProductionCompaniesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieProductionCompaniesViewHolder {
        return MovieProductionCompaniesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.production_company_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieProductionCompaniesViewHolder, position: Int) {
        holder.bind(activity,movieProductionCompaniesList[position])
    }

    override fun getItemCount(): Int {
        return movieProductionCompaniesList.size
    }

    class MovieProductionCompaniesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var binding:ProductionCompanyLayoutBinding = ProductionCompanyLayoutBinding.bind(itemView)
        fun bind(activity: Activity, movieDetailsProductionCompanies:MovieDetailsProductionCompanies){
            binding.productionCompanyName.text = movieDetailsProductionCompanies.name
            Picasso.with(activity).load(movieDetailsProductionCompanies.getTheLogoPath()).into(binding.productionCompanyImageView)
        }
    }
}