package com.example.singleactivityapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.singleactivityapp.ui.screens.BaseViewModel
import com.example.singleactivityapp.ui.screens.UserViewModel
import com.example.singleactivityapp.ui.theme.SingleActivityAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SingleActivityAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = hiltViewModel()
) {
    val clickedEvent = remember { mutableIntStateOf(2) }
    val result = viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadData()
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(20.dp)) {
        Text(
            text = "Increase ${clickedEvent.intValue}",
            modifier = modifier.clickable {
                clickedEvent.intValue++
                viewModel.handleIntent(UserViewModel.UserIntent.IncreaseCount(clickedEvent.intValue))
            }
        )
        val list = result.value.userList
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            items(list?.results ?: listOf()) { user ->
                Text(
                    text = " ${user.user?.username} \n ${user.user?.gender} \n ${user.user?.phone}",
                    modifier = modifier
                )
            }
        }


    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SingleActivityAppTheme {
        Greeting("Android")
    }
}