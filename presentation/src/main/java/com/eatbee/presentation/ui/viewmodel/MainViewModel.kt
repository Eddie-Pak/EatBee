package com.eatbee.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eatbee.domain.model.EatBeeMatzip
import com.eatbee.domain.usecase.GetAllMatzipUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllMatzipUseCase: GetAllMatzipUseCase
) : ViewModel() {

    val matzipList: StateFlow<List<EatBeeMatzip>> = getAllMatzipUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}