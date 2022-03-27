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
import nguyenxuanthu.demo.themovie.activity.PersonDetailActivity
import nguyenxuanthu.demo.themovie.R
import nguyenxuanthu.demo.themovie.databinding.SearchLayoutItemBinding
import nguyenxuanthu.demo.themovie.model.person.PersonResults

class PersonSearchAdapter (private var personResultsList: List<PersonResults>,
                           private val activity: Activity)
    : RecyclerView.Adapter<PersonSearchAdapter.PersonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_layout_item,parent,false))
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val personResult = personResultsList[position]
        holder.bind(personResult)
        holder.setPosterImageView(activity, personResult.getTheProfilePath())
        holder.itemView.setOnClickListener {
            val intent = Intent(activity, PersonDetailActivity::class.java)
            intent.putExtra("id", personResult.id)
            activity.startActivity(intent)

            // create some animation to open the new activity
            activity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
        }
    }

    override fun getItemCount(): Int {
        return personResultsList.size
    }

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding:SearchLayoutItemBinding = SearchLayoutItemBinding.bind(itemView)

        fun bind(personResults: PersonResults){
            // tạo hiệu ứng animation cho poster với KenBurnsView
            binding.posterImageView.setTransitionGenerator(RandomTransitionGenerator(1000,
                DecelerateInterpolator()))
            if(personResults.name !=null){
                binding.posterTitle.visibility = View.VISIBLE
                binding.posterTitle.text = personResults.name
            }else{
                binding.posterTitle.visibility = View.GONE
            }
        }

        fun setPosterImageView(context: Context,posterUrl:String){
            Picasso.with(context).load(posterUrl).into(binding.posterImageView)
        }

    }

}