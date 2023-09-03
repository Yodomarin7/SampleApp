package com.example.sampleapp.ui.screen.helper.page

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sampleapp.data.source.helper.NotSuccess
import com.example.sampleapp.R

@Composable
fun ErrorPage(
    e: Throwable?,
    btnTxt: String = stringResource(id = R.string.try_again),
    isAgainBtn: Boolean = true,
    onClick: () -> Unit = { }
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        Arrangement.Center, Alignment.CenterHorizontally
    ) {
        val msg = when(e) {
            is NotSuccess -> {
                stringResource(id = R.string.not_success)
            }
            else -> e?.message
        }

        var error = stringResource(id = R.string.error)
        msg?.let { error += ": $msg" }

        Text(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            text = error,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.error
        )
        if(isAgainBtn) {
            Box(Modifier.height(8.dp))
            Button(onClick = { onClick() }) {
                Text(text = btnTxt)
            }
        }
    }
}