package com.mvs.service.util

import com.papsign.ktor.openapigen.model.operation.OperationModel
import java.util.*

class ServiceInfoModel(
    var serviceCode: Long? = null,
    var created: Date? = null,
    var expired: Date? = null,
    var modified: Date? = null,
    var forSandbox: Boolean = true,
    var supplier: Long = 0,
    var beta: Boolean = false,
): OperationModel()