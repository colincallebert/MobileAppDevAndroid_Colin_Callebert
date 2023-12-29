package com.example.boerderij.ui.homepage

import Discription
import android.util.Log
import android.webkit.RenderProcessGoneDetail
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boerderij.data.RetrofitInstance
import com.example.boerderij.model.activity.Activity
import com.example.boerderij.ui.components.Function
import androidx.navigation.NavController
import com.example.boerderij.ui.components.Destinations


@Composable
fun Homepage(activities: List<Activity>,  goDetail: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Discription()
        }
        items(activities) { activity ->
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


@Composable
@Preview
fun ActivitiesPagePreview() {
}