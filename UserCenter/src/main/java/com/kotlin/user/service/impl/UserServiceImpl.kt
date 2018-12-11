package com.kotlin.user.service.impl

import android.util.Log
import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.base.rx.BaseException
import com.kotlin.user.data.repository.UserRepository
import com.kotlin.user.service.UserService
import rx.Observable
import rx.functions.Func1

class UserServiceImpl: UserService {

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {

        val repository = UserRepository()

        return repository.register(mobile, pwd, verifyCode)
                .flatMap(object: Func1<BaseResp<String>, Observable<Boolean>>{
                    override fun call(t: BaseResp<String>): Observable<Boolean> {
                        Log.d("mock-result","请求结果")
                        Log.d("mock-result","result: "+t.status+", "+t.message+", "+t.data)
                        if (t.status != 0) {
                            return Observable.error(BaseException(t.status, t.message))
                        }

                        return Observable.just(true)
                    }
                })
    }
}