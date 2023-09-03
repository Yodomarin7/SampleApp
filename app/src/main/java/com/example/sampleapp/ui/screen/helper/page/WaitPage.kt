package com.example.sampleapp.ui.screen.helper.page

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sampleapp.R

@Composable
fun WaitPage(
    text: String? = null,
    r: Int? = null
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        Arrangement.Center, Alignment.CenterHorizontally
    ) {
        var result = stringResource(id = R.string.loading)
        if(r != null) result = stringResource(id = r)
        if(text != null) result = text

        Text(text = result)
        Box(Modifier.height(8.dp))
        CircularProgressIndicator()
    }
}