package com.eatbee.domain.usecase

import com.eatbee.domain.model.EatBeeMatzip
import com.eatbee.domain.repository.EatBeeMatzipRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMatzipUseCase @Inject constructor(
    private val repository: EatBeeMatzipRepository
) {
    operator fun invoke(): Flow<List<EatBeeMatzip>> = repository.getAllMatzip()
}