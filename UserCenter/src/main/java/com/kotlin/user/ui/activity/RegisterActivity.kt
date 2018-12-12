package com.kotlin.user.ui.activity

import android.os.Bundle
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.RegisterPresenter
import com.kotlin.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //验证码默认 123456
//        mRegisterBtn.setOnClickListener {
//            mPresenter.register(mMobileEt.text.toString(), mVerifyCodeEt.text.toString(), mPwdEt.text.toString())
//        }
//        mRegisterBtn.onClick(object: View.OnClickListener{
//            override fun onClick(v: View?) {
//                mPresenter.register(mMobileEt.text.toString(), mVerifyCodeEt.text.toString(), mPwdEt.text.toString())
//            }
//        })
        mRegisterBtn.onClick {
            mPresenter.register(mMobileEt.text.toString(), mVerifyCodeEt.text.toString(), mPwdEt.text.toString())
        }
    }

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

    override fun onRegisterResult(result: String) {
        toast(result)
    }
}
