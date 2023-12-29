package com.example.boerderij.ui.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.boerderij.data.RetrofitInstance
import com.example.boerderij.modelview.ActivityController


@Composable
fun ActivityReservation(id: Int, goDetail: (Int) -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var numberOfPeople by remember { mutableStateOf(1) }

    var toggle by remember { mutableStateOf(false) }

    if (toggle) {
        registerActivity(id, numberOfPeople)
        toggle = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Resevatie maken",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = numberOfPeople.toString(),
            onValueChange = { numberOfPeople = it.toIntOrNull() ?: 1 },
            label = { Text("Number of People") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Button(
            onClick = {
                toggle = true
                goDetail(id)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Reservatie maken")
        }
    }
}

@Composable
fun registerActivity(id: Int, numberOfPeople: Int) {
    LaunchedEffect(Unit) {
        ActivityController().registreren(id, numberOfPeople)

    }
}