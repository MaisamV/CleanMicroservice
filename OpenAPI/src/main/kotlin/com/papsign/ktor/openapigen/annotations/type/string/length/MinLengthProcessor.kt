package com.papsign.ktor.openapigen.annotations.type.string.length

import com.papsign.ktor.openapigen.model.schema.SchemaModel

object MinLengthProcessor : LengthConstraintProcessor<MinLength>() {
    override fun process(model: SchemaModel.SchemaModelLitteral<*>, annotation: MinLength): SchemaModel.SchemaModelLitteral<*> {
        @Suppress("UNCHECKED_CAST")
        return (model as SchemaModel.SchemaModelLitteral<Any?>).apply {
            minLength = annotation.value
        }
    }

    override fun getConstraint(annotation: MinLength): LengthConstraint {
        return LengthConstraint(min = annotation.value, errorMessage = annotation.errorMessage)
    }
}