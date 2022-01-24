package com.mvs.service.util

import com.mvs.auth.RoleType
import com.mvs.auth.UserClaim
import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*
import ir.sabaolgoo.ICommand
import ir.sabaolgoo.ICommandFactory

inline fun <reified TCommand: ICommand> Route.ktorService(factory: ICommandFactory<TCommand>,
                                                          service: Route.(ICommandFactory<TCommand>) -> Unit) {
    service.invoke(this, factory)
}
inline fun <reified TCommand: ICommand> Route.authHead(
    factory: ICommandFactory<TCommand>,
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(TCommand, UserClaim) -> Unit
): Route {
    return headSafe(getAuthBody(factory, body))
}

inline fun <reified TCommand: ICommand> Route.authOptions(
    factory: ICommandFactory<TCommand>,
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(TCommand, UserClaim) -> Unit
): Route {
    return optionsSafe(getAuthBody(factory, body))
}

inline fun <reified TCommand: ICommand> Route.authGet(
    factory: ICommandFactory<TCommand>,
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(TCommand, UserClaim) -> Unit
): Route {
    return getSafe(getAuthBody(factory, body))
}

inline fun <reified TCommand: ICommand> Route.authPost(
    factory: ICommandFactory<TCommand>,
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(TCommand, UserClaim) -> Unit
): Route {
    return postSafe(getAuthBody(factory, body))
}

inline fun <reified TCommand: ICommand> Route.authPut(
    factory: ICommandFactory<TCommand>,
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(TCommand, UserClaim) -> Unit
): Route {
    return putSafe(getAuthBody(factory, body))
}

inline fun <reified TCommand: ICommand> Route.authDelete(
    factory: ICommandFactory<TCommand>,
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(TCommand, UserClaim) -> Unit
): Route {
    return deleteSafe(getAuthBody(factory, body))
}

inline fun <reified TCommand: ICommand> Route.authPatch(
    factory: ICommandFactory<TCommand>,
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(TCommand, UserClaim) -> Unit
): Route {
    return patchSafe(getAuthBody(factory, body))
}

inline fun <TCommand: ICommand> getAuthBody(
    factory: ICommandFactory<TCommand>,
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(TCommand, UserClaim) -> Unit
): (suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit) {
    val newBody: (suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit) = {
        withClaim { currentUserClaim ->
            body.invoke(this, factory.create(currentUserClaim), currentUserClaim)
        }
    }
    return newBody
}

inline fun PipelineContext<Unit, ApplicationCall>.withClaim(body: (UserClaim) -> Unit) {
    val userRoles = arrayListOf<RoleType>()
    val headers = call.request.headers
    val currentUserId = headers["USER-ID"]?.toLong()
    val targetUserId = headers["TARGET-USER-ID"]?.toLong() ?: currentUserId
    headers["USER-ROLE"]?.let {
        userRoles.add(RoleType.valueOf(it))
    }
    userRoles.add(RoleType.ANONYMOUS)
    body.invoke(UserClaim(currentUserId, targetUserId, userRoles))
}