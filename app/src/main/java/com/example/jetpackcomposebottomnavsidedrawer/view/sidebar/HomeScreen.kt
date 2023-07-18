package com.example.jetpackcomposebottomnavsidedrawer.view.sidebar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import com.example.jetpackcomposebottomnavsidedrawer.navigation.Screens
import com.example.jetpackcomposebottomnavsidedrawer.viewmodel.MainViewModel
import com.example.jetpackcomposebottomnavsidedrawer.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    viewModel.setCurrentScreen(Screens.DrawerScreens.Home)
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Box(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.text),
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}