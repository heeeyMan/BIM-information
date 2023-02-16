package com.example.gettinginfoonbin.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gettinginfoonbin.datamodels.BinTextState
import com.example.gettinginfoonbin.datamodels.ClearIconTextState
import com.example.gettinginfoonbin.datamodels.RequestErrorState
import com.example.gettinginfoonbin.datamodels.TypesItem
import com.example.gettinginfoonbin.models.IMainModel
import com.example.gettinginfoonbin.router.IMainRouter
import kotlinx.coroutines.launch
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MainViewModel(
    private val model: IMainModel,
    private val router: IMainRouter
) : ViewModel(), IMainView {
    var dataList = MutableLiveData<List<Item>>()
    var binTextState = MutableLiveData<BinTextState>()
    var queryErrorTextState = MutableLiveData<RequestErrorState>()
    var clearIconState = MutableLiveData<ClearIconTextState>()
    override fun showHistory() {
        router.navigationToHistory()
    }

    override fun requestBinInfo(request: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = model.getBinData(request)
                    withContext(Dispatchers.Main) {
                        dataListUpdate(model.getData(response))
                    }
                queryErrorTextState.postValue(RequestErrorState.CORRECT)
                }
            } catch (ex: HttpException) {
                when(ex.code()) {
                    404 -> queryErrorTextState.postValue(RequestErrorState.NOT_FOUND)
                    in 500..511 -> queryErrorTextState.postValue(RequestErrorState.SERVER_ERROR)
                    else -> queryErrorTextState.postValue(RequestErrorState.UNKNOWN_ERROR)
                }
            } catch (t: Throwable) {
                queryErrorTextState.postValue(RequestErrorState.UNKNOWN_ERROR)
            }
        }
    }

    override fun itemClicked(clickedItem: String, type: TypesItem) {
        router.openLink(clickedItem, type)
    }

    override fun addHistoryItem(query: String) {
        model.addHistoryItem(query)
    }

    override fun checkConnection(): Boolean {
        val isConnection = model.isConnection()
        if(!isConnection) {
            queryErrorTextState.postValue(RequestErrorState.NO_INTERNET)
        } else {
            queryErrorTextState.postValue( RequestErrorState.CORRECT)
        }
        return isConnection
    }

    private fun dataListUpdate(data: List<Item>) {
        dataList.postValue(data)
    }

    fun handleEditText(text: String) {
        when  {
            text.isEmpty() -> binTextState.postValue( BinTextState.EMPTY)
            isNoNumberChar(text.last()) -> binTextState.postValue( BinTextState.WRONG_SYMBOL)
            text.length > 9 -> binTextState.postValue( BinTextState.TOO_MANY_SYMBOLS)
            else -> binTextState.postValue( BinTextState.CORRECT)
        }
    }
    private fun isNoNumberChar(symbol: Char): Boolean {
        return symbol !in '0'..'9'
    }

}