package com.woon.domain.book.exception

sealed class BookException(
    cause: Throwable? = null
) : Exception(cause) {

    class Network(
        cause: Throwable? = null
    ) : BookException( cause)

    class Unknown(
        cause: Throwable? = null
    ) : BookException(cause)

    class NotFound(
        cause: Throwable? = null
    ) : BookException(cause)

    class UpdateFailure(
        cause: Throwable? = null
    ) : BookException(cause)

    class RemoveFailure(
        cause: Throwable? = null
    ) : BookException(cause)


}