package com.woon.repository.book.mapper

import com.woon.datasource.remote.book.response.book.Document
import com.woon.domain.book.entity.Book
import com.woon.domain.book.entity.BookStatus
import com.woon.domain.money.entity.Money
import com.woon.repository.book.ext.toDate

internal fun Document.toDomain() : Book {
    return Book (
        authors = authors,
        contents = contents,
        time = datetime.toDate(),
        isbn = isbn,
        salePrice = Money(salePrice),
        price = Money(price),
        publisher = publisher,
        status = BookStatus.from(status),
        title = title,
        image = thumbnail,
        translators = translators,
        url = url,
        favorite = false
    )
}