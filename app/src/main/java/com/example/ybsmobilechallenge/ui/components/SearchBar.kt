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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    onQueryChanged: (String) -> Unit
) {
    var textState by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .border(border = BorderStroke(0.dp, MaterialTheme.colorScheme.primary))
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                trailingIcon = {
                    IconButton(
                        onClick = {
                            onQueryChanged(textState)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search...",
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
                label = { Text("Search...") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    onQueryChanged(textState)
                })
            )
        }
    }
}