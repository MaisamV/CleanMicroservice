package com.mvs.di

import com.mvs.delivery.IDelivery
import com.mvs.service.KtorDelivery

class KtorDeliveryProvider {
    fun provide(): IDelivery {
        return KtorDelivery(BaseCommandFactoryProvider())
    }
}