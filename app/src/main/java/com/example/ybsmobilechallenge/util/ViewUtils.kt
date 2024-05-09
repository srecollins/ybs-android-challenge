package com.example.ybsmobilechallenge.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.unit.LayoutDirection

fun halfPadding(innerPadding: PaddingValues): PaddingValues {
    // Calculate half of each padding value
    return PaddingValues(
        start = innerPadding.calculateStartPadding(layoutDirection = LayoutDirection.Ltr) / 2,
        top = innerPadding.calculateTopPadding() / 2,
        end = innerPadding.calculateEndPadding(layoutDirection = LayoutDirection.Ltr) / 2,
        bottom = innerPadding.calculateBottomPadding() / 2
    )
}