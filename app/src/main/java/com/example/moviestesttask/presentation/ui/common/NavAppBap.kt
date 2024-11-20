package com.example.moviestesttask.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviestesttask.R
import com.example.moviestesttask.navigation.NavRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavTopBar(modifier: Modifier = Modifier) {
        TopAppBar(
            title = {},
            actions = {
                IconButton(onClick = { /* do something */ }, Modifier.padding(8.dp)) {
                    Image(
                        painterResource(R.drawable.user_placeholder),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            },
            navigationIcon = {

            },
            modifier = modifier
        )
}


@Preview
@Composable
fun NavTopBarPreview() {
    NavTopBar()
}