package com.example.boerderij.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.boerderij.R
import com.example.boerderij.model.activity.Activity

/**
 * Composable function for the Activity Card.
 */
@Composable
fun ActivityCard(activity: Activity, goDetail: (Int) -> Unit) {
    // Extracting image name and shortdescription from the activity description (format: "shortdescription|image|longdescription")
    var imageName = activity.description.split("|")[1]
    var imageResourceId = getImageResourceId(imageName)
    var shortDescription = activity.description.split("|")[0]

    // Card containing the activity information
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .testTag("${stringResource(R.string.detail)}+${activity.id}")
            .clickable(onClick = { goDetail(activity.id) }),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .border(2.dp, Color(0xFFA9ACA5), MaterialTheme.shapes.large)
        ) {
            // Activity title
            Text(
                text = activity.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000),
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .padding(16.dp)
            )

            // Activity image
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

            // Activity short description
            Text(
                text = shortDescription,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .padding(16.dp),
                maxLines = Int.MAX_VALUE
            )
        }
    }
}

// Function to get the image resource ID based on the image name
private fun getImageResourceId(imageName: String): Int {
    var resourceId = R.drawable::class.java.getField("${imageName}").getInt(null)
    return resourceId
}
