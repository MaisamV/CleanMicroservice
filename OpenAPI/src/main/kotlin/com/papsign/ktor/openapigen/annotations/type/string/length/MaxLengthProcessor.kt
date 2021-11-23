package com.papsign.ktor.openapigen.annotations.type.string.length

import com.papsign.ktor.openapigen.model.schema.SchemaModel

object MaxLengthProcessor : LengthConstraintProcessor<MaxLength>() {
    override fun process(model: SchemaModel.SchemaModelLitteral<*>, annotation: MaxLength): SchemaModel.SchemaModelLitteral<*> {
        @Suppress("UNCHECKED_CAST")
        return (model as SchemaModel.SchemaModelLitteral<Any?>).apply {
            maxLength = annotation.value
        }
    }

    override fun getConstraint(annotation: MaxLength): LengthConstraint {
        return LengthConstraint(max = annotation.value, errorMessage = annotation.errorMessage)
    }
}