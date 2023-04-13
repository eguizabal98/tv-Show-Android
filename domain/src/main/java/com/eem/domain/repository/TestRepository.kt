package com.eem.domain.repository

import kotlinx.coroutines.flow.Flow

interface TestRepository {

    suspend fun getHtml(): Flow<String>
}