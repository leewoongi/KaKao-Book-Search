package com.woon.favorite.mapper

import com.woon.core.ext.toDate
import com.woon.core.ext.toFormattedString
import com.woon.domain.book.entity.Book
import com.woon.domain.book.entity.BookStatus
import com.woon.domain.money.entity.Money
import com.woon.favorite.model.BookUiModel

internal fun Book.toUiModel() : BookUiModel {
    return BookUiModel(
        authors = convertAuthorsToString(),
        contents = contents,
        time = time.toFormattedString(),
        isbn = isbn,
        salePrice = salePrice.toCurrencyString(),
        price = price.toCurrencyString(),
        salePercent = price.changeRateString(salePrice),
        isDiscount = isDisCount(),
        publisher = publisher,
        status = status.name,
        title = title,
        image = image,
        translators = translators,
        url = url,
        isFavorite = isFavorite,
        query = query
    )
}

internal fun BookUiModel.toDomain() : Book {
    return Book(
        authors = authors.split(", ").map { it.trim() },
        contents = contents,
        time = time.toDate(),
        isbn = isbn,
        salePrice = Money.fromCurrencyString(salePrice),
        price = Money.fromCurrencyString(price),
        publisher = publisher,
        status = BookStatus.valueOf(status),
        title = title,
        image = image,
        translators = translators,
        url = url,
        isFavorite = isFavorite,
        query = query
    )
}