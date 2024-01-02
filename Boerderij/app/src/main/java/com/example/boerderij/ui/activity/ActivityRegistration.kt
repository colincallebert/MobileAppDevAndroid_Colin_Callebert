package com.example.boerderij.ui.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boerderij.viewmodel.ActivityViewModel

/**
 * Composable function for the Activity Form.
 */
@Composable
fun ActivityReservation(id: Int, goDetail: (Int) -> Unit) {
    // State variables for user input
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var numberOfPeople by remember { mutableStateOf(1) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var numberOfPeopleError by remember { mutableStateOf<String?>(null) }


    // State variable to handle the toggle for activity registration
    var toggle by remember { mutableStateOf(false) }

    fun validateInput(): Boolean {
        var isValid = true
        if (email.isBlank()) {
            emailError = "Email is verplicht"
            isValid = false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Email is niet geldig"
            isValid = false
        }
        if (numberOfPeople <= 0) {
            numberOfPeopleError = "Aantal personen moet groter zijn dan 0"
            isValid = false
        }
        return isValid
    }

    // Check and perform action based on toggle state
    if (toggle) {
        registerActivity(id, numberOfPeople)
        goDetail(id)
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
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        //Input field for user's first name
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Voornaam") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        // Input field for user's name
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Naam") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Input field for user's email
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = null
            },
            label = { Text("Email") },
            isError = emailError != null,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        if (emailError != null) {
            Text(
                text = emailError!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        } else {
            Text(text = "*Email is verplicht", style = MaterialTheme.typography.bodySmall)
        }

        // Input field for the number of people with error text
        OutlinedTextField(
            value = numberOfPeople.toString(),
            onValueChange = {
                numberOfPeople = it.toIntOrNull() ?: 0
                numberOfPeopleError = null
            },
            label = { Text("Aantal personen") },
            isError = numberOfPeopleError != null,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        if (numberOfPeopleError != null) {
            Text(
                text = numberOfPeopleError!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        } else {
            Text(text = "*Aantal is verplicht ", style = MaterialTheme.typography.bodySmall)
        }

        // Button to submit the reservation and navigate to the activity detail
        Button(
            onClick = {
                if (validateInput()) {
                    toggle = true
                }
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .widthIn(min = 200.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Text(text = "Reservatie maken")
        }
    }
}


@Composable
fun registerActivity(id: Int, numberOfPeople: Int) {
    val activityViewModel: ActivityViewModel = viewModel(factory = ActivityViewModel.Factory)
    LaunchedEffect(key1 = id, key2 = numberOfPeople) {
        activityViewModel.registreren(id, numberOfPeople)
    }
}