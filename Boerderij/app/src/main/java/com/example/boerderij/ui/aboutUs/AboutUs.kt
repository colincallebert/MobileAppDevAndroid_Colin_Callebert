package com.example.boerderij.ui.aboutUs

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boerderij.R

/**
 * Composable function for the About Us screen.
 */
@Composable
fun AboutUs() {
    // State variables to handle click events for different actions
    var toggleMaps by remember { mutableStateOf(false) }
    var toggleEmail by remember { mutableStateOf(false) }
    var toggleCall by remember { mutableStateOf(false) }

    // Check and perform actions based on state changes
    if (toggleMaps) {
        openGoogleMaps("Steenakkerstraat 4, 8900 Ieper")
        toggleMaps = false
    }
    if (toggleEmail) {
        openEmailToWebsite()
        toggleEmail = false
    }
    if (toggleCall) {
        openCall("+32 499 99 99 99")
        toggleCall = false
    }

    // LazyColumn to display different information sections
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Section 1: Introduction and Image
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Onze ouderenboerderij",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = stringResource(id = R.string.kortoverons),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.farm_image),
                        contentDescription = "Farm Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clip(shape = MaterialTheme.shapes.medium),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        // Section 2: Address
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { toggleMaps = true }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Adres",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Steenakkerstraat 4, 8900 Ieper",
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                }
            }
        }

        // Section 3: Phone Number
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { toggleCall = true },
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Telefoonnummer",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Phone, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "+32 499 99 99 99",
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                }
            }
        }

        // Section 4: Email
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { toggleEmail = true },
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Email",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Email, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "ouderenboerderij@gmail.com",
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                }
            }
        }
    }
}

/**
 * Preview function for the About Us screen.
 */
@Preview
@Composable
fun AboutUsPagePreview() {
    AboutUs()
}

/**
 * Opens Google Maps with the specified address.
 *
 * @param address The address to open in Google Maps.
 */
@Composable
fun openGoogleMaps(address: String) {
    val googleMapsUrl = "https://www.google.com/maps/search/?api=1&query=${Uri.encode(address)}"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(googleMapsUrl))
    LocalContext.current.startActivity(intent)
}

/**
 * Opens an email client with a pre-filled email to the specified email address.
 */
@Composable
fun openEmailToWebsite() {
    val context = LocalContext.current
    val email = "ouderenboerderij@gmail.com"
    val subject = "Vraag via website"
    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        putExtra(Intent.EXTRA_SUBJECT, subject)
    }
    context.startActivity(emailIntent)
}

/**
 * Opens the phone dialer with the specified phone number.
 *
 * @param number The phone number to dial.
 */
@Composable
fun openCall(number: String) {
    val context = LocalContext.current
    val callIntent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$number")
    }
    context.startActivity(callIntent)
}






