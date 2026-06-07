package com.eatbee.presentation.ui.screen.main.bottomsheet

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eatbee.domain.model.EatBeeMatzip
import com.eatbee.presentation.ui.component.MatzipCard
import com.eatbee.presentation.ui.theme.EatBeeDimens

@Composable
fun MatzipListContent(
    matzipList: List<EatBeeMatzip>,
    onItemClick: (EatBeeMatzip) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxHeight(0.7f),
        contentPadding = PaddingValues(bottom = EatBeeDimens.Padding.Small)
    ) {
        items(
            items = matzipList,
            key = { it.id }
        ) { matzip ->
            MatzipCard(matzip) { matzip ->
                onItemClick(matzip)
            }
        }
    }
}