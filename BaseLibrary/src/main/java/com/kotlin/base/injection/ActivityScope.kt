package com.kotlin.base.injection

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME
import javax.inject.Scope

/**
 * Activity级别 作用域
 */
@Scope
@Retention(RUNTIME)
annotation class ActivityScope