package com.example.jetpackcomposebottomnavsidedrawer.components.dialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.jetpackcomposebottomnavsidedrawer.ui.theme.dark_gray
import com.example.jetpackcomposebottomnavsidedrawer.R
import com.example.jetpackcomposebottomnavsidedrawer.ui.theme.ColorGray
import com.example.jetpackcomposebottomnavsidedrawer.ui.theme.ColorWhite
import com.example.jetpackcomposebottomnavsidedrawer.ui.theme.Purple500


@Composable
fun LogoutDialog() {
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },

            title = { Text(text = stringResource(id = R.string.logout), color = dark_gray) },
            text = { Text(stringResource(id = R.string.wouldyouliketologout), color = dark_gray) },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {

                    Text(stringResource(id = R.string.logoutok), color = Purple500)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false

                    }
                ) {
                    Text(stringResource(id = R.string.cancel), color = ColorGray)
                }
            },
            backgroundColor = ColorWhite,
            contentColor = dark_gray
        )
    }
}