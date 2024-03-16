package com.petukji.matrimonialapp.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.petukji.matrimonialapp.R
import com.petukji.matrimonialapp.auth.domain.PersonalDetailsViewModel

@Composable
fun FamilyDetailScreen(navController: NavController, viewModel: PersonalDetailsViewModel) {
    val personalDetails = viewModel.personalDetails.value

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Family Details",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = personalDetails.fatherName,
            onValueChange = { viewModel.updateFatherName(it) },
            label = { Text("Father Name") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.width(8.dp))

        OutlinedTextField(
            value = personalDetails.motherName,
            onValueChange = { viewModel.updateMotherName(it) },
            label = { Text("Mother Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = personalDetails.brothers,
            onValueChange = { viewModel.updateBrotherName(it) },
            label = { Text("Brother Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = personalDetails.sisters,
            onValueChange = { viewModel.updateSisterName(it) },
            label = { Text("Sister Name") },
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


        OutlinedTextField(
            value = personalDetails.totalFamily,
            onValueChange = { viewModel.totalFamilyMembers(it) },
            label = { Text("Total Family") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.width(8.dp))

        OutlinedTextField(
            value = personalDetails.fatherOccupation,
            onValueChange = { viewModel.updateFatherOccupation(it) },
            label = { Text("Father Occupation") },
            modifier = Modifier.fillMaxWidth(),
        )


        OutlinedTextField(
            value = personalDetails.motherOccupation,
            onValueChange = { viewModel.updateMotherOccupation(it) },
            label = { Text("Mother Occupation") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.lightRed)
            ),
            onClick = {
                navController.navigate("qualification")
            }
        ) {
            Text(text = "Next")
        }

    }
}

@Preview
@Composable
fun FamilyDetailScreenPreview() {
    val navController = rememberNavController()
    val viewModel = remember { PersonalDetailsViewModel() }
    FamilyDetailScreen(navController = navController, viewModel = viewModel)
}
