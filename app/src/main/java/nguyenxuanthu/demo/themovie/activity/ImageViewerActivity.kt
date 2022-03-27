package nguyenxuanthu.demo.themovie.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.squareup.picasso.Picasso
import nguyenxuanthu.demo.themovie.databinding.ActivityImageViewerBinding

class ImageViewerActivity : AppCompatActivity() {
    private lateinit var binding:ActivityImageViewerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set full Screen for the activity
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        if(intent.extras != null){
            if(intent.getStringExtra("image_url")!=null){
                Picasso.with(this).load(intent.getStringExtra("image_url")).into(binding.zoomImageView)
            }
        }
    }
}