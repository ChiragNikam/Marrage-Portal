package com.petukji.matrimonialapp.auth.presentation

import android.widget.ImageView
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petukji.matrimonialapp.R
import com.petukji.matrimonialapp.auth.domain.PersonalDetailsViewModel

@Composable
fun AdditionalDetailScreen(
    viewModel: PersonalDetailsViewModel
) {
    rememberScrollState()
    val additionalDetails = viewModel.personalDetails.value

    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Additional Detail",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Row() {
            OutlinedTextField(
                value = additionalDetails.permanentCity,
                onValueChange = { viewModel.updateCity(it) },
                label = { Text("City") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = additionalDetails.permanentState,
                onValueChange = { viewModel.updateState(it) },
                label = { Text("State") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = additionalDetails.permanentCountry,
            onValueChange = { viewModel.updateCountry(it) },
            label = { Text("Country") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = additionalDetails.permanentAddress,
            onValueChange = { viewModel.updatePermanentAddress(it) },
            label = { Text("Current Address") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = additionalDetails.permanentPIN,
                onValueChange = { viewModel.updatePermanentPincode(it) },
                label = { Text("Pincode") },
                modifier = Modifier.weight(1.5f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                modifier = Modifier
                    .weight(0.2f)
                    .size(30.dp)

            )
        }
        var isChecked by remember {
            mutableStateOf(false)
        }

        Text(
            text = "Is this same as permanent address?",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 18.sp
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = !isChecked }
            )
            Text(
                text = "No",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 18.sp
            )
        }

        if (isChecked) {
            Row() {
                OutlinedTextField(
                    value = additionalDetails.correspondenceCity,
                    onValueChange = { viewModel.updateCorrespondenceCity(it) },
                    label = { Text("City") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    value = additionalDetails.correspondenceState,
                    onValueChange = { viewModel.updateCorrespondenceState(it) },
                    label = { Text("State") },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = additionalDetails.correspondenceCountry,
                onValueChange = { viewModel.updateCorrespondenceCountry(it) },
                label = { Text("Country") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = additionalDetails.correspondenceAddress,
                onValueChange = { viewModel.updateCorrespondenceAddress(it) },
                label = { Text("Current Address") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = additionalDetails.correspondencePIN,
                    onValueChange = { viewModel.updateCorrespondencePincode(it) },
                    label = { Text("Pincode") },
                    modifier = Modifier.weight(1.5f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    modifier = Modifier
                        .weight(0.2f)
                        .size(30.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.lightRed)
            ),
            onClick = {

            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AdditionalDetailScreenPreview() {
    AdditionalDetailScreen(viewModel = PersonalDetailsViewModel())
}