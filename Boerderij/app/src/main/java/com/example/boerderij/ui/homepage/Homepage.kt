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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boerderij.network.activityApi.Activity
import com.example.boerderij.ui.components.Function
import com.example.boerderij.viewmodel.ActivityViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.example.boerderij.R
import com.example.boerderij.network.activityApi.ActivitiesApiState
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homepage(goDetail: (Int) -> Unit
             ,activityViewModel: ActivityViewModel = viewModel(factory = ActivityViewModel.Factory)
) {
    var searchText by remember { mutableStateOf("") }


    val uiActivityListState by activityViewModel.uiActivityListState.collectAsState()
    val activityApiState = activityViewModel.activityApiState

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Discription()
        }
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
        when (activityApiState) {
            // Display error text when there is an error fetching news
            is ActivitiesApiState.Error -> {

            }

            is ActivitiesApiState.Loading -> {

            }

            // Display the news list when data is successfully fetched
            is ActivitiesApiState.Success -> {
                val filteredActivities = uiActivityListState.activityList.filter { activity ->
                    activity.title.contains(searchText, ignoreCase = true)
                }

                items(filteredActivities) { activity ->
                    Function(
                        activity = activity,
                        goDetail = { id ->
                            goDetail(id)
                        }
                    )
                    Spacer(modifier = Modifier
                        .height(8.dp)
                    )
                }
            }
        }

    }
}
