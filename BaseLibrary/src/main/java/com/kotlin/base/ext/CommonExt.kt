package com.kotlin.base.ext

import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.base.utils.AndroidSchedulers
import rx.Observable
import rx.schedulers.Schedulers

fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>) {
    this.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)
}