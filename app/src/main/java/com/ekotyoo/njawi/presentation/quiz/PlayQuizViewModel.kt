package com.ekotyoo.njawi.presentation.quiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import io.reactivex.rxjava3.schedulers.Timed
import java.util.*
import java.util.concurrent.TimeUnit

class PlayQuizViewModel : ViewModel () {

    private var _progress = MutableLiveData<Float>()
    private var _currentAnswer = MutableLiveData<List<String>>()
    val progress: LiveData<Float> = _progress
    val currentAnswer: LiveData<List<String>> = _currentAnswer

    var _subscriptions: CompositeDisposable = CompositeDisposable()

    init {
        startTimer()
    }


    private fun startTimer() {
        val disposable = Observable.interval(10L, TimeUnit.MILLISECONDS)
            .timeInterval()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                _progress.value = it.value() / 3000f * 100
                if (_progress.value!! > 100) {
                    stopTimer()
                }
            }.doOnDispose {
                Log.d("onDispose", "Done!")
            }
            .subscribe()
        _subscriptions.add(disposable)
    }

    private fun stopTimer() {
        _progress.value = 0f
        _subscriptions.dispose()
    }

    fun addAnswer(answer: String) {
        val tempList : MutableList<String> = _currentAnswer.value?.toMutableList() ?: mutableListOf<String>()
        tempList.add(answer)
        _currentAnswer.value = tempList
    }
}