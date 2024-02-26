package com.petukji.marrageportal.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petukji.marrageportal.AvailableGirlsVerticalGrid
import com.petukji.marrageportal.HomeViewModel
import com.petukji.marrageportal.SearchDropBoxView
import com.petukji.marrageportal.SearchPropertiesButtons

// Screen for Advanced Search
@Composable
fun SearchScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    Surface(modifier = modifier) {
        Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Advance Search",
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "Drag Component",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            )

            Spacer(modifier = Modifier.height(42.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Drag able Properties : city, state, country, qualification, degree, age, religion, body type
                Column {
                    val buttonModifier = Modifier.width(128.dp)

                    SearchPropertiesButtons(modifier = buttonModifier, propertyName = "City")
                    SearchPropertiesButtons(modifier = buttonModifier, propertyName = "State")
                    SearchPropertiesButtons(modifier = buttonModifier, propertyName = "Country")
                    SearchPropertiesButtons(
                        modifier = buttonModifier,
                        propertyName = "Qualification"
                    )
                    SearchPropertiesButtons(modifier = buttonModifier, propertyName = "Degree")
                    SearchPropertiesButtons(modifier = buttonModifier, propertyName = "Age")
                    SearchPropertiesButtons(modifier = buttonModifier, propertyName = "Religion")
                    SearchPropertiesButtons(modifier = buttonModifier, propertyName = "Body Type")
                }
                Spacer(modifier = Modifier.width(12.dp))
                // Place where to drop the property: user have to pick a property and drag it into this column to use this property for search
                Column(verticalArrangement = Arrangement.spacedBy(22.dp)) {
                    SearchDropBoxView(propertyName = "Qualification", propertyValue = "BCA")
                    SearchDropBoxView(propertyName = "Religion", propertyValue = "Hindu")
                    SearchDropBoxView(propertyName = "City", propertyValue = "Delhi")
                    SearchDropBoxView(propertyName = "Body Type", propertyValue = "Slim")
                    SearchDropBoxView(propertyName = "", propertyValue = "")
                }
            }
            
            Spacer(modifier = Modifier.height(42.dp))

            Button(onClick = { viewModel.updateShowSearchResultsState(showResults = true) }, contentPadding = PaddingValues(horizontal = 42.dp, vertical = 12.dp), elevation = ButtonDefaults.buttonElevation(4.dp)) {
                Text(text = "Search", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(42.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchViewBottomSheet(viewModel: HomeViewModel) {
    val sheetState = rememberModalBottomSheetState()    // Bottom sheet state

    ModalBottomSheet(
        containerColor = MaterialTheme.colorScheme.background,
        sheetState = sheetState,
        onDismissRequest = { viewModel.updateShowSearchResultsState(false) }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            AvailableGirlsVerticalGrid()
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(viewModel = HomeViewModel())
}