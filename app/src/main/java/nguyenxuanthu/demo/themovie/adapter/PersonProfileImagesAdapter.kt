package nguyenxuanthu.demo.themovie.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import nguyenxuanthu.demo.themovie.activity.ImageViewerActivity
import nguyenxuanthu.demo.themovie.R
import nguyenxuanthu.demo.themovie.databinding.ProfileImagesLayoutBinding
import nguyenxuanthu.demo.themovie.model.person.PersonImagesProfile

class PersonProfileImagesAdapter(private var profileImagesList: List<PersonImagesProfile>,
                                 private val activity: Activity): RecyclerView.Adapter<PersonProfileImagesAdapter.PersonProfileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonProfileViewHolder {
        return PersonProfileViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.profile_images_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PersonProfileViewHolder, position: Int) {
        holder.bind(activity,profileImagesList[position].getTheFilePath())
        holder.profileImage.setOnClickListener {
            val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, holder.profileImage,
                ViewCompat.getTransitionName(holder.profileImage)!!
            )
            val intent = Intent(activity, ImageViewerActivity::class.java).apply {
                putExtra("image_url", profileImagesList[position].getTheFilePath())
            }
            activity.startActivity(intent,compat.toBundle())
        }
    }

    override fun getItemCount(): Int {
        return profileImagesList.size
    }

    class PersonProfileViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var binding:ProfileImagesLayoutBinding = ProfileImagesLayoutBinding.bind(itemView)
        var profileImage = binding.profileImage
        fun bind(activity: Activity, profilePath:String){
            Picasso.with(activity).load(profilePath).into(profileImage)
        }
    }
}