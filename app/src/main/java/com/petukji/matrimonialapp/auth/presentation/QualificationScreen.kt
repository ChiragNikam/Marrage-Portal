package com.petukji.matrimonialapp.auth.presentation

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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

@Composable
fun QualificationScreen(viewModel: PersonalDetailsViewModel) {
    val personalDetails = viewModel.personalDetails.value

    val occupations = listOf("Job", "Business", "Professional")
    val selectedOccupation = remember { mutableStateOf(occupations[0]) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Qualification",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedTextField(
                value = personalDetails.degree1,
                onValueChange = { viewModel.updateDegree(it) },
                label = { Text("Degree") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = personalDetails.passoutYear1,
                onValueChange = { viewModel.updatePassOutYear(it) },
                label = { Text("Passout Year") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = personalDetails.college1,
            onValueChange = { viewModel.updateCollege(it) },
            label = { Text("College") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OccupationRadioGroup(
            selectedOccupation = selectedOccupation.value,
            onOccupationChange = { selectedOccupation.value = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedTextField(
                value = personalDetails.companyName,
                onValueChange = { viewModel.updateCompany(it) },
                label = { Text("Company") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = personalDetails.designation,
                onValueChange = { viewModel.updateDesignation(it) },
                label = { Text("Designation") },
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedTextField(
                value = personalDetails.annualIncome,
                onValueChange = { viewModel.updateAnnualIncome(it) },
                label = { Text("Annual Income") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = personalDetails.occupation,
                onValueChange = { viewModel.updateExperience(it) },
                label = { Text("Experience") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = personalDetails.permanentAddress,
            onValueChange = { viewModel.updateAddress(it) },
            label = { Text("Address") },
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

@Composable
fun OccupationRadioGroup(
    selectedOccupation: String,
    onOccupationChange: (String) -> Unit
) {
    val occupations = listOf("Job", "Business", "Professional")

    Column {
        Text(text = "Occupation", style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(8.dp))
        occupations.forEach { occupation ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = occupation == selectedOccupation,
                    onClick = { onOccupationChange(occupation) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primary
                    )
                )
                Text(text = occupation, style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@Preview
@Composable
fun QualificationScreenPreview() {
    QualificationScreen(PersonalDetailsViewModel())
}