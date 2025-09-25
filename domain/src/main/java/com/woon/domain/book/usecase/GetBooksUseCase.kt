package com.woon.domain.book.usecase

import androidx.paging.PagingData
import com.woon.domain.book.entity.Book
import com.woon.domain.book.entity.SortType
import com.woon.domain.book.exception.BookException
import com.woon.domain.book.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.net.UnknownHostException
import javax.inject.Inject

class GetBooksUseCase
@Inject constructor(
    private val bookRepository: BookRepository
) {
    fun getRemoteBooks(
        query: String,
        filter: String
    ): Flow<PagingData<Book>> {
        return try{
            bookRepository.getRemote(query, filter)
        }catch (e: UnknownHostException) {
            throw BookException.Network(e)
        }catch (e: Exception) {
            throw BookException.Unknown(e)
        }
    }

    fun getLocalBooks(
        query: String,
        filter: String,
        range: Pair<Int, Int>
    ): Flow<PagingData<Book>> {
        return try{
            bookRepository.getLocal(
                query = query,
                filter = SortType.from(filter),
                range = range
            )
        } catch (e: IllegalStateException) {
            throw BookException.NotFound(e)
        } catch (e: Exception) {
            throw BookException.Unknown(e)
        }
    }
}