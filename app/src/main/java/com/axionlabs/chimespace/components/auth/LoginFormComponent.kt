package com.axionlabs.chimespace.components.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.axionlabs.chimespace.components.CommonTextField
import com.axionlabs.chimespace.components.PasswordTextField
import com.axionlabs.chimespace.viewmodel.AuthViewModel

@Composable
fun LoginFormComponent(modifier: Modifier = Modifier, authViewModel: AuthViewModel = hiltViewModel()){
    val userNameState = rememberSaveable { mutableStateOf("") }
    val passwordState = rememberSaveable {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val isValidForm = remember(userNameState.value, passwordState.value) {
        userNameState.value.trim().isNotEmpty() && passwordState.value.trim().isNotEmpty()
    }
    val context = LocalContext.current
    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            CommonTextField(
                valueState = userNameState,
                placeholder = "Username",
                onAction = KeyboardActions{
                    keyboardController?.hide()
                },
                onValueChange = {
                    userNameState.value = it
                }
            )
            PasswordTextField(
                valueState = passwordState,
                placeholder = "Password",
                onAction = KeyboardActions {
                    if (!isValidForm) return@KeyboardActions
                    keyboardController?.hide()
                },
                onValueChange = {
                    passwordState.value = it
                }
            )
            Button(
                onClick = {
                    if (!isValidForm) return@Button
                    authViewModel.login(userNameState.value.trim(), passwordState.value.trim())
                    if(authViewModel.data.value.e?.message?.isNotEmpty() == true){
                        Toast.makeText(context, "Login Failed:"+authViewModel.data.value.e.toString(), Toast.LENGTH_SHORT).show()

                    }
                    else{
                        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                    }

                }
            ){
                Text(text = "Login")
            }
        }
    }
}