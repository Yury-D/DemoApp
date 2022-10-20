package com.dmitrienko.demoapp2.utils

import io.reactivex.Completable
import io.reactivex.CompletableTransformer
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Observables {
    companion object {
        fun <T> setSchedulers(): ObservableTransformer<T, T> {
            return ObservableTransformer { observable: Observable<T> ->
                observable
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
