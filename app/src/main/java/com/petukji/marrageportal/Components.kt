package com.petukji.marrageportal

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthMenus(modifier: Modifier = Modifier, onMobileOTP: @Composable (() -> Unit)) {
    var selectedOption by remember {
        mutableIntStateOf(1)
    }
    Row(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.spacedBy(90.dp),
            horizontalAlignment = Alignment.End
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                modifier = Modifier
                    .background(
                        color = if (selectedOption == 1) MaterialTheme.colorScheme.onPrimary else Color.Transparent,
                        shape = if (selectedOption == 1) RoundedCornerShape(
                            topStart = 30.dp,
                            bottomStart = 30.dp
                        ) else RoundedCornerShape(0.dp)
                    )
                    .clickable {
                        selectedOption = 1
                    }
                    .padding(20.dp),
                text = "Mobile/OTP",
                fontSize = 18.sp,
                fontWeight = FontWeight(700),
                color = if (selectedOption == 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
            )
            Text(
                modifier = Modifier
                    .background(
                        color = if (selectedOption == 2) MaterialTheme.colorScheme.onPrimary else Color.Transparent,
                        shape = if (selectedOption == 2) RoundedCornerShape(
                            topStart = 30.dp,
                            bottomStart = 30.dp
                        ) else RoundedCornerShape(0.dp)
                    )
                    .clickable {
                        selectedOption = 2
                    }
                    .padding(20.dp),
                text = "eMail/Pass",
                fontSize = 18.sp,
                fontWeight = FontWeight(700),
                color = if (selectedOption == 2) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
            )
            Text(
                modifier = Modifier
                    .background(
                        color = if (selectedOption == 3) MaterialTheme.colorScheme.onPrimary else Color.Transparent,
                        shape = if (selectedOption == 3) RoundedCornerShape(
                            topStart = 30.dp,
                            bottomStart = 30.dp
                        ) else RoundedCornerShape(0.dp)
                    )
                    .clickable {
                        selectedOption = 3
                    }
                    .padding(20.dp),
                text = "Google",
                fontSize = 18.sp,
                fontWeight = FontWeight(700),
                color = if (selectedOption == 3) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
            )

        }
        Column(
            modifier = Modifier.weight(0.7f)
        ) {
            var animateVisibility by rememberSaveable {
                mutableStateOf(false)
            }
            LaunchedEffect(selectedOption) {
                animateVisibility = !animateVisibility
            }

            AnimatedVisibility(visible = animateVisibility,
                enter = slideInVertically(
                    // Enters by sliding down from offset -fullHeight to 0.
                    initialOffsetY = { fullHeight -> -fullHeight }
                ),
                exit = slideOutVertically(
                    // Exits by sliding up from offset 0 to -fullHeight.
                    targetOffsetY = { fullHeight -> -fullHeight }
                )
            )
            {
                when (selectedOption) {
                    1 -> onMobileOTP()
                    2 -> Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "eMail/Pass")
                    }

                    3 -> Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Google")
                    }
                }
            }
//            AnimatedVisibility(visible = selectedOption == 2,
//                enter = slideInVertically(
//                    // Enters by sliding down from offset -fullHeight to 0.
//                    initialOffsetY = { fullHeight -> fullHeight }
//                ),
//                exit = slideOutVertically(
//                    // Exits by sliding up from offset 0 to -fullHeight.
//                    targetOffsetY = { fullHeight -> fullHeight }
//                )
//            ) {
//                Box(modifier = Modifier.fillMaxSize()) {
//                    Text(text = "eMail/Pass")
//                }
//            }
//            AnimatedVisibility(visible = selectedOption == 3,
//                enter = slideInVertically(
//                    // Enters by sliding down from offset -fullHeight to 0.
//                    initialOffsetY = { fullHeight -> fullHeight }
//                ),
//                exit = slideOutVertically(
//                    // Exits by sliding up from offset 0 to -fullHeight.
//                    targetOffsetY = { fullHeight -> fullHeight }
//                )
//            ) {
//                Box(modifier = Modifier.fillMaxSize()) {
//                    Text(text = "Google")
//                }
//            }
        }
    }
}

@Composable
fun ProfilePicCircular(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier
            .size(100.dp)
            .background(MaterialTheme.colorScheme.inversePrimary, shape = CircleShape)
            .padding(5.dp),
        imageVector = Icons.Filled.Person,
        contentDescription = "profile pic"
    )
}

@Composable
fun myTextField(
    modifier: Modifier = Modifier,
    hintText: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
): String {
    val text = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = modifier, value = text.value, onValueChange = { text.value = it },
        label = {
            Text(text = hintText, fontSize = MaterialTheme.typography.bodySmall.fontSize)
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = keyboardOptions
    )
    return text.value
}

@Preview
@Composable
fun PreviewProfilePic() {
    ProfilePicCircular()
}

@Preview
@Composable
fun PreviewTextField() {
    myTextField(hintText = "")
}