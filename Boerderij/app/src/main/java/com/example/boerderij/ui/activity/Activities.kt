package com.example.boerderij.ui.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.boerderij.R
import com.example.boerderij.model.activity.Activity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityList(activity: Activity, goDetail: (Int) -> Unit, modifier: Modifier = Modifier) {
    val imageName = activity.description.split("|")[1]
    val imageResourceId = getImageResourceId(imageName)

    Column(
        modifier = modifier
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

            Text(
                text = activity.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f).padding(horizontal = 6.dp)
            )
            Icon(
                imageVector = Icons.Filled.MoreHoriz,
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .padding(start = 8.dp)
            )
        }

        Divider(modifier = Modifier.height(1.dp).fillMaxWidth())
    }
}

@Composable
fun Activities(activities: List<Activity>, goDetail: (Int) -> Unit) {
    LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
        items(activities) { activity ->
            ActivityList(activity, goDetail)
        }
    }
}

private fun getImageResourceId(imageName: String): Int {
    return R.drawable::class.java.getField(imageName).getInt(null)
}
