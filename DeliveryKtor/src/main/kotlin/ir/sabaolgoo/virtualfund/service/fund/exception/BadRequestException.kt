package ir.sabaolgoo.virtualfund.service.fund.exception

import com.mvs.service.exception.RemoteException
import io.ktor.http.*

class BadRequestException: RemoteException(HttpStatusCode.BadRequest, 1, "Parameters are not filled or contain invalid values.", "مقادیر وارد شده صحیح نیست.") {
}