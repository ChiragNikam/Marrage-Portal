package com.petukji.matrimonialapp.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.petukji.matrimonialapp.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PickUpYourFav(navController: NavController) {

    val optionCreativity = listOf(
        "Traveling", "Learning Languages", "History",
        "Science", "Technology", "Space Exploration", "Psychology",
        "Philosophy", "Art History"
    )

    val optionSports = listOf(
        "Football (Soccer)", "Basketball", "Cricket", "Tennis",
        "Swimming", "Running", "Cycling", "Yoga", "Golf", "Hiking",
    )

    val optionHobby = listOf(
        "Painting", "Photography", "Cooking", "Gardening", "Reading",
        "Writing", "Music", "Dancing", "Sculpture", "Collecting Coins"
    )

    val selectedOptions = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Pick up your favorite", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            Modifier
                .fillMaxWidth(1f)
                .wrapContentHeight(align = Alignment.Top),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            optionCreativity.fastForEachIndexed { _, element ->
                val isSelected = selectedOptions.contains(element)
                InputChip(
                    selected = isSelected,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .align(alignment = Alignment.CenterVertically),
                    onClick = {
                        if (isSelected) {
                            selectedOptions.remove(element)
                        } else {
                            selectedOptions.add(element)
                        }
                    },
                    label = { Text(element) }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            optionSports.fastForEachIndexed { _, element ->
                val isSelected = selectedOptions.contains(element)
                InputChip(
                    selected = isSelected,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .align(alignment = Alignment.CenterVertically),
                    onClick = {
                        if (isSelected) {
                            selectedOptions.remove(element)
                        } else {
                            selectedOptions.add(element)
                        }
                    },
                    label = { Text(element) }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            optionHobby.fastForEachIndexed { _, element ->
                val isSelected = selectedOptions.contains(element)
                InputChip(
                    selected = isSelected,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .align(alignment = Alignment.CenterVertically),
                    onClick = {
                        if (isSelected) {
                            selectedOptions.remove(element)
                        } else {
                            selectedOptions.add(element)
                        }
                    },
                    label = { Text(element) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.lightRed)
            ),
            onClick = {
                navController.navigate("qualification")
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChipGroupReflowSamplePreview() {
    PickUpYourFav(rememberNavController())
}