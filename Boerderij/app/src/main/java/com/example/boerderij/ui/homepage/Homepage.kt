package com.example.boerderij.ui.homepage

import Discription
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boerderij.model.activity.Activity
import com.example.boerderij.ui.components.ActivityCard

/**
 * Composable function for the Homepage.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homepage(activities: List<Activity>, goDetail: (Int) -> Unit) {
    // State for handling the search text
    var searchText by remember { mutableStateOf("") }

    // LazyColumn containing the homepage content and be able to scroll
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Item displaying the description
        item {
            Discription()
        }

        // Item containing the search bar
        item {
            OutlinedTextField(
                value = searchText,
                // Update the search text on input change
                onValueChange = {
                    searchText = it
                },
                placeholder = { Text("Filter op activiteit") },
                // Adding Icon and styling to be like Material Design
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                ),
                shape = RoundedCornerShape(24.dp)
            )
        }

        // Items displaying the filtered activities
        items(activities.filter { it.title.contains(searchText, ignoreCase = true) }) { activity ->
            ActivityCard(
                activity = activity,
                goDetail = { id ->
                    goDetail(id)
                }
            )
            // Spacer to add space between activity cards
            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )
        }
    }
}
