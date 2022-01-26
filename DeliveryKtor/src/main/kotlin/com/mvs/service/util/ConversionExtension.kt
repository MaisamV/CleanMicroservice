package com.mvs.service.util

import com.mvs.service.dto.ListDto

fun <T> List<T>.toDto(): ListDto<T> = ListDto(this)
fun <T> List<T>.toDto(count: Long): ListDto<T> = ListDto(this, count)