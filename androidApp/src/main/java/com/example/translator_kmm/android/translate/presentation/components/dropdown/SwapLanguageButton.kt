package com.example.translator_kmm.android.translate.presentation.components.dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.translator_kmm.android.R

@Composable
fun SwapLanguagesButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .size(30.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.swap_languages),
            contentDescription = stringResource(id = R.string.swap_languages),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}