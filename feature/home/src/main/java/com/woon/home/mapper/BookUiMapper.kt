package com.woon.home.mapper

import com.woon.core.ext.toFormattedString
import com.woon.domain.book.entity.Book
import com.woon.home.model.BookUiModel

internal fun Book.toUiModel() : BookUiModel {
    return BookUiModel(
        authors = authors,
        contents = contents,
        time = time.toFormattedString(),
        isbn = isbn,
        salePrice = salePrice.toCurrencyString(),
        price = price.toCurrencyString(),
        salePercent = price.changeRateString(salePrice),
        publisher = publisher,
        status = status.name,
        title = title,
        image = image,
        translators = translators,
        url = url
    )
}