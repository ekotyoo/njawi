package com.ekotyoo.njawi.presentation.quiz

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.ekotyoo.njawi.common.Constants
import com.ekotyoo.njawi.domain.models.Materi
import com.ekotyoo.njawi.domain.models.Quiz
import com.ekotyoo.njawi.domain.models.Response
import com.ekotyoo.njawi.domain.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class PlayQuizViewModel @Inject constructor(
    private val repository: QuizRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel () {

    private var _quizState = mutableStateOf<Response<Quiz>>(Response.Loading)
    private var _progress = MutableLiveData<Float>()
    private var _question = MutableLiveData<String>()
    private var _currentAnswer = MutableLiveData<List<String>>()
    private var _isDone = MutableLiveData<Boolean>()
    private var _isCorrect = MutableLiveData<Boolean>()
    private var _currentIndex = MutableLiveData<Int>()
    private var _shuffledWords = MutableLiveData<List<String>>()
    private var _correctWords = MutableLiveData<List<String>>()
    private var _correctQuestions = MutableLiveData<Int>()
    private var _totalScore = MutableLiveData<Int>()
    private var _state = mutableStateOf(PlayQuizScreenState())
    private var _isTimesUp = mutableStateOf<Boolean>(false)


    val quizState: State<Response<Quiz>> = _quizState
    val progress: LiveData<Float> = _progress
    val question: LiveData<String> = _question
    val currentAnswer: LiveData<List<String>> = _currentAnswer
    val currentIndex: LiveData<Int> = _currentIndex
    val isDone: LiveData<Boolean> = _isDone
    val isCorrect: LiveData<Boolean> = _isCorrect
    val shuffledWords: LiveData<List<String>> = _shuffledWords
    val correctWords: LiveData<List<String>> = _correctWords
    val correctQuestions: LiveData<Int> = _correctQuestions
    val totalScore: LiveData<Int> = _totalScore
    var state: State<PlayQuizScreenState> = _state
    var isTimesUp: State<Boolean> = _isTimesUp

    private var _subscriptions: CompositeDisposable = CompositeDisposable()
    private var disposable = Observable
        .interval(0, 10, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.io())
        .takeWhile {
            (it < 1500) && !isCorrect.value!!
        }
        .observeOn(AndroidSchedulers.mainThread())

    var quiz: Quiz = Constants.getQuiz()

    init {
        savedStateHandle.get<String>("id")?.let {
            getQuiz(it)
        }
    }

    private fun getQuiz(id: String) {
        viewModelScope.launch {
            repository.getQuizById(id).collect { response ->
                _quizState.value = response
                when(val quizResponse = quizState.value) {
                    is Response.Loading -> {
                        _state.value = PlayQuizScreenState(isLoading = true)
                    }
                    is Response.Success -> {
                        PlayQuizScreenState(quiz = quizResponse.data)
                        quiz = quizResponse.data
                        startGame()
                    }
                    is Response.Error -> {
                        _state.value = PlayQuizScreenState(error = "Something went wrong")
                    }
                }
            }
        }
    }

    private fun startGame() {
        _totalScore.value = 0
        _correctQuestions.value = 0
        _currentIndex.value = 0
        _isCorrect.value = false
        _isDone.value = false
        _question.value = quiz.questions!![currentIndex.value!!].targetSentence
        _correctWords.value = quiz.questions!![currentIndex.value!!].baseSentence.split(" ").toMutableList()
        val tempWords: MutableList<String> = correctWords.value!!.toMutableList()
        shuffle(tempWords)
        _shuffledWords.value = tempWords
        startTimer()
    }

    fun restartGame() {
        _subscriptions.clear()
        _currentAnswer.value = mutableListOf()
        startGame()
    }

    fun nextQuestion() {
        _subscriptions.clear()
        if (isCorrect.value == true) {
            _correctQuestions.value = _correctQuestions.value!! + 1
            _totalScore.value = totalScore.value!! + (correctQuestions.value!! * 10000f / progress.value!!).toInt()
        }
        if (_currentIndex.value!! < quiz.questions!!.size - 1) {
            _currentIndex.value = _currentIndex.value!! + 1
            _isCorrect.value = false
            _isTimesUp.value = false
            _currentAnswer.value = mutableListOf()
            _correctWords.value = quiz.questions!![currentIndex.value!!].baseSentence.split(" ").toMutableList()
            val tempWords: MutableList<String> = correctWords.value!!.toMutableList()
            shuffle(tempWords)
            _shuffledWords.value = tempWords
            startTimer()
        } else {
            _isDone.value = true
        }
    }

    private fun startTimer() {
        _subscriptions.add(disposable.subscribeBy(
            onNext = {
                _progress.value = it / 1500f * 100
            },
            onError = {
                Log.d("onError", "OnError in Observable Time: $it")
            },
            onComplete = {
                stopTimer()
                checkResult()
                if (_currentIndex.value!! < quiz.questions!!.size - 1) {
                    showCorrectAnswerDialog()
                } else {
                    nextQuestion()
                }
            }),
        )
    }

    private fun showCorrectAnswerDialog() {
        _isTimesUp.value = true
    }


    private fun stopTimer() {
        _progress.value = 0f
    }

    private fun checkResult() {
        if (currentAnswer.value != null) {
            _isCorrect.value = isEqual(currentAnswer.value!!, correctWords.value!!)
        }
    }

    fun addAnswer(answer: String) {
        val tempList : MutableList<String> = _currentAnswer.value?.toMutableList() ?: mutableListOf()
        tempList.add(answer)
        _currentAnswer.value = tempList
        removeOption(answer)

        if (currentAnswer.value!!.size == correctWords.value!!.size) {
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
        val tempList : MutableList<String> = _shuffledWords.value!!.toMutableList()
        tempList.remove(option)
        _shuffledWords.value = tempList
    }

    private fun addOption(option: String) {
        val tempList : MutableList<String> = _shuffledWords.value!!.toMutableList()
        tempList.add(option)
        _shuffledWords.value = tempList
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

    override fun onCleared() {
        super.onCleared()
        _subscriptions.dispose()
    }
}