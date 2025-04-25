package com.axionlabs.chimespace.components.profile

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.axionlabs.chimespace.models.domain.Chime
import com.axionlabs.chimespace.utils.formatTimeStamp

@Composable
fun ChimeCardComponent(
    modifier: Modifier = Modifier,
    chime: Chime,
    onEdit: (Chime) -> Unit = {},
    onDelete: (Chime) -> Unit = {},
) {
    val showDropDown =
        remember {
            mutableStateOf(false)
        }

    OutlinedCard(
        modifier =
            modifier
                .padding(vertical = 4.dp, horizontal = 16.dp)
                .fillMaxWidth()
                .clickable { Log.d("ChimeCardComponent", "Clicked on: ${chime.chimeTitle}") },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = chime.chimeTitle,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                )

                Column(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.TopEnd),
                ) {
                    IconButton(onClick = { showDropDown.value = !showDropDown.value }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More options")
                    }
                    DropdownMenu(
                        expanded = showDropDown.value,
                        onDismissRequest = { showDropDown.value = false },
                        modifier =
                            Modifier
                                .width(160.dp),
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = "Edit") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit",
                                )
                            },
                            onClick = {
                                showDropDown.value = false
                                onEdit(chime)
                            },
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Delete") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                )
                            },
                            onClick = {
                                showDropDown.value = false
                                onDelete(chime)
                            },
                        )
                    }
                }
            }

            Text(
                text = chime.chimeContent,
                style = MaterialTheme.typography.bodyMedium,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = formatTimeStamp(chime.createdAt),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.padding(end = 4.dp),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                ) {
                    Icon(
                        imageVector = if (chime.isPrivate) Icons.Default.Lock else Icons.Default.Public,
                        modifier = Modifier.size(18.dp),
                        contentDescription = if (chime.isPrivate) "Private" else "Public",
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (chime.isPrivate) "Private" else "Public",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    )
                }
            }
        }
    }
}
