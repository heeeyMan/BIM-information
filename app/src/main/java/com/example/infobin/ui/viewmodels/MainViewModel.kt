package com.example.infobin.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infobin.datamodels.*
import com.example.infobin.models.IMainModel
import com.example.infobin.router.IMainRouter
import kotlinx.coroutines.launch
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException

class MainViewModel(
    private val model: IMainModel,
    private val router: IMainRouter
) : ViewModel(), IMainView {
    var dataList = MutableLiveData<List<Item>>()
    var binTextState = MutableLiveData<BinTextState>()
    var queryErrorTextState = MutableLiveData<RequestErrorState>()

    override fun showHistory() {
        router.navigationToHistory()
    }

    override fun requestBinInfo(request: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val isConnection = checkConnection()
                    if (isConnection) {
                        val response = model.getBinData(request)
                        withContext(Dispatchers.Main) {
                            dataList.postValue(model.getData(response))
                            queryErrorTextState.postValue(RequestErrorState.CORRECT)
                        }
                    } else {
                        throw ConnectException("No connection!")
                    }
                }
            } catch (t: ConnectException) {
                queryErrorTextState.postValue(RequestErrorState.NO_INTERNET)
            } catch (ex: HttpException) {
                when (ex.code()) {
                    400 -> queryErrorTextState.postValue(RequestErrorState.CLIENT_ERROR)
                    404 -> queryErrorTextState.postValue(RequestErrorState.NOT_FOUND)
                    in 500..511 -> queryErrorTextState.postValue(RequestErrorState.SERVER_ERROR)
                    else -> queryErrorTextState.postValue(RequestErrorState.UNKNOWN_ERROR)
                }
            } catch (error: Throwable) {
                queryErrorTextState.postValue(RequestErrorState.UNKNOWN_ERROR)
            }
        }
    }

    override fun itemClicked(clickedItem: String, type: TypesItem) {
        router.openLink(clickedItem, type)
    }

    override fun addHistoryItem(query: String) {
        viewModelScope.launch {
            try {
                model.addHistoryItem(query)
            } catch (error: Throwable) {
                println("ERROR addHistoryItem $error")
            }
        }
    }

    override fun checkConnection(): Boolean {
        return model.isConnection()
    }

    fun handleEditText(text: String) {
        when {
            text.isEmpty() -> binTextState.postValue(BinTextState.EMPTY)
            else -> binTextState.postValue(BinTextState.NO_EMPTY)
        }
    }
}