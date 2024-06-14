package com.mind.small.model

/**
 * create by Rui on 2024-06-14
 * desc:
 */
data class ModuleConfig(
    var modules: List<String>?,
    var env: String?,
    var applicationId: String?,
    var versionCode: String?,
    var versionName: String?,
    var logoUrl: String,
    var appName: String,
    var h5Url: String
)