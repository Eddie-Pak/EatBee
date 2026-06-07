package com.eatbee.presentation.ui.screen.main.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.eatbee.domain.model.EatBeeMatzip
import com.eatbee.presentation.R
import com.eatbee.presentation.ui.component.EatBeeSpacer
import com.eatbee.presentation.ui.theme.EatBeeDimens

@Composable
fun MatzipDetailContent(
    matzip: EatBeeMatzip,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.7f)
            .padding(
                horizontal = EatBeeDimens.Padding.XSmall,
                vertical = EatBeeDimens.Padding.Large
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(R.drawable.icon_arrow_back),
                    contentDescription = "뒤로가기"
                )
            }

            Text(
                text = "맛집 정보",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        EatBeeSpacer(EatBeeDimens.Padding.Medium)

        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = EatBeeDimens.Padding.XXLarge),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = matzip.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            EatBeeSpacer(EatBeeDimens.Padding.Small)

            Text(
                text = matzip.roadAddress,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            EatBeeSpacer(EatBeeDimens.Padding.Small)

            Text(
                text = matzip.link,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}