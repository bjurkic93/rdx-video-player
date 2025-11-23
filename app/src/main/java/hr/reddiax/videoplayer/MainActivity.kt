package hr.reddiax.videoplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import hr.reddiax.videoplayer.ui.theme.RdXVideoPlayerTheme
import hr.reddiax.videoplayer.video.VideoPlayerScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RdXVideoPlayerTheme {
                VideoPlayerScreen()
            }
        }
    }
}

