package com.mvs.service.util

import com.mvs.auth.RoleType
import com.mvs.auth.UserClaim
import com.mvs.service.dto.BaseDto
import com.mvs.service.dto.BaseResponse
import com.papsign.ktor.openapigen.modules.RouteOpenAPIModule
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import com.papsign.ktor.openapigen.route.response.OpenAPIPipelineResponseContext
import ir.sabaolgoo.ICommand
import ir.sabaolgoo.ICommandFactory

inline fun <reified TCommand : ICommand, reified TParams : BaseDto, reified TResponse : Any?> NormalOpenAPIRoute.xAuthGet(
    factory: ICommandFactory<TCommand>,
    vararg modules: RouteOpenAPIModule,
    example: TResponse? = null,
    noinline body: suspend OpenAPIPipelineResponseContext<BaseResponse<TResponse>>.(TCommand, UserClaim, TParams) -> Unit
): NormalOpenAPIRoute {
    val newBody: (suspend OpenAPIPipelineResponseContext<BaseResponse<TResponse>>.(TParams) -> Unit) = { params ->
        withClaim(params) { currentUserClaim ->
            body.invoke(this, factory.create(currentUserClaim), currentUserClaim, params)
        }
    }
    return xGet(modules = modules, example, newBody)
}

inline fun <reified TCommand : ICommand, reified TParams : BaseDto, reified TResponse : Any?, reified TRequest : Any> NormalOpenAPIRoute.xAuthPost(
    factory: ICommandFactory<TCommand>,
    vararg modules: RouteOpenAPIModule,
    example: TResponse? = null,
    exampleRequest: TRequest? = null,
    noinline body: suspend OpenAPIPipelineResponseContext<BaseResponse<TResponse>>.(TCommand, UserClaim, TParams, TRequest) -> Unit
): NormalOpenAPIRoute {
    val newBody: (suspend OpenAPIPipelineResponseContext<BaseResponse<TResponse>>.(TParams, TRequest) -> Unit) =
        { params, req ->
            withClaim(params) { currentUserClaim ->
                body.invoke(this, factory.create(currentUserClaim), currentUserClaim, params, req)
            }
        }
    return xPost(modules = modules, example, exampleRequest, newBody)
}

inline fun <reified TCommand : ICommand, reified TParams : BaseDto, reified TResponse : Any?, reified TRequest: Any> NormalOpenAPIRoute.xAuthPut(
    factory: ICommandFactory<TCommand>,
    vararg modules: RouteOpenAPIModule,
    example: TResponse? = null,
    exampleRequest: TRequest? = null,
    noinline body: suspend OpenAPIPipelineResponseContext
    <BaseResponse<TResponse>>.(TCommand, UserClaim, TParams, TRequest) -> Unit
): NormalOpenAPIRoute {
    val newBody: (suspend OpenAPIPipelineResponseContext<BaseResponse<TResponse>>.(TParams, TRequest) -> Unit) = { params, req ->
        withClaim(params) { currentUserClaim ->
            body.invoke(this, factory.create(currentUserClaim), currentUserClaim, params, req)
        }
    }
    return xPut(modules = modules, example, exampleRequest, newBody)
}

inline fun <reified TCommand : ICommand, reified TParams : BaseDto, reified TResponse : Any?> NormalOpenAPIRoute.xAuthDelete(
    factory: ICommandFactory<TCommand>,
    vararg modules: RouteOpenAPIModule,
    example: TResponse? = null,
    noinline body: suspend OpenAPIPipelineResponseContext
    <BaseResponse<TResponse>>.(TCommand, UserClaim, TParams) -> Unit
): NormalOpenAPIRoute {
    val newBody: (suspend OpenAPIPipelineResponseContext<BaseResponse<TResponse>>.(TParams) -> Unit) = { params ->
        withClaim(params) { currentUserClaim ->
            body.invoke(this, factory.create(currentUserClaim), currentUserClaim, params)
        }
    }
    return xDelete(modules = modules, example, newBody)
}

inline fun <reified TCommand : ICommand, reified TParams : BaseDto, reified TResponse : Any?, reified TRequest: Any> NormalOpenAPIRoute.xAuthPatch(
    factory: ICommandFactory<TCommand>,
    vararg modules: RouteOpenAPIModule,
    example: TResponse? = null,
    exampleRequest: TRequest? = null,
    noinline body: suspend OpenAPIPipelineResponseContext
    <BaseResponse<TResponse>>.(TCommand, UserClaim, TParams, TRequest) -> Unit
): NormalOpenAPIRoute {
    val newBody: (suspend OpenAPIPipelineResponseContext<BaseResponse<TResponse>>.(TParams, TRequest) -> Unit) = { params, req ->
        withClaim(params) { currentUserClaim ->
            body.invoke(this, factory.create(currentUserClaim), currentUserClaim, params, req)
        }
    }
    return xPatch(modules = modules, example, exampleRequest, newBody)
}

inline fun <reified TCommand : ICommand, reified TParams : BaseDto, reified TResponse : Any?> NormalOpenAPIRoute.xAuthHead(
    factory: ICommandFactory<TCommand>,
    vararg modules: RouteOpenAPIModule,
    example: TResponse? = null,
    noinline body: suspend OpenAPIPipelineResponseContext
    <BaseResponse<TResponse>>.(TCommand, UserClaim, TParams) -> Unit
): NormalOpenAPIRoute {
    val newBody: (suspend OpenAPIPipelineResponseContext<BaseResponse<TResponse>>.(TParams) -> Unit) = { params ->
        withClaim(params) { currentUserClaim ->
            body.invoke(this, factory.create(currentUserClaim), currentUserClaim, params)
        }
    }
    return xHead(modules = modules, example, newBody)
}

inline fun withClaim(baseParams: BaseDto, body: (UserClaim) -> Unit) {
    val userRoles = arrayListOf<RoleType>()
    val currentUserId = baseParams.`USER-ID`
    val targetUserId = baseParams.`TARGET-USER-ID` ?: currentUserId
    baseParams.`USER-ROLE`?.let {
        userRoles.add(it)
    }
    userRoles.add(RoleType.ANONYMOUS)
    body.invoke(UserClaim(currentUserId, targetUserId, userRoles))
}