package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() { // manager function
    var artwork by remember { mutableStateOf(1) } // tracks the current artwork

    Column { // child functions
        ArtWorkCanvas(artwork = artwork) // passes down the actual state
        ArtWorkDescription(artwork = artwork) // passes down the actual state
        DisplayController(
            onNextClick = {
                if (artwork == 1) {
                    artwork++
                }
                else if (artwork == 2) {
                    artwork++
                }
                else {
                    artwork = 1
                }}, // pass actual function logic
            onPreviousClick = {
                if (artwork == 1) {
                    artwork = 3
                }
                else if (artwork == 2) {
                    artwork--
                }
                else {
                    artwork = 2
                }
            }, // pass actual function logic
            modifier = Modifier.padding(top = 32.dp)
        )
    }
}

@Composable
fun ArtWorkCanvas(artwork: Int) { // takes the state as a template
    val imageResource = when (artwork) {
        1 -> R.drawable._1
        2 -> R.drawable._2
        3 -> R.drawable._3
        else -> R.drawable._1
    }

    Image(
        painter = painterResource(id = imageResource),
        contentDescription = "Statue with Halo",
        modifier = Modifier
            .size(500.dp, 600.dp)  // Set a fixed size to prevent taking up all available space
            .padding(16.dp),
        contentScale = ContentScale.Fit
        )
}

@Composable
fun ArtWorkDescription(artwork: Int) { // takes the state as a template
    val artworkTitle = when (artwork) {
        1 -> R.string.artwork_1_title
        2 -> R.string.artwork_2_title
        3 -> R.string.artwork_3_title
        else -> R.string.artwork_1_title
    }

    val artworkArtist = when (artwork) {
        1 -> R.string.artist_1
        2 -> R.string.artist_2
        3 -> R.string.artist_3
        else -> R.string.artist_1
    }

    val artworkYear = when (artwork) {
        1 -> R.string.artwork_1_year
        2 -> R.string.artwork_2_year
        3 -> R.string.artwork_3_year
        else -> R.string.artwork_1_year
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = artworkTitle),
            color = Color.DarkGray,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            stringResource(id = artworkArtist),
            fontWeight = FontWeight.W400,
            fontStyle = FontStyle.Italic
        )
        Text(
            stringResource(id = artworkYear),
            fontWeight = FontWeight.W400,
            fontStyle = FontStyle.Italic
        )

    }
}

@Composable
fun DisplayController(
    onNextClick: () -> Unit, // pass function signature expected
    onPreviousClick: () -> Unit, // pass function signature expected
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.Center,

    ) {
        Button(
            onClick = { onPreviousClick() }
        ) {
            Text(text = "Previous")
        }

        Button(
            onClick = { onNextClick() },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}