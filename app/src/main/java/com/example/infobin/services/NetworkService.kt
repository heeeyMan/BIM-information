package  com.example.infobin.services

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkService(private val appContext: Context) : INetworkService {
    override fun checkNetworkConnection(): Boolean {
        val connectManager =
            appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectManager.activeNetwork
        val networkCapabilities = connectManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}