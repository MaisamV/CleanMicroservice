package com.mvs.service.util

import com.papsign.ktor.openapigen.model.IOperationModelFactory
import com.papsign.ktor.openapigen.model.operation.OperationModel

object ServiceInfoFactory: IOperationModelFactory {
    override fun create(): OperationModel {
        return ServiceInfoModel()
    }
}