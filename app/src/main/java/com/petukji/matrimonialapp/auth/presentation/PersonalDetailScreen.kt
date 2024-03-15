package com.petukji.matrimonialapp.auth.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.petukji.matrimonialapp.R
import com.petukji.matrimonialapp.auth.domain.PersonalDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PersonalDetailsForm(viewModel: PersonalDetailsViewModel) {
    val personalDetails = viewModel.personalDetails.value

//    val selectedDate = remember { mutableStateOf(LocalDate.now()) }
    val openDialog = remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = null)

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Personal Details",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedTextField(
                value = personalDetails.firstName,
                onValueChange = { viewModel.updateFirstName(it) },
                label = { Text("First Name") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = personalDetails.lastName,
                onValueChange = { viewModel.updateLastName(it) },
                label = { Text("Last Name") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
//            horizontalArrangement = Arrangement.SpaceAround
        ) {

            OutlinedTextField(
                value = "${datePickerState.selectedDateMillis ?: "Date of Birth"}",
                readOnly = true,
                onValueChange = {},
//                label = { Text("") },
                trailingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) },
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        openDialog.value = true
                    }
            )

            if (openDialog.value) {
                DatePickerDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                openDialog.value = false
                            }
                        ) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                openDialog.value = false
                            }
                        ) {
                            Text("CANCEL")
                        }
                    },
                    colors = DatePickerDefaults.colors()
                ) {
                    DatePicker(
                        state = datePickerState
                    )

                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = personalDetails.age,
                onValueChange = { viewModel.updateAge(it) }, // Consider disabling editing
                label = { Text("Age") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = personalDetails.email,
            onValueChange = { viewModel.updateEmail(it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = personalDetails.maritalStatus,
            onValueChange = { viewModel.updateMaritalStatus(it) },
            label = { Text("Marital Status") },
            trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = personalDetails.timeOfBirth,
            onValueChange = { viewModel.updateTimeOfBirth(it) },
            label = { Text("Time of Birth") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = personalDetails.gotra,
            onValueChange = { viewModel.updateBloodGroup(it) },
            label = { Text("Blood Group") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedTextField(
                value = personalDetails.height,
                onValueChange = { viewModel.updateHeight(it) },
                label = { Text("Height") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = personalDetails.dependentUponYou,
                onValueChange = { viewModel.updateWeight(it) },
                label = { Text("Weight") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedTextField(
                value = personalDetails.bodyColor,
                onValueChange = { viewModel.updateColor(it) },
                label = { Text("Color") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = personalDetails.bodyType,
                onValueChange = { viewModel.updateBodyType(it) },
                label = { Text("Body type") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = personalDetails.dietPreference,
            onValueChange = { viewModel.updateDiet(it) },
            label = { Text("Diet") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.lightRed)
            ),
            onClick = { }
        ) {
            Text(text = "Next")
        }

    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PersonalDetailPreview() {
    PersonalDetailsForm(PersonalDetailsViewModel())
}
