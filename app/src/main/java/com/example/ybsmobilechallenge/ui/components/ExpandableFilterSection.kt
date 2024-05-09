package com.example.ybsmobilechallenge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.ybsmobilechallenge.ui.theme.Green3

@Composable
fun ExpandableFilterSection(
    onTextSearch: (String) -> Unit,
    onTagSearch: (String, Boolean) -> Unit,
    onUsernameSearch: (String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    var textState by remember { mutableStateOf("") }
    var tagState by remember { mutableStateOf("") }
    var usernameState by remember { mutableStateOf("") }
    var matchAllTags by remember { mutableStateOf(false) }


    Column(
        modifier =
        Modifier.background(MaterialTheme.colorScheme.primary)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable { isExpanded = !isExpanded }
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.End)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Search Options...",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Icon(
                    tint = Color.White,
                    modifier = Modifier.align(Alignment.CenterEnd),
                    imageVector = if (!isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (isExpanded) "Collapse" else "Expand"
                )
            }
        }

        if (isExpanded) {
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    OutlinedTextField(
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    onTextSearch(textState)
                                    isExpanded = false
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "Text Search...",
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
                        label = { Text("Text Search...") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(onSearch = {
                            onTextSearch(textState)
                        })
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                // Username Searching
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    OutlinedTextField(
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    onUsernameSearch(usernameState)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "Username Search...",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        },
                        value = usernameState,
                        onValueChange = { newText ->
                            usernameState = newText
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
                        label = { Text("Username Search...") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(onSearch = {
                            onUsernameSearch(usernameState)
                        })
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))


                // Tag Searching
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    OutlinedTextField(
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    onTagSearch(tagState, matchAllTags)
                                    isExpanded = false
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "Comma Separated Tags...",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        },
                        value = tagState,
                        onValueChange = { newText ->
                            tagState = newText
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
                            onTagSearch(tagState, matchAllTags)
                        })
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .align(AbsoluteAlignment.Right),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Match ALL Tags",
                        color = Color.White
                    )
                    Checkbox(
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = Color.White,
                            checkmarkColor = Green3,
                            checkedColor = Color.White
                        ),
                        checked = matchAllTags,
                        onCheckedChange = { matchAllTags = it }
                    )
                }
            }
        }
    }
}
