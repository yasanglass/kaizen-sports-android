package dev.yasan.kaizen.presentation.ui.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yasan.kaizen.domain.usecase.GetSportsUseCase
import dev.yasan.kaizen.model.Sport
import dev.yasan.kit.core.DispatcherProvider
import dev.yasan.kit.core.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for [HomeScreen].
 *
 * @see loadSports
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val getSportsUseCase: GetSportsUseCase
) : ViewModel() {

    private var _sports = MutableLiveData<Resource<List<Sport>>>(Resource.Initial())
    val sports: LiveData<Resource<List<Sport>>> get() = _sports

    fun loadSports() {
        viewModelScope.launch(dispatchers.io) {
            _sports.postValue(Resource.Loading())
            val data = getSportsUseCase()
            if (data is Resource.Error) {
                // Add fake delay for UX purposes
                delay(1000)
            }
            _sports.postValue(data)
        }
    }

}