package t.project.delfood.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import t.project.delfood.dto.Dish
import t.project.delfood.model.FeedModel
import t.project.delfood.model.FeedModelState
import t.project.delfood.repository.DishRepository
import javax.inject.Inject



@HiltViewModel
@ExperimentalCoroutinesApi
class DishViewModel @Inject constructor(
    private val repository: DishRepository,
) : ViewModel() {

    private val _state = MutableLiveData(FeedModelState())
    val state: LiveData<FeedModelState>
        get() = _state
    val data: LiveData<FeedModel> = repository.data.map {
        FeedModel(dishes = it, empty = it.isEmpty())
    }.asLiveData(Dispatchers.Default)


    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch {
            _state.postValue(FeedModelState(loading = true))
            try {
                repository.getAll()
                _state.postValue(FeedModelState())
            } catch (e: Exception) {
                _state.value = FeedModelState(error = true)
            }
        }

    }
}