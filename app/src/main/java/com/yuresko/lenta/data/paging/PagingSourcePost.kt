package com.yuresko.lenta.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yuresko.lenta.data.Repository
import com.yuresko.lenta.extensions.pagingSucceeded
import com.yuresko.lenta.models.ModelPost

class PagingSourcePost(
    private val repository: Repository,
) : PagingSource<Int, ModelPost>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ModelPost> {

        val page = params.key ?: FIRST_PAGE_INDEX
        return repository.getPosts(page.toString()).pagingSucceeded { data ->
            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = data.lastOrNull()?.id
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ModelPost>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 423151
    }

}