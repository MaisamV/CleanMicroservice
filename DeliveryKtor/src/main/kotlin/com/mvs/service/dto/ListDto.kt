package com.mvs.service.dto

class ListDto<T> (
    var items: List<T>,
    var total_count: Long? = null
)