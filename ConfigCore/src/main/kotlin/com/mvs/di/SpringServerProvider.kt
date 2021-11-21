package com.mvs.di

import com.mvs.delivery.IDelivery
import com.mvs.service.SpringServer

object SpringServerProvider {
    fun provide(): IDelivery {
        val commandProvider = CoreCommandProvider()
        return SpringServer(commandProvider.provideHealth(), commandProvider.providePing())
    }
}