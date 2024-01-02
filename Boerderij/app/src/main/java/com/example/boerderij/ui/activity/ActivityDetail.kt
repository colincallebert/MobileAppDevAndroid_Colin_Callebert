package com.example.boerderij.ui.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boerderij.R
import com.example.boerderij.network.activityApi.ActivitiesApiState
import com.example.boerderij.network.activityApi.Activity
import com.example.boerderij.viewmodel.ActivityViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Composable function for the Activity Detail screen.
 */
@Composable
fun ActivityCard(activity: Activity, goRegistration: (Int) -> Unit) {
    // Extracting image name and longdescription from the activity description (format: "shortdescription|image|longdescription")
    var imageName = activity.description.split("|")[1]
    var imageResourceId = getImageResourceId(imageName)
    var longDescription = activity.description.split("|")[2]

    // State variable to handle the toggle for activity deletion
    var toggle by remember { mutableStateOf(false) }

    // Check and perform action based on toggle state
    if (toggle) {
        verwijderActivity(activity.id)
        toggle = false
    }

    // Card displaying detailed information about the activity
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(2.dp, Color(0xFFA9ACA5))
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Title of the activity
            item {  // Image associated with the activity
                Image(
                    painter = painterResource(id = imageResourceId),
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(1.77f),
                    contentScale = ContentScale.Crop
                )
            }
            item {
                Text(
                    text = activity.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(16.dp)
                )
            }



            item {
                // Long description of the activity
                Text(
                    text = longDescription,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .padding(horizontal = 16.dp)
                )
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                // Formatted date of the activity
                Text(
                    text = formattedDate(activity.starttime),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(bottom = 4.dp, top = 16.dp)
                        .padding(horizontal = 16.dp)
                )

                // Formatted time range of the activity
                Text(
                    text = formattedTimeRange(activity.starttime, activity.endtime),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .padding(horizontal = 16.dp)
                )
            }
            item {
                // Button for registration or cancellation based on availability
                if (activity.amount == 0) {
                    Button(
                        onClick = { goRegistration(activity.id) },
                        modifier = Modifier
                            .padding(8.dp)
                            .padding(horizontal = 8.dp)
                            .testTag("${stringResource(R.string.form)}"),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text(
                            text = "Maak een reservering",
                            modifier = Modifier.padding(),
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                } else {
                    Button(
                        onClick = { toggle = true },
                        modifier = Modifier
                            .padding(8.dp)
                            .padding(horizontal = 8.dp),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text(
                            text = "Reservering annuleren",
                            modifier = Modifier.padding(),
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun verwijderActivity(id: Int) {
    // LaunchedEffect to asynchronously delete the activity registration
    val activityViewModel: ActivityViewModel = viewModel(factory = ActivityViewModel.Factory)
    LaunchedEffect(Unit) {
        activityViewModel.deleteRegistration(id)
    }
}

/**
 * Retrieves the resource ID for the given image name.
 *
 * @param imageName The name of the image.
 * @return The resource ID associated with the image name.
 */
private fun getImageResourceId(imageName: String): Int {
    var resourceId = R.drawable::class.java.getField("${imageName}").getInt(null)
    return resourceId
}

/**
 * Formats the date string into a format that just shows the date.
 *
 * @param dateString The date string to be formatted.
 * @return The formatted date string.
 */
fun formattedDate(dateString: String): String {
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val date: Date = try {
        dateFormatter.parse(dateString) ?: Date()
    } catch (e: Exception) {
        Date()
    }

    val dateFormatter2 = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormatter2.format(date)
}

/**
 * Formats the time range string into a format that shows the start and end time.
 *
 * @param startTime The start time of the activity.
 * @param endTime The end time of the activity.
 * @return The formatted time range string.
 */
fun formattedTimeRange(startTime: String, endTime: String): String {
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

    val startTimeDate: Date = try {
        dateFormatter.parse(startTime) ?: Date()
    } catch (e: Exception) {
        Date()
    }

    val endTimeDate: Date = try {
        dateFormatter.parse(endTime) ?: Date()
    } catch (e: Exception) {
        Date()
    }

    val dateFormatter24 = SimpleDateFormat("HH:mm", Locale.getDefault())

    val startTimeString = dateFormatter24.format(startTimeDate)
    val endTimeString = dateFormatter24.format(endTimeDate)

    return "$startTimeString - $endTimeString"
}

@Composable
fun ActivityDetail(
    id: Int,
    activityViewModel: ActivityViewModel = viewModel(factory = ActivityViewModel.Factory),
    goRegistration: (Int) -> Unit
) {
    val uiActivityListState by activityViewModel.uiActivityListState.collectAsState()
    val activityApiState = activityViewModel.activityApiState


    when (activityApiState) {
        is ActivitiesApiState.Error -> {
            Text(text = stringResource(R.string.error))
        }

        is ActivitiesApiState.Loading -> {
            Text(text = stringResource(R.string.loading))
        }

        is ActivitiesApiState.Success -> {
            val activity = uiActivityListState.activityList.find { activity -> activity.id == id }
            if (activity != null) {
                // Activity found, execute ActivityCard
                ActivityCard(activity, goRegistration)
            }
        }
    }

}
