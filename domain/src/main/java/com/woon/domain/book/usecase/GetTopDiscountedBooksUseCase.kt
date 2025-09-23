package com.woon.domain.book.usecase

import com.woon.domain.book.entity.Book
import javax.inject.Inject

class GetTopDiscountedBooksUseCase
@Inject constructor(

){
    suspend operator fun invoke(
        books: List<Book>,
        limit: Int
    ) : List<Book> {
        return  books.sortedByDescending {
            it.salePrice.changeRate(it.price)
        }.take(limit)
    }
}