package ir.sabaolgoo

import com.mvs.auth.UserClaim


interface ICommandFactory<C: ICommand> {
    fun create(userClaim: UserClaim): C
}