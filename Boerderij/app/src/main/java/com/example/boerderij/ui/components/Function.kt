package com.example.boerderij.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.boerderij.R
import com.example.boerderij.data.RetrofitInstance
import com.example.boerderij.model.activity.Activity
import com.example.boerderij.ui.theme.BoerderijTheme

@Composable
fun Function(activity: Activity, goDetail: (Int) -> Unit) {

    var imageName = activity.description.split("|")[1]
    var imageResourceId = getImageResourceId(imageName)
    var shortdescription = activity.description.split("|")[0]
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = { goDetail(activity.id) }),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .border(2.dp, Color(0xFFA9ACA5), MaterialTheme.shapes.large)
        )
        {
            Text(
                text = activity.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000),
                modifier = Modifier.padding(vertical = 8.dp)
                    .padding(16.dp)
            )

            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
                    .background(Color.Gray, shape = MaterialTheme.shapes.medium)
                    .aspectRatio(1.77f),
                contentScale = ContentScale.Crop
            )

            Text(
                text = shortdescription,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(vertical = 8.dp)
                .padding(16.dp),
                maxLines = Int.MAX_VALUE

            )
        }
    }

}

private fun getImageResourceId(imageName: String): Int {
    var resourceId = R.drawable::class.java.getField("${imageName}").getInt(null)

    return resourceId
}