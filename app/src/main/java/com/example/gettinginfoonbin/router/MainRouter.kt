package com.example.gettinginfoonbin.router

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.navigation.NavController
import com.example.gettinginfoonbin.datamodels.TypesItem
import com.example.gettinginfoonbin.ui.fragments.MainFragmentDirections

class MainRouter(
    private val navController: NavController,
    private val context: Context
): IMainRouter {
    override fun navigationToHistory() {
        navController.navigate(MainFragmentDirections.actionNavigationHomeToNavigationHistory())
    }

    override fun openLink(data: String, typeLink: TypesItem) {
        val intent: Intent
        when(typeLink) {
            TypesItem.LINK -> {
                val url = Uri.parse("http://$data")
                intent = Intent(Intent.ACTION_VIEW, url)
                context.startActivity(intent)
            }
            TypesItem.PHONE -> {
                val url = Uri.parse("tel:$data")
                intent = Intent(Intent.ACTION_VIEW, url)
            }
            TypesItem.MAP -> {
                val url = Uri.parse("http://maps.google.com/maps?daddr=$data")
                intent = Intent(Intent.ACTION_VIEW, url)
            }
        }
        context.startActivity(intent)
    }

}