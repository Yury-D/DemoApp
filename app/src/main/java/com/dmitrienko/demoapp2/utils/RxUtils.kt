package com.dmitrienko.demoapp2.utils

import io.reactivex.Completable
import io.reactivex.CompletableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Singles {
    companion object {
        fun <T> setSchedulers(): SingleTransformer<T, T> {
            return SingleTransformer { upstream: Single<T> ->
                upstream
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}

class Completables {
    companion object {
        fun setSchedulers(): CompletableTransformer {
            return CompletableTransformer { upstream: Completable ->
                upstream
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}
