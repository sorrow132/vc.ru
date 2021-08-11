package com.yuresko.lenta.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yuresko.lenta.data.Repository
import com.yuresko.lenta.extensions.pagingSucceeded
import com.yuresko.lenta.models.ModelPost


class PagingSourcePost(
    private val repository: Repository
) : PagingSource<Int, ModelPost>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ModelPost> {
        val page = params.key ?: FIRST_PAGE_INDEX
        return repository.getModels().pagingSucceeded { data ->
            LoadResult.Page(
                data = data,
                prevKey = if (page == 422424) null else page - 1,
                nextKey = if (data.isEmpty()) null else page.plus(1)
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ModelPost>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 422167
    }


}