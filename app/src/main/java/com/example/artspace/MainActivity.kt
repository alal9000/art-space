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
fun ArtSpaceLayout() {
    Column {
        ArtWorkCanvas()
        ArtWorkDescription()
        DisplayController(
            modifier = Modifier.padding(top = 32.dp)
        )
    }
}

@Composable
fun ArtWorkCanvas() {


    Image(
        painter = painterResource(id = R.drawable._01_digitalart),
        contentDescription = "Statue with Halo",
        modifier = Modifier
            .size(500.dp, 600.dp)  // Set a fixed size to prevent taking up all available space
            .padding(16.dp),
        contentScale = ContentScale.Fit
        )
}

@Composable
fun ArtWorkDescription() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Text(
            text = stringResource(id = R.string.artwork_1_title),
            color = Color.DarkGray,
            fontSize = 28.sp
        )
        Text(
            stringResource(id = R.string.artist_1),
            fontWeight = FontWeight.Thin,
            fontStyle = FontStyle.Italic

        )
        Text(
            stringResource(id = R.string.artwork_1_year),
            fontWeight = FontWeight.Thin,
            fontStyle = FontStyle.Italic
        )

    }
}

@Composable
fun DisplayController(
    modifier: Modifier = Modifier

) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.Center,

    ) {
        Button(onClick = { /* handle previous button click */}) {
            Text(text = "Previous")
        }

        Button(
            onClick = { /* handle next button click */},
            modifier = Modifier.padding(start = 8.dp)
            ) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtWorkImagePreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}