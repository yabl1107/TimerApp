package com.myself.mytimerapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myself.mytimerapp.components.CronCard
import com.myself.mytimerapp.components.FloatButton
import com.myself.mytimerapp.components.MainTitle
import com.myself.mytimerapp.components.formatTiempo
import com.myself.mytimerapp.viewModels.CronosViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, cronosVM: CronosViewModel) {
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title="CRONO APP")},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatButton {
                navController.navigate("AddView")
            }
        }
    ){
        ContentHomeView(it, navController, cronosVM)
    }
}

@Composable
fun ContentHomeView(it: PaddingValues, navController: NavController, cronosVM: CronosViewModel){
    Column(
        modifier = Modifier.padding(it)
    ) {
        val cronosList by cronosVM.cronosList.collectAsState()
        LazyColumn{
            items(cronosList){item ->
                val delete = SwipeAction(
                    icon = rememberVectorPainter(Icons.Default.Delete),
                    background = androidx.compose.ui.graphics.Color.Red,
                    onSwipe = { cronosVM.deleteCrono(item) }
                )
                SwipeableActionsBox(endActions = listOf(delete), swipeThreshold = 270.dp) {
                    CronCard(item.title, formatTiempo(item.crono)) {
                        navController.navigate("EditView/${item.id}")
                    }
                }

            }
        }

    }
}
