package com.yuresko.lenta.base

import com.yuresko.lenta.models.ModelPost

sealed class UiState {
    object Loading : UiState()
    class Success(val data: List<ModelPost>) : UiState()
    class Error(val error: Exception) : UiState()
}