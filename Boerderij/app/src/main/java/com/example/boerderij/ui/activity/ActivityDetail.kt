package com.example.boerderij.ui.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.boerderij.R
import com.example.boerderij.model.activity.Activity
import com.example.boerderij.modelview.ActivityController
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ActivityDetail(activity: Activity, goRegistration: (Int) -> Unit) {

    var imageName = activity.description.split("|")[1]
    var imageResourceId = getImageResourceId(imageName)
    var longDescription = activity.description.split("|")[2]

    var toggle by remember { mutableStateOf(false) }

    if (toggle) {
        verwijderActivity(activity.id)
        toggle = false
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(2.dp, Color(0xFFA9ACA5), MaterialTheme.shapes.large)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = activity.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 4.dp)
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
                text = longDescription,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = formattedDate(activity.starttime),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 4.dp, top = 16.dp)
            )

            Text(
                text = formattedTimeRange(activity.starttime, activity.endtime),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            if (activity.amount == 0) {
                Button(
                    onClick = { goRegistration(activity.id) } ,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text="Maak een reservering",
                        modifier = Modifier.padding(),
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }  else {
                Button(
                    onClick = {  toggle = true } ,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text="Reservering annuleren",
                        modifier = Modifier.padding(),
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}

@Composable
fun verwijderActivity(id: Int) {
    LaunchedEffect(Unit) {
        ActivityController().deleteRegistration(id)

    }
}

private fun getImageResourceId(imageName: String): Int {
    var resourceId = R.drawable::class.java.getField("${imageName}").getInt(null)

    return resourceId
}

fun formattedDate(dateString: String): String {
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val date: Date = try {
        dateFormatter.parse(dateString) ?: Date()
    } catch (e: Exception) {
        Date()
    }

    val dateFormatter2 = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return dateFormatter2.format(date)
}

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

    val dateFormatter2 = SimpleDateFormat("hh:mm a", Locale.getDefault())

    val startTimeString = dateFormatter2.format(startTimeDate)
    val endTimeString = dateFormatter2.format(endTimeDate)

    return "$startTimeString - $endTimeString"
}