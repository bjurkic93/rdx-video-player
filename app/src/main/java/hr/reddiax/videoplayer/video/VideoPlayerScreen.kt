package hr.reddiax.videoplayer.video

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import hr.reddiax.videoplayer.ui.theme.RdXVideoPlayerTheme

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun VideoPlayerScreen(
    videoUrl: String = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
) {
    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(videoUrl)
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    DisposableEffect(Unit) {
        onDispose { exoPlayer.release() }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        shape = RectangleShape
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(
                factory = { playerContext ->
                    PlayerView(playerContext).apply {
                        layoutParams =
                            android.view.ViewGroup.LayoutParams(
                            android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                            android.view.ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        useController = true
                        isFocusable = true
                        isFocusableInTouchMode = true
                        requestFocus()
                        player = exoPlayer
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .focusRequester(focusRequester)
                    .focusable()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VideoPlayerScreenPreview() {
    RdXVideoPlayerTheme {
        VideoPlayerScreen()
    }
}
