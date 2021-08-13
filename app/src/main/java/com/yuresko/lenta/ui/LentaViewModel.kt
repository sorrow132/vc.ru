package com.yuresko.lenta.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yuresko.lenta.data.Repository
import com.yuresko.lenta.data.paging.PagingSourcePost
import com.yuresko.lenta.models.ModelPost
import com.yuresko.lenta.utils.Constants
import com.yuresko.lenta.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LentaViewModel @Inject constructor(
    private val repository: Repository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    val posts: Flow<PagingData<ModelPost>> =
        Pager(
            PagingConfig(
                enablePlaceholders = false,
                pageSize = Constants.PER_PAGE,
                maxSize = Constants.MAX_PAGE_SIZE,
            ), pagingSourceFactory = {
                PagingSourcePost(repository)
            })
            .flow.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}