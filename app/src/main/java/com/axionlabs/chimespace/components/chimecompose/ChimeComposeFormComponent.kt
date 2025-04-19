package com.axionlabs.chimespace.components.chimecompose

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.axionlabs.chimespace.components.CommonTextField
import com.axionlabs.chimespace.models.request.chime.ChimeCreateOrUpdateRequest

@Composable
fun ChimeComposeFormComponent(
    modifier: Modifier = Modifier,
    onSubmit: (ChimeCreateOrUpdateRequest) -> Unit = {},
) {
    val chimeTitleState = rememberSaveable { mutableStateOf("") }
    val chimeContentState = rememberSaveable { mutableStateOf("") }
    val isPrivateState = rememberSaveable { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    val isValidForm =
        remember(chimeTitleState.value, chimeContentState.value) {
            chimeTitleState.value.trim().isNotEmpty() && chimeContentState.value.trim().isNotEmpty()
        }

    LazyColumn(
        modifier =
            modifier
                .padding(16.dp)
                .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                CommonTextField(
                    valueState = chimeTitleState,
                    placeholder = "Chime Title",
                    onValueChange = { chimeTitleState.value = it },
                    onAction = KeyboardActions { keyboardController?.hide() },
                    modifier = Modifier.fillMaxWidth(),
                )

                CommonTextField(
                    valueState = chimeContentState,
                    placeholder = "What are your thoughts today?",
                    maxLines = Int.MAX_VALUE,
                    singleLine = false,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .heightIn(min = 150.dp, max = 400.dp),
                    onValueChange = { chimeContentState.value = it },
                    onAction =
                        KeyboardActions {
                            if (!isValidForm) return@KeyboardActions
                            keyboardController?.hide()
                        },
                )

                FilterChip(
                    onClick = { isPrivateState.value = !isPrivateState.value },
                    label = { Text("Is Private") },
                    selected = isPrivateState.value,
                    leadingIcon =
                        if (isPrivateState.value) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Done icon",
                                )
                            }
                        } else {
                            null
                        },
                )

                FilledTonalButton(
                    onClick = {
                        if (isValidForm) {
                            val request =
                                ChimeCreateOrUpdateRequest(
                                    chimeTitle = chimeTitleState.value.trim(),
                                    chimeContent = chimeContentState.value.trim(),
                                    isPrivate = isPrivateState.value,
                                )
                            onSubmit.invoke(request)
                        } else {
                            Toast
                                .makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT)
                                .show()
                        }
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Create,
                        contentDescription = "Post",
                        modifier = Modifier.size(ButtonDefaults.IconSize),
                    )
                    Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                    Text(text = "Post")
                }
            }
        }
    }
}
