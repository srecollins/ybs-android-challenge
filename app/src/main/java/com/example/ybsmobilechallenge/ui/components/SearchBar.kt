package com.example.ybsmobilechallenge.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    onSearch: (String, Boolean) -> Unit
) {
    var textState by remember { mutableStateOf("") }
    var matchAllTags by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .border(border = BorderStroke(0.dp, MaterialTheme.colorScheme.primary))
            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .align(AbsoluteAlignment.Right),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Match ALL Tags")
                Checkbox(
                    checked = matchAllTags,
                    onCheckedChange = { matchAllTags = it }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                onSearch(textState, matchAllTags)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Comma Separated Tags Search",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    value = textState,
                    onValueChange = { newText ->
                        textState = newText
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedTextColor = Color.DarkGray,
                        unfocusedTextColor = Color.DarkGray,
                        focusedLeadingIconColor = Color.DarkGray,
                        unfocusedLeadingIconColor = Color.DarkGray,
                        focusedSupportingTextColor = Color.DarkGray,
                        unfocusedSupportingTextColor = Color.DarkGray
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    label = { Text("Comma Separated Tags...") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        onSearch(textState, matchAllTags)
                    })
                )
            }
        }
    }
}