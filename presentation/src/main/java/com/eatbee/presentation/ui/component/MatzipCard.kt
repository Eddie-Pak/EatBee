package com.eatbee.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.eatbee.domain.model.EatBeeMatzip
import com.eatbee.presentation.ui.theme.EatBeeDimens

@Composable
fun MatzipCard(
    matzip: EatBeeMatzip,
    onItemClick: (EatBeeMatzip) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = EatBeeDimens.Padding.Large,
                vertical = EatBeeDimens.Padding.Small
            ),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(EatBeeDimens.Elevation.Low),
        onClick = { onItemClick(matzip) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(EatBeeDimens.Padding.Medium),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = matzip.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            EatBeeSpacer(EatBeeDimens.Padding.XSmall)

            Text(
                text = matzip.roadAddress,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            EatBeeSpacer(EatBeeDimens.Padding.XSmall)

            Text(
                text = matzip.link,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}