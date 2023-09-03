@file:OptIn(ExperimentalMaterialApi::class)

package com.example.sampleapp.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sampleapp.data.source.local.doorsLocal.DoorEntity
import com.example.sampleapp.ui.theme._F6F6F6
import kotlinx.coroutines.launch

@Composable
fun ColumnScope.Doors(
    doors: List<DoorEntity>,
    onEditClick: (id: Int, str: String) -> Unit,
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
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(
                items = doors,
                key = { it.id }
            ) { doorEntity ->
                DoorsSwipebaleCard(
                    doorEntity,
                    onEditClick = onEditClick,
                    onFavoriteClick = onFavoriteClick,
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
    }
}


























