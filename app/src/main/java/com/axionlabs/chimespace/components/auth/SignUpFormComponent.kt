package com.axionlabs.chimespace.components.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.axionlabs.chimespace.components.CommonTextField
import com.axionlabs.chimespace.components.PasswordTextField
import com.axionlabs.chimespace.models.request.auth.SignUpRequest

@Composable
fun SignUpFormComponent(
    modifier: Modifier = Modifier,
    onSignUp: (SignUpRequest) -> Unit,
    isLoading: Boolean?,
    onLoginClick: () -> Unit
) {
    val userNameState = rememberSaveable {
        mutableStateOf("")
    }
    val emailState = rememberSaveable {
        mutableStateOf("")
    }
    val penNameState = rememberSaveable {
        mutableStateOf("")
    }
    val passwordState = rememberSaveable {
        mutableStateOf("")
    }
    val reEnterPasswordState = rememberSaveable {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val isValidForm = remember(
        userNameState.value,
        emailState.value,
        penNameState.value,
        passwordState.value,
        reEnterPasswordState.value
    ) {
        userNameState.value.trim()
            .isNotBlank() && emailState.value.isNotEmpty() && penNameState.value.trim()
            .isNotEmpty() && passwordState.value.trim()
            .isNotEmpty() && reEnterPasswordState.value.trim()
            .isNotEmpty() && passwordState.value.trim() == reEnterPasswordState.value.trim()
    }

    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            CommonTextField(
                valueState = userNameState,
                placeholder = "Username*",
                onAction = KeyboardActions {
                    keyboardController?.hide()
                },
                onValueChange = {
                    userNameState.value = it
                }
            )
            CommonTextField(
                valueState = emailState,
                placeholder = "Email*",
                onAction = KeyboardActions {
                    keyboardController?.hide()
                },
                onValueChange = {
                    emailState.value = it
                }
            )
            CommonTextField(
                valueState = penNameState,
                placeholder = "Pen Name*",
                onAction = KeyboardActions {
                    keyboardController?.hide()
                },
                onValueChange = {
                    penNameState.value = it
                }
            )
            PasswordTextField(
                valueState = passwordState,
                placeholder = "Password*",
                onAction = KeyboardActions {
                    keyboardController?.hide()
                },
                onValueChange = {
                    passwordState.value = it

                }
            )
            PasswordTextField(
                valueState = reEnterPasswordState,
                placeholder = "Re-enter Password*",
                onAction = KeyboardActions {
                    keyboardController?.hide()
                },
                onValueChange = {
                    reEnterPasswordState.value = it
                }
            )
            Text(
                text = "Already Having Account ? Login",
                modifier = Modifier.padding(4.dp).clickable {
                    onLoginClick()
                }
            )
            Button(
                onClick = {
                    if (!isValidForm) return@Button
                    val signUpRequest = SignUpRequest(
                        userName = userNameState.value.trim(),
                        penName = penNameState.value.trim(),
                        email = emailState.value.trim(),
                        password = passwordState.value.trim()
                    )
                    onSignUp(signUpRequest)

                },
                enabled = isValidForm || isLoading == false,
            ) {
                Text(text = "Sign Up")
            }
        }
    }
}