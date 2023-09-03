@file:OptIn(ExperimentalMaterialApi::class)

package com.example.sampleapp.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sampleapp.data.source.local.camerasLocal.RoomEntity
import com.example.sampleapp.ui.theme._333333
import com.example.sampleapp.ui.theme._F6F6F6
import kotlinx.coroutines.launch

@Composable
fun ColumnScope.Cameras(
    rooms: List<RoomEntity>,
    onFavoriteClick: (id: Int) -> Unit,
    update: () -> Unit,
) {
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    fun refresh() = refreshScope.launch {
        refreshing = true
        update()
        refreshing = false
    }
    val state = rememberPullRefreshState(refreshing, ::refresh)

    Box(Modifier.pullRefresh(state)) {
        LazyColumn(Modifier.fillMaxSize().background(_F6F6F6)) {
            rooms.forEach { roomEntity->
                val roomName = roomEntity.name

                item {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(20.dp, 16.dp),
                        text = roomName,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Light,
                        color = _333333,
                    )
                }

                val cameras = roomEntity.cameras
                items(
                    items = cameras,
                    key = { it.id }
                ) { camerasEntity ->
                    CamerasSwipebaleCard(
                        camerasEntity,
                        onFavoriteClick = onFavoriteClick
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
    }

}



























