package com.petukji.matrimonialapp.bottom_nav.presentation

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.petukji.matrimonialapp.bottom_nav.components.AvailableGirlsVerticalGrid
import com.petukji.matrimonialapp.member_info.presentation.MemberCompleteInfoActivity
import com.petukji.matrimonialapp.R
import com.petukji.matrimonialapp.bottom_nav.components.SingleGirlView
import com.petukji.matrimonialapp.bottom_nav.domain.HomeViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomSheetContent(bottomPadding: Dp, viewModel: HomeViewModel) {

    val userPreferenceData by viewModel.userPreferenceData.collectAsState()

    val context = LocalContext.current

    Column {
        var showSingleGirlView by rememberSaveable {
            mutableStateOf(false)
        }
        Row {
            IconButton(
                onClick = {
                    showSingleGirlView = !showSingleGirlView
                }) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.icon_cards),
                    contentDescription = "Cards Icon"
                )
            }
            Spacer(modifier = Modifier.weight(1f))

        }
        AnimatedVisibility(
            visible = showSingleGirlView,
            enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)),
            exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f))
        ) {
            val pagerState = rememberPagerState(pageCount = { userPreferenceData.size })

            HorizontalPager(
                modifier = Modifier.padding(bottom = bottomPadding),
                state = pagerState
            ) {
                val currentPage = pagerState.currentPage
                SingleGirlView(
                    data = userPreferenceData[currentPage],
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 40.dp, vertical = 20.dp),
                    imageModifier = Modifier
                        .fillMaxHeight()
                        .clickable {
                            context.startActivity(
                                Intent(
                                    context,
                                    MemberCompleteInfoActivity::class.java
                                )
                            )
                        }
                )
            }
        }
        AnimatedVisibility(
            visible = !showSingleGirlView,
        ) {
            AvailableGirlsVerticalGrid(
                gridItems = userPreferenceData,
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp
                )
            )
        }

    }
}

