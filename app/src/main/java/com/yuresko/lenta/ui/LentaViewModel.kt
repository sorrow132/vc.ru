package com.yuresko.lenta.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yuresko.lenta.base.ResponseResult
import com.yuresko.lenta.base.UiState
import com.yuresko.lenta.data.Repository
import com.yuresko.lenta.data.paging.PagingSourcePost
import com.yuresko.lenta.models.ModelPost
import com.yuresko.lenta.utils.Constants
import com.yuresko.lenta.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LentaViewModel @Inject constructor(
    private val repository: Repository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private val _data = MutableLiveData<UiState>(UiState.Loading)
    val data: LiveData<UiState> = _data

    init {
        fetchData()
    }

//    val posts: Flow<PagingData<ModelPost>> =
//        Pager(
//            PagingConfig(
//                enablePlaceholders = false,
//                pageSize = Constants.PER_PAGE,
//                maxSize = Constants.MAX_PAGE_SIZE,
//            ), pagingSourceFactory = {
//                PagingSourcePost(repository)
//            })
//            .flow.cachedIn(viewModelScope)

    private fun fetchData() {
        viewModelScope.launch(dispatchers.io) {
            when (val result = repository.getModels()) {
                is ResponseResult.Error -> {
                    _data.postValue(UiState.Error(result.exception))
                }
                is ResponseResult.Success -> {
                    _data.postValue(result.data.let { UiState.Success(it) })
                }
            }
        }
    }
}