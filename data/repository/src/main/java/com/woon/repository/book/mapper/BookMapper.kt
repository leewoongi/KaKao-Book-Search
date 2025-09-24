package com.woon.repository.book.mapper

import com.woon.datasource.local.room.entity.BookEntity
import com.woon.datasource.remote.book.response.book.Document
import com.woon.domain.book.entity.Book
import com.woon.domain.book.entity.BookStatus
import com.woon.domain.money.entity.Money
import com.woon.repository.book.ext.toDate
import java.util.Date

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
        isFavorite = false
    )
}

internal fun Book.toEntity() : BookEntity {
    return BookEntity(
        isbn = isbn,
        title = title,
        authors = authors.joinToString(", "),
        contents = contents,
        time = time.time,
        price = price.value,
        salePrice = salePrice.value,
        publisher = publisher,
        status = status.name,
        image = image,
        translators = translators.joinToString(", "),
        url = url,
        favorite = isFavorite
    )
}

internal fun BookEntity.toDomain(): Book {
    return Book (
        authors = authors.split(", ").map { it.trim() },
        contents = contents,
        time = Date(time),
        isbn = isbn,
        salePrice = Money(salePrice),
        price = Money(price),
        publisher = publisher,
        status = BookStatus.from(status),
        title = title,
        image = image,
        translators = translators.split(", ").map { it.trim() },
        url = url,
        isFavorite = favorite
    )
}