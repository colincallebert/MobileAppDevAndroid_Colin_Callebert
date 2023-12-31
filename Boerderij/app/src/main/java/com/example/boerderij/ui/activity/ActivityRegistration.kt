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

/**
 * Composable function for the Activity Form.
 */
@Composable
fun ActivityReservation(id: Int, goDetail: (Int) -> Unit) {
    // State variables for user input
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var numberOfPeople by remember { mutableStateOf(1) }

    // State variable to handle the toggle for activity registration
    var toggle by remember { mutableStateOf(false) }

    // Check and perform action based on toggle state
    if (toggle) {
        registerActivity(id, numberOfPeople)
        toggle = false
    }

    // Column displaying the activity reservation form
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Reservatie maken",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        // Input field for user's name
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Input field for user's email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Input field for the number of people
        OutlinedTextField(
            value = numberOfPeople.toString(),
            onValueChange = { numberOfPeople = it.toIntOrNull() ?: 1 },
            label = { Text("Number of People") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Button to submit the reservation and navigate to the activity detail
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
    // LaunchedEffect to asynchronously register the activity
    LaunchedEffect(Unit) {
        //ActivityController().registreren(id, numberOfPeople)
    }
}