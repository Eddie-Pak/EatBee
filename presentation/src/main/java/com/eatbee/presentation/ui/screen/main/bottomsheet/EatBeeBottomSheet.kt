package com.eatbee.presentation.ui.screen.main.bottomsheet

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eatbee.presentation.ui.theme.EatBeeDimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EatBeeBottomSheetScaffold(
    sheetState: SheetState,
    sheetContent: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {

    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        sheetPeekHeight = EatBeeDimens.Height.BottomSheetPeekHeight,
        sheetContainerColor = MaterialTheme.colorScheme.surface,
        sheetContent = { sheetContent() },
    ) { paddingValues ->
        content(paddingValues)
    }
}