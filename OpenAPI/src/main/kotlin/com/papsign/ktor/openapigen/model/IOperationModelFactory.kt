package com.papsign.ktor.openapigen.model

import com.papsign.ktor.openapigen.model.operation.OperationModel

interface IOperationModelFactory {
    fun create(): OperationModel
}