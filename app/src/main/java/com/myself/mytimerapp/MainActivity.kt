package com.myself.mytimerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.myself.mytimerapp.navigation.NavManager
import com.myself.mytimerapp.ui.theme.MyTimerAppTheme
import com.myself.mytimerapp.viewModels.CronometroViewModel
import com.myself.mytimerapp.viewModels.CronosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cronosVM : CronosViewModel by viewModels()
        val cronometroVM : CronometroViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            MyTimerAppTheme {
                NavManager(cronometroVM, cronosVM)
            }
        }
    }
}

