package nguyenxuanthu.demo.themovie.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nguyenxuanthu.demo.themovie.R
import nguyenxuanthu.demo.themovie.adapter.PersonProfileImagesAdapter
import nguyenxuanthu.demo.themovie.databinding.ActivityPersonDetailBinding
import nguyenxuanthu.demo.themovie.model.person.PersonDetails
import nguyenxuanthu.demo.themovie.model.person.PersonImages
import nguyenxuanthu.demo.themovie.viewmodel.PersonViewModel

@AndroidEntryPoint
class PersonDetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPersonDetailBinding
    private val personViewModel: PersonViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.personDetailProfileImageRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        if(intent!=null && intent.extras!=null) {
            if (intent!!.getIntExtra("id", -1) != -1) {
                val id = intent!!.getIntExtra("id", -1)
                setDataToUI(id)
                setImagesToProfileRecyclerView(id)
            }
        }
    }

    private lateinit var personProfileImagesAdapter: PersonProfileImagesAdapter
    private fun setImagesToProfileRecyclerView(id: Int) {
        var personImagesResponse: PersonImages?
        CoroutineScope(Dispatchers.Main).launch {
            personImagesResponse = personViewModel.getPersonImages(id)
            if(personImagesResponse!=null){
                val personImagesProfileList = personImagesResponse!!.profiles
                if(personImagesProfileList.size>0){
                    binding.personDetailProfileImageLayout.visibility = View.VISIBLE
                    personProfileImagesAdapter = PersonProfileImagesAdapter(personImagesProfileList,this@PersonDetailActivity)
                    binding.personDetailProfileImageRecyclerView.adapter = personProfileImagesAdapter
                    val controller = AnimationUtils.loadLayoutAnimation(this@PersonDetailActivity,
                        R.anim.layout_slide_right
                    )
                    binding.personDetailProfileImageRecyclerView.layoutAnimation = controller
                    binding.personDetailProfileImageRecyclerView.scheduleLayoutAnimation()
                }else{
                    binding.personDetailProfileImageLayout.visibility = View.GONE
                }
            }
        }
    }

    private fun setDataToUI(id: Int) {
        var personDetailResponse: PersonDetails?
        CoroutineScope(Dispatchers.Main).launch {
            personDetailResponse = personViewModel.getPersonDetail(id)
            if (personDetailResponse != null) {
                // set data to UI
                Picasso.with(this@PersonDetailActivity)
                    .load(personDetailResponse!!.getTheProfilePath())
                    .into(binding.personDetailProfileImageView)
                binding.personDetailName.text = personDetailResponse!!.name
                if (personDetailResponse!!.alsoKnownAs.size>0) {
                    binding.personDetailKnownAsLayout.visibility = View.VISIBLE
                    var knownAs = ""
                    val sizeOfKnownAs = personDetailResponse!!.alsoKnownAs.size - 1
                    if (sizeOfKnownAs > 0) {
                        for (i in 0..sizeOfKnownAs) {
                            knownAs += personDetailResponse!!.alsoKnownAs[i]
                            knownAs += if(i == sizeOfKnownAs){
                                ". "
                            }else{
                                ", "
                            }
                        }
                        binding.personDetailKnownAs.text = knownAs
                    }
                } else {
                    binding.personDetailKnownAsLayout.visibility = View.GONE
                }
                if (personDetailResponse!!.birthday != null) {
                    binding.personDetailBirthdayLayout.visibility = View.VISIBLE
                    binding.personDetailBirthday.text = personDetailResponse!!.birthday
                } else {
                    binding.personDetailBirthdayLayout.visibility = View.GONE
                }
                if (personDetailResponse!!.placeOfBirth != null) {
                    binding.personDetailPlaceOfBirthLayout.visibility = View.VISIBLE
                    binding.personDetailPlaceOfBirth.text = personDetailResponse!!.placeOfBirth
                } else {
                    binding.personDetailPlaceOfBirthLayout.visibility = View.GONE
                }
                if (personDetailResponse!!.deathday != null) {
                    binding.personDetailDeathdayLayout.visibility = View.VISIBLE
                    binding.personDetailDeathday.text = personDetailResponse!!.deathday
                } else {
                    binding.personDetailDeathdayLayout.visibility = View.GONE
                }
                if (personDetailResponse!!.knownForDepartment != null) {
                    binding.personDetailKnownForDepartmentLayout.visibility = View.VISIBLE
                    binding.personDetailKnownForDepartment.text = personDetailResponse!!.knownForDepartment
                } else {
                    binding.personDetailKnownForDepartmentLayout.visibility = View.GONE
                }
                if (personDetailResponse!!.homepage != null) {
                    binding.personDetailHomepageLayout.visibility = View.VISIBLE
                    binding.personDetailHomepage.text = personDetailResponse!!.homepage
                } else {
                    binding.personDetailHomepageLayout.visibility = View.GONE
                }
                if (personDetailResponse!!.biography != null) {
                    binding.personDetailBiographyLayout.visibility = View.VISIBLE
                    binding.personDetailBiography.text = personDetailResponse!!.biography
                } else {
                    binding.personDetailBiographyLayout.visibility = View.GONE
                }
            }else{
                Toast.makeText(this@PersonDetailActivity, "Any details not found", Toast.LENGTH_LONG).show()
            }
        }
    }


    // set animation for back to MainActivity
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}