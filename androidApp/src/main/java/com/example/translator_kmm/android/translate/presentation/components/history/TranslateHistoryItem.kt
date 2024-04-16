package com.example.translator_kmm.android.translate.presentation.components.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.translator_kmm.android.core.theme.LightBlue
import com.example.translator_kmm.android.translate.presentation.components.textfield.SmallLanguageIcon
import com.example.translator_kmm.android.translate.presentation.theme.gradientSurface
import com.example.translator_kmm.translate.presentation.UiHistoryItem

@Composable
fun TranslateHistoryItem(
    item: UiHistoryItem,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .gradientSurface()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            SmallLanguageIcon(language = item.fromLanguage)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = item.fromText,
                color = LightBlue,
                style = MaterialTheme.typography.body2
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
    }
}