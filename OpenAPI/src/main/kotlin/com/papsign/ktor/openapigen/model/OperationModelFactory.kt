package com.papsign.ktor.openapigen.model

import com.papsign.ktor.openapigen.model.operation.OperationModel

object OperationModelFactory : IOperationModelFactory {
    override fun create(): OperationModel {
        return OperationModel()
    }
}