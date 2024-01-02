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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.boerderij.R
import com.example.boerderij.network.activityApi.Activity

/**
 * Composable function representing a UI component for displaying information about an activity.
 *
 * @param activity The activity data to be displayed.
 * @param goDetail Callback function to navigate to the detail screen for the activity.
 */
@Composable
fun Function(activity: Activity, goDetail: (Int) -> Unit) {
    // Extract image name and resource ID from activity description
    val imageName = activity.description.split("|")[1]
    val imageResourceId = getImageResourceId(imageName)
    val shortDescription = activity.description.split("|")[0]

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .testTag("${stringResource(R.string.detail)}+${activity.id}")
            .clickable(onClick = { goDetail(activity.id) }),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .border(2.dp, Color(0xFFA9ACA5))
        ) {
            // Display the image with content scale set to crop
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1.77f),
                contentScale = ContentScale.Crop
            )

            // Display the title of the activity
            Text(
                text = activity.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000),
                modifier = Modifier.padding(16.dp)
            )

            // Display the short description of the activity
            Text(
                text = shortDescription,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(16.dp),
                maxLines = Int.MAX_VALUE
            )
        }
    }
}

/**
 * Gets the resource ID for the image based on the image name.
 *
 * @param imageName The name of the image.
 * @return The resource ID of the image.
 */
private fun getImageResourceId(imageName: String): Int {
    val resourceId = R.drawable::class.java.getField("${imageName}").getInt(null)
    return resourceId
}
