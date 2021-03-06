package com.kotlin.user.ui.activity

import android.view.View
import com.kotlin.base.ext.enable
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.RegisterPresenter
import com.kotlin.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 * 注册界面
 */
class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initView() {
        mRegisterBtn.enable(mMobileEt, {isBtnEnable()})
        mRegisterBtn.enable(mVerifyCodeEt, {isBtnEnable()})
        mRegisterBtn.enable(mPwdEt, {isBtnEnable()})
        mRegisterBtn.enable(mPwdConfirmEt, {isBtnEnable()})

        //验证码默认 123456
        mVerifyCodeBtn.onClick(this)
        mRegisterBtn.onClick(this)
    }

    override fun initData() {
    }

    /**
     * Dagger注册
     */
    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证码成功") //默认123456
            }

            R.id.mRegisterBtn -> {
                mPresenter.register(mMobileEt.text.toString(), mVerifyCodeEt.text.toString(), mPwdEt.text.toString())
            }
        }
    }

    /**
     * 判断注册按钮是否可用
     */
    private fun isBtnEnable():Boolean {
        return mMobileEt.text.isNullOrEmpty().not()
            && mVerifyCodeEt.text.isNullOrEmpty().not()
            && mPwdEt.text.isNullOrEmpty().not()
            && mPwdConfirmEt.text.isNullOrEmpty().not()
    }

    /**
     * 注册结果回调
     */
    override fun onRegisterResult(result: String) {
        toast(result)
        finish()
    }

}
