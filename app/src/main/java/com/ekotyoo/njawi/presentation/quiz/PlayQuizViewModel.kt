package com.ekotyoo.njawi.presentation.quiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class PlayQuizViewModel : ViewModel () {

    private var _progress = MutableLiveData<Float>()
    private var _currentAnswer = MutableLiveData<List<String>>()
    private var _isCorrect = MutableLiveData<Boolean>()
    private var _isPlaying = MutableLiveData<Boolean>()
    private var _words = MutableLiveData<List<String>>()
    val progress: LiveData<Float> = _progress
    val currentAnswer: LiveData<List<String>> = _currentAnswer
    val isCorrect: LiveData<Boolean> = _isCorrect
    val words: LiveData<List<String>> = _words

    private var _subscriptions: CompositeDisposable = CompositeDisposable()
    private var disposable = Observable
        .interval(1000, 10, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.io())
        .takeWhile {
            (it < 1500) && (isCorrect.value == false)
        }
        .observeOn(AndroidSchedulers.mainThread())

    private val correctSentence = "bapak sare kula adus"
    private val correctWords = correctSentence.split(" ").toMutableList()

    init {
        startGame()
    }

    private fun startGame() {
        _isCorrect.value = false
        val tempWords = correctWords.toMutableList()
        shuffle(tempWords)
        _words.value = tempWords
        startTimer()
    }

    fun restartGame() {
        _progress.value = 0f
        _currentAnswer.value = mutableListOf<String>()
        _isCorrect.value = false
        val tempWords = correctWords.toMutableList()
        shuffle(tempWords)
        _words.value = tempWords
        startTimer()
    }

    private fun startTimer() {
        _subscriptions.add(disposable.subscribeBy(
            onNext = this::updateProgress,
            onError = this::onError,
            onComplete = this::onComplete),
        )
    }


    private fun updateProgress(value: Long) {
        _progress.value = value / 1500f * 100
    }

    private fun onError(throwable: Throwable) {
        Log.d("onError", "OnError in Observable Time: $throwable")
    }

    private fun onComplete() {
        stopTimer()
        checkResult()
        Log.d("onComplete", "Done")
    }

    private fun stopTimer() {
        _progress.value = 0f
    }

    private fun checkResult() {
        if (currentAnswer.value != null)
        {
            _isCorrect.value = isEqual(currentAnswer.value!!, correctWords)
        }
    }

    fun addAnswer(answer: String) {
        val tempList : MutableList<String> = _currentAnswer.value?.toMutableList() ?: mutableListOf()
        tempList.add(answer)
        _currentAnswer.value = tempList
        removeOption(answer)

        if (currentAnswer.value!!.size == correctWords.size) {
            checkResult()
        }
    }

    fun removeAnswer(answer: String) {
        val tempList : MutableList<String> = _currentAnswer.value!!.toMutableList()
        tempList.remove(answer)
        _currentAnswer.value = tempList
        addOption(answer)
    }

    private fun removeOption(option: String) {
        val tempList : MutableList<String> = _words.value!!.toMutableList()
        tempList.remove(option)
        _words.value = tempList
    }

    private fun addOption(option: String) {
        val tempList : MutableList<String> = _words.value!!.toMutableList()
        tempList.add(option)
        _words.value = tempList
    }

    private fun <T> shuffle(list: MutableList<T>)
    {
        for (i in list.size - 1 downTo 1)
        {
            val j = Random.nextInt(i + 1)
            val temp = list[i]
            list[i] = list[j]
            list[j] = temp
        }
    }

    private fun<T> isEqual(first: List<T>, second: List<T>): Boolean {

        if (first.size != second.size) {
            return false
        }

        first.forEachIndexed { index, value -> if (second[index] != value) { return false} }
        return true
    }
}