package com.example.sampleapp.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.sampleapp.R
import com.example.sampleapp.ui.screen.helper.page.ErrorPage
import com.example.sampleapp.ui.screen.helper.page.WaitPage
import com.example.sampleapp.ui.theme._333333

@Composable
fun MainScreen(
    vm: MainVM,
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 20.dp),
            text = stringResource(id = R.string.my_house),
            textAlign = TextAlign.Center,
            fontSize = 21.sp,
            color = _333333,
        )

        val tabState = remember { mutableStateOf(0) }
        val titles = listOf(stringResource(id = R.string.cameras), stringResource(id = R.string.doors))

        TabRow(
            containerColor = Color.White,
            selectedTabIndex = tabState.value,
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = tabState.value == index,
                    onClick = { tabState.value = index },
                    text = {
                        Text(
                            overflow = TextOverflow.Ellipsis,
                            text = title,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal,
                            color = _333333,
                        )
                    }
                )
            }
        }

        val s = vm.state.observeAsState().value
        s?.let {
            when(tabState.value) {
                0 -> {
                    when {
                        s.camerasWait -> { WaitPage() }
                        s.camerasError != null -> {
                            ErrorPage(
                                e = s.camerasError,
                                onClick = {
                                    vm.getCameras()
                                }
                            )
                        }
                        else -> {
                            Cameras(
                                rooms = s.rooms,
                                onFavoriteClick = { id->
                                    vm.setCameraFavorite(id)
                                },
                                update = {
                                    vm.updateCameras()
                                }
                            )
                        }
                    }
                }
                1 -> {
                    when {
                        s.doorsWait -> { WaitPage() }
                        s.doorsError != null -> {
                            ErrorPage(
                                e = s.doorsError,
                                onClick = {
                                    vm.getDoors()
                                }
                            )
                        }
                        else -> {
                            val editDialog = remember { mutableStateOf(false) }
                            val editId = remember { mutableStateOf(0) }
                            val str = remember { mutableStateOf("") }

                            Doors(
                                doors = s.doors,
                                onEditClick = { id, text ->
                                    editDialog.value = true
                                    editId.value = id
                                    str.value = text
                                },
                                onFavoriteClick = { id->
                                    vm.setDoorFavorite(id)
                                },
                                update = {
                                    vm.updateDoors()
                                }
                            )

                            if(editDialog.value) {
                                EditDialog(
                                    str = str.value,
                                    onDismissRequest = {
                                        editDialog.value = false
                                    },
                                    onEdit = {
                                        vm.setDoorName(editId.value, it)
                                        editDialog.value = false
                                    }
                                )
                            }
                        }
                    }
                }
                else -> {}
            }
        }
    }
}

@Composable
private fun EditDialog(
    str: String,
    onDismissRequest: () -> Unit,
    onEdit: (text: String) -> Unit,
) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = onDismissRequest
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            val text = remember { mutableStateOf(str) }
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .imePadding()
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = text.value,
                    onValueChange = {
                        text.value = it
                    }
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onEdit(text.value) }
                ) {
                    Text(stringResource(id = R.string.edit))
                }
            }
        }
    }
}
















