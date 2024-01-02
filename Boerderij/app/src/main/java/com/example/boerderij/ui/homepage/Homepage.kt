package com.example.boerderij.ui.homepage

import Discription
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boerderij.network.activityApi.ActivitiesApiState
import com.example.boerderij.ui.components.Function
import com.example.boerderij.viewmodel.ActivityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homepage(
    goDetail: (Int) -> Unit,
    activityViewModel: ActivityViewModel = viewModel(factory = ActivityViewModel.Factory)
) {
    var searchText by remember { mutableStateOf("") }

    // Collect the UI state and activity list state from the ViewModel
    val uiActivityListState by activityViewModel.uiActivityListState.collectAsState()
    val activityApiState = activityViewModel.activityApiState

    // Composable for displaying the UI
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Item for displaying a description
        item {
            Discription()
        }
        // Item for displaying a search field
        item {
            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                },
                placeholder = { Text("Filter op activiteit") },
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

        // Display different content based on the API state
        when (activityApiState) {
            // Display error text when there is an error fetching activities
            is ActivitiesApiState.Error -> {
                item {
                    Text(
                        text = "Error fetching activities",
                        color = Color.Red
                    )
                }
            }

            // Display a loading indicator when data is being fetched
            is ActivitiesApiState.Loading -> {
                item {
                    Text(
                        text = "Loading activities..."
                    )
                }
            }

            // Display the activities list when data is successfully fetched
            is ActivitiesApiState.Success -> {
                val filteredActivities = uiActivityListState.activityList.filter { activity ->
                    activity.title.contains(searchText, ignoreCase = true)
                }

                // Display each activity using the Function Composable
                items(filteredActivities) { activity ->
                    Function(
                        activity = activity,
                        goDetail = { id ->
                            goDetail(id)
                        }
                    )
                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                    )
                }
            }
        }
    }
}
