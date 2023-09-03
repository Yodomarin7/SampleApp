@file:OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)

package com.example.sampleapp.ui.screen.main

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.sampleapp.R
import com.example.sampleapp.data.source.local.camerasLocal.CamerasEntity
import com.example.sampleapp.ui.theme._555555
import kotlin.math.roundToInt

@Composable
fun CamerasSwipebaleCard(
    camerasEntity: CamerasEntity,
    onFavoriteClick: (id: Int) -> Unit,
) {
    val density = LocalDensity.current
    val state = remember {
        AnchoredDraggableState(
            initialValue = DragAnchors.Start,
            positionalThreshold = { distance: Float -> distance * 0.5f },
            velocityThreshold = { with(density) { 100.dp.toPx() } },
            animationSpec = tween(),
        )
    }
    val anchors = with (density) {
        DraggableAnchors {
            DragAnchors.Start at 0.dp.toPx()
            DragAnchors.End at -44.dp.toPx()
        }
    }
    SideEffect {
        state.updateAnchors(anchors)
    }

    Box(
        Modifier.fillMaxWidth().padding(20.dp, 0.dp)
    ) {

        Box(
            Modifier.fillMaxHeight().align(Alignment.CenterEnd)
        ) {
            Image(
                painterResource(R.drawable.outline_star), contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .align(Alignment.Center)
                    .clip(CircleShape)
                    .width(36.dp)
                    .clickable {
                        onFavoriteClick(camerasEntity.id)
                    }
            )
        }

        Card(
            Modifier.fillMaxWidth().offset { IntOffset(x = state.requireOffset().roundToInt(), y = 0) }
                .anchoredDraggable(state, Orientation.Horizontal),
            elevation = CardDefaults.cardElevation(3.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(207.dp)
                    .background(Color.Gray)
            ) {
                GlideImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    model = camerasEntity.snapshot,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                if(camerasEntity.rec) {
                    Image(
                        painterResource(R.drawable.rec), contentDescription = null,
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier.padding(8.dp).height(24.dp).align(Alignment.TopStart)
                    )
                }
                if(camerasEntity.favorites) {
                    Image(
                        painterResource(R.drawable.star), contentDescription = null,
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier.padding(8.dp).height(24.dp).align(Alignment.TopEnd)
                    )
                }
                Image(
                    painterResource(R.drawable.play_button), contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clip(CircleShape)
                        .width(60.dp)
                        .clickable { }
                )
            }
            Text(
                text = camerasEntity.name,
                modifier = Modifier.padding(16.dp, 30.dp),
                color = _555555,
                fontSize = 17.sp
            )
        }
    }

}











