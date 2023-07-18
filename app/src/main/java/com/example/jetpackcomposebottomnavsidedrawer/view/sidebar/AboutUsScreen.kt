package com.example.jetpackcomposebottomnavsidedrawer.view.sidebar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebottomnavsidedrawer.navigation.Screens
import com.example.jetpackcomposebottomnavsidedrawer.ui.theme.BottomNavWithSideBarTheme
import com.example.jetpackcomposebottomnavsidedrawer.viewmodel.MainViewModel

@Composable
fun AboutUsScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    viewModel.setCurrentScreen(Screens.DrawerScreens.AboutUs)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "About us.", style = MaterialTheme.typography.h4)
    }
}