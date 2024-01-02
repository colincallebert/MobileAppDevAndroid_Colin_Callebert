package com.example.boerderij.ui.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boerderij.R
import com.example.boerderij.network.activityApi.Activity
import com.example.boerderij.viewmodel.ActivityViewModel

/**
 * Composable function for the Activities screen.
 */
@Composable
fun ActivityList(activity: Activity, goDetail: (Int) -> Unit, modifier: Modifier = Modifier) {
    // Extracting image name from the activity description (format: "shortdescription|image|longdescription")
    val imageName = activity.description.split("|")[1]
    // Getting resource ID for the image
    val imageResourceId = getImageResourceId(imageName)

    Column(
        modifier = modifier
            // Adding a test tag for UI testing
            .testTag("${stringResource(R.string.detail)}+${activity.id}")
            // Making the item clickable and providing a click listener
            .clickable(onClick = { goDetail(activity.id) })
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 7.dp)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Displaying the image associated with the activity
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .background(Color.Gray, shape = MaterialTheme.shapes.medium)
                    .clip(MaterialTheme.shapes.extraSmall)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Displaying the title of the activity
            Text(
                text = activity.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 6.dp)
            )
            // Adding a more options icon
            Icon(
                imageVector = Icons.Filled.MoreHoriz,
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .padding(start = 8.dp)
            )
        }

        // Adding a divider between activity items
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun Activities(
    goDetail: (Int) -> Unit,
    activityViewModel: ActivityViewModel = viewModel(factory = ActivityViewModel.Factory)
) {
    val uiActivityListState by activityViewModel.uiActivityListState.collectAsState()

    // Displaying a list of activities using LazyVerticalGrid
    LazyVerticalGrid(
        modifier = Modifier.padding(top = 8.dp),
        columns = GridCells.Adaptive(minSize = 500.dp)
    ) {
        // Check if the filtered list is empty
        if (uiActivityListState.activityList.filter { activity ->  activity.amount > 0}.isEmpty()) {
            // Display a message when there are no reserved activities
            item {
                Text(
                    text = "Geen gereserveerde activiteiten",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                )
            }
        } else {
            // Display individual activity items when the list is not empty
            items(uiActivityListState.activityList.filter { activity ->  activity.amount > 0}) { activity ->
                // Displaying individual activity items with added padding
                ActivityList(
                    activity, goDetail, Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}


/**
 * Retrieves the resource ID for the given image name.
 *
 * @param imageName The name of the image.
 * @return The resource ID associated with the image name.
 */
private fun getImageResourceId(imageName: String): Int {
    // Using reflection to get the resource ID dynamically
    return R.drawable::class.java.getField(imageName).getInt(null)
}
