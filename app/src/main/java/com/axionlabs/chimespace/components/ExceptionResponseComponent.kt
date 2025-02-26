package com.axionlabs.chimespace.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExceptionResponseComponent(modifier: Modifier = Modifier, message: String, tryAgainFunction: () -> Unit = {}, showTryAgainButton : Boolean = true){
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = message,
        )
        if(showTryAgainButton){
            Button(onClick = {
                tryAgainFunction()

            }) {
                Text(text = "Try Again")
            }
        }


    }
}