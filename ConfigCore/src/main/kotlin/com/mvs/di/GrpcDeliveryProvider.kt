package com.mvs.di

import com.mvs.delivery.GrpcDelivery
import com.mvs.delivery.IDelivery

class GrpcDeliveryProvider {
    fun provide(): IDelivery {
        return GrpcDelivery(BaseCommandFactoryProvider())
    }
}