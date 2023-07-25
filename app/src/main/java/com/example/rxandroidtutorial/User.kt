package com.example.rxandroidtutorial

import io.reactivex.rxjava3.core.Observable

class User(var id: Int, var name: String){
    override fun toString(): String {
        return "User(id=$id, name='$name')"
    }
    fun getNameObservable() : Observable<String>{
        return Observable.just(name)
    }
    fun getNameDeferObservable() : Observable<String>{
        return Observable.defer{Observable.just(name)}
    }
}
