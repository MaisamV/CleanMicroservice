package com.mvs.exception

inline fun handleError(error: (BaseException) -> Unit, body: ()-> Unit) {
    try {
        body.invoke()
    } catch (e: BaseException) {
        e.printStackTrace()
        error.invoke(e)
    } catch (e: Throwable) {
        e.printStackTrace()
        error.invoke(UnknownException())
    }
}

fun <T: BaseException> T.toErrorData(): ErrorData = ErrorData(localMessage, listOf(localMessage), null, code)