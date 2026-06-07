package com.eatbee.domain.usecase

import com.eatbee.domain.repository.EatBeeMatzipRepository
import javax.inject.Inject

class InitializeMatzipDataUseCase @Inject constructor(
    private val repository: EatBeeMatzipRepository
) {
    suspend operator fun invoke() = repository.initializeMatzipData()
}