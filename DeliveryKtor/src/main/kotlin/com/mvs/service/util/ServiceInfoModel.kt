package com.mvs.service.util

import com.mvs.service.projectName
import com.papsign.ktor.openapigen.model.operation.OperationModel

class ServiceInfoModel(
    var serviceCode: String? = null,
    var created: String? = null,
    var expired: String? = null,
    var modified: String? = null,
    var forSandbox: Boolean = true,
    var supplier: Long = 0,
    var beta: Boolean = false,
): OperationModel() {
    fun setServiceCode(value: Long) {
        serviceCode = "${projectName}_$value"
    }
}