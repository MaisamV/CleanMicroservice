package com.mvs.health

import ir.sabaolgoo.ICommand

interface IPingCommand: ICommand {
    fun ping(): Boolean
}