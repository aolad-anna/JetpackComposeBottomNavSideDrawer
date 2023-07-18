package com.example.jetpackcomposebottomnavsidedrawer.view.bottom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.jetpackcomposebottomnavsidedrawer.navigation.Screens
import com.example.jetpackcomposebottomnavsidedrawer.viewmodel.MainViewModel

@Composable
fun SavedScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    viewModel.setCurrentScreen(Screens.HomeScreens.Saved)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Saved .", style = MaterialTheme.typography.h4)
    }
}