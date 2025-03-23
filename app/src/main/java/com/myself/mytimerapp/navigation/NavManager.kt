package com.myself.mytimerapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.myself.mytimerapp.viewModels.CronometroViewModel
import com.myself.mytimerapp.viewModels.CronosViewModel
import com.myself.mytimerapp.views.EditView
import com.myself.mytimerapp.views.HomeView
import com.myself.mytimerapp.views.addView

@Composable
fun NavManager(cronometroVM: CronometroViewModel, cronosVM: CronosViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(navController, cronosVM)
        }
        composable("AddView"){
            addView(navController, cronometroVM, cronosVM)
        }
        composable("EditView/{id}", arguments = listOf(
            navArgument("id") { type = NavType.LongType })
        ){
            val id = it.arguments?.getLong("id") ?: 0
            EditView(navController, cronometroVM, cronosVM, id)
        }
    }
}