package com.mvs.exception

class PermissionDeniedException: BaseException(ExceptionCode.PERMISSION_DENIED.code, "Permission denied.", "دسترسی مجاز نیست.")