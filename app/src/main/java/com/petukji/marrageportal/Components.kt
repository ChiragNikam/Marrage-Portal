package com.petukji.marrageportal

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petukji.marrageportal.member_info.presentation.GirlCompleteInfoActivity
import com.petukji.marrageportal.ui.theme.transientWhite

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
                        color = if (selectedOption == 1) MaterialTheme.colorScheme.background else Color.Transparent,
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
                        color = if (selectedOption == 2) MaterialTheme.colorScheme.background else Color.Transparent,
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
                        color = if (selectedOption == 3) MaterialTheme.colorScheme.background else Color.Transparent,
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

            AnimatedVisibility(
                visible = selectedOption == 1,
                enter = slideInVertically(
                    // Enters by sliding down from offset -fullHeight to 0.
                    initialOffsetY = { fullHeight -> -fullHeight }
                ),
                exit = slideOutVertically(
                    // Exits by sliding up from offset 0 to -fullHeight.
                    targetOffsetY = { fullHeight -> -fullHeight }
                )
            ) {
                onMobileOTP()
            }

            AnimatedVisibility(
                visible = selectedOption == 2,
                enter = slideInVertically(
                    // Enters by sliding down from offset -fullHeight to 0.
                    initialOffsetY = { fullHeight -> -fullHeight }
                ),
                exit = slideOutVertically(
                    // Exits by sliding up from offset 0 to -fullHeight.
                    targetOffsetY = { fullHeight -> -fullHeight }
                )
            ) {
                onMobileOTP()
            }
            AnimatedVisibility(visible = selectedOption == 3,
                enter = slideInVertically(
                    // Enters by sliding down from offset -fullHeight to 0.
                    initialOffsetY = { fullHeight -> -fullHeight }
                ),
                exit = slideOutVertically(
                    // Exits by sliding up from offset 0 to -fullHeight.
                    targetOffsetY = { fullHeight -> -fullHeight }
                )
            ) {
                Box {
                    Text(text = "Google")
                }
            }
        }
    }
}

@Composable
fun ProfilePicCircular(modifier: Modifier = Modifier, imageVector: ImageVector) {
    Icon(
        modifier = modifier
            .background(MaterialTheme.colorScheme.inversePrimary, shape = CircleShape)
            .padding(5.dp),
        imageVector = imageVector,
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
fun AvailableGirlsVerticalGrid(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 110.dp)
    ) {
        items(8) {
            SingleGirlView(modifier = Modifier
                .background(color = Color.Transparent, shape = RoundedCornerShape(8.dp))
                .clickable {
                    context.startActivity(Intent(context, GirlCompleteInfoActivity::class.java))
                })
        }
    }
}

@Composable
fun SingleGirlView(
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    greenTick: Boolean = true,
    blueTick: Boolean = true
) {
    Box(modifier = modifier) {
        ImageViewWithGreenBlueTick(
            imageModifier = imageModifier,
            tickModifier = Modifier.align(Alignment.TopEnd)
        )

        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp)
                .background(
                    transientWhite, RoundedCornerShape(30.dp)
                )
                .height(45.dp)
                .padding(horizontal = 18.dp, vertical = 5.dp),
            text = "Priya Jaiswal, 45\nDelhi, India",
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontWeight = FontWeight(700),
            lineHeight = 14.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun ImageViewWithGreenBlueTick(
    tickModifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    imageHeight: Dp = 200.dp,
    greenTick: Boolean = true,
    blueTick: Boolean = true
) {

    Image(
        painter = painterResource(id = R.drawable.tryimage),
        contentDescription = "girl pic",
        contentScale = ContentScale.Crop,
        modifier = imageModifier
            .padding(top = 5.dp)
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(imageHeight)
    )
    Row(
        modifier = tickModifier
            .height(20.dp)
            .width(50.dp)

    ) {
        if (greenTick) GreenTick()

        Spacer(modifier = Modifier.width(4.dp))

        if (blueTick) BlueTick()
    }
}

// status view
@Preview
@Composable
fun ProfileStatus(modifier: Modifier = Modifier) {
//    Box {
    val localDensity = LocalDensity.current

    var columnHeight by remember {
        mutableStateOf(10.dp)
    }
    Row {
        Column(
            modifier = modifier
                .background(
                    MaterialTheme.colorScheme.secondary,
                    RoundedCornerShape(8.dp)
                )
                .onGloballyPositioned { layoutCoordinates ->
                    columnHeight = with(localDensity) { layoutCoordinates.size.height.toDp() }
                }
                .padding(horizontal = 10.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(
                text = "Total Profile Accepted: 50",
                color = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Total Profile Rejected by you: 5",
                color = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Total Rejected by other: 3",
                color = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Total Shortlisted profile: 150",
                color = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "People viewed your profile: 67",
                color = MaterialTheme.colorScheme.onSecondary
            )
            Text(text = "Viewed by your: 97", color = MaterialTheme.colorScheme.onSecondary)
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .padding(top = 8.dp)
                .height(columnHeight),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val modifier = Modifier.weight(1f)
            val contentScale = ContentScale.Crop
            Image(
                modifier = modifier,
                painter = painterResource(id = R.drawable.icon_facebook),
                contentDescription = "facebook icon",
                contentScale = contentScale
            )
            Image(
                modifier = modifier,
                painter = painterResource(id = R.drawable.icon_instagram),
                contentDescription = "facebook icon",
                contentScale = contentScale
            )
            Image(
                modifier = modifier,
                painter = painterResource(id = R.drawable.icon_whatsapp),
                contentDescription = "facebook icon",
                contentScale = contentScale
            )
        }

    }


}

@Composable
fun GreenTick(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier.background(Color.Green, shape = CircleShape),
        imageVector = Icons.Outlined.Check,
        contentDescription = "green tick",
        tint = Color.White
    )
}

@Composable
fun BlueTick(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier.background(Color.Blue, shape = CircleShape),
        imageVector = Icons.Outlined.Check,
        contentDescription = "green tick",
        tint = Color.White
    )
}


@Composable
fun SearchDropBoxView(
    modifier: Modifier = Modifier,
    propertyName: String = "Qualification",
    propertyValue: String = "Delhi"
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .background(Color.Red, shape = RoundedCornerShape(16.dp))
                .padding(top = 8.dp, bottom = 8.dp, start = 18.dp, end = 18.dp)
                .height(24.dp)
                .width(120.dp),
        ) {
            Text(
                text = propertyName,
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                color = Color.White
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 128.dp)
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(30.dp)
                )
                .border(2.dp, color = Color.Black, shape = RoundedCornerShape(30.dp))
                .height(24.dp)
                .widthIn(min = 150.dp, max = 180.dp)
                .padding(horizontal = 20.dp, vertical = 3.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(text = propertyValue, fontSize = 14.sp)
        }

    }


}

@Composable
fun SearchPropertiesButtons(modifier: Modifier = Modifier, propertyName: String) {
    Button(modifier = modifier, onClick = { /*TODO*/ }) {
        Text(text = propertyName)
    }
}

// tab options row for Request Received Screen
@Composable
fun TabRowOptions(modifier: Modifier = Modifier) {
    Column {
        Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(30.dp)) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_info),
                    contentDescription = "info"
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_document),
                    contentDescription = "info"
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.filled_profile),
                    contentDescription = "info"
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_info),
                    contentDescription = "info"
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_document),
                    contentDescription = "info"
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.filled_profile),
                    contentDescription = "info"
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Settings, contentDescription = "settings")
        }
    }
}

@Preview
@Composable
fun TabRowOptionsPreview() {
    TabRowOptions(
        modifier = Modifier.background(
            Color.White,
            RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
        )
    )
}

@Preview
@Composable
fun PreviewSearchDropBoxLayout() {
    SearchDropBoxView()
}


@Preview
@Composable
fun PreviewProfilePic() {
//    ProfilePicCircular(image = Icons.Filled.Person)
}

@Preview
@Composable
fun PreviewTextField() {
    myTextField(hintText = "")
}

@Preview
@Composable
fun PreviewSearchPropertiesButtons() {
    SearchPropertiesButtons(propertyName = "City")
}
