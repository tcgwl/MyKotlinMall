package com.kotlin.user.injection.component

import com.kotlin.base.injection.PerComponentScope
import com.kotlin.base.injection.component.ActivityComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.ui.activity.RegisterActivity
import dagger.Component

@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(UserModule::class))
interface UserComponent {
    fun inject(activity: RegisterActivity)
}