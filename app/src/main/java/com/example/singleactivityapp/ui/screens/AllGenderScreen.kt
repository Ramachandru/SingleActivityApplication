package com.example.singleactivityapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.singleactivityapp.R
import com.example.singleactivityapp.data.newtwork.User
import com.example.singleactivityapp.ui.theme.Pink80
import com.example.singleactivityapp.ui.theme.Purple80
import com.example.singleactivityapp.ui.theme.SingleActivityAppTheme

@Composable
fun AllGenderList(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = hiltViewModel(),
    navigateToDetails: (User) -> Unit,
) {
    val clickedEvent = remember { mutableIntStateOf(2) }
    val result = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }
    Column(modifier = Modifier.fillMaxSize()) {
        AppToolBar("All Gender", R.drawable.male1)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "Increase ${clickedEvent.intValue}",
                modifier = modifier
                    .background(color = Purple80)
                    .clickable {
                        clickedEvent.intValue++
                        viewModel.handleIntent(UserViewModel.UserIntent.IncreaseCount(clickedEvent.intValue))
                    }
            )
            val list = result.value.genderList
            ShowingListOfUser(modifier = modifier, list, navigateToDetails)
        }
    }
}

@Composable
fun ShowingListOfUser(
    modifier: Modifier = Modifier,
    userList: List<User>?,
    navigationToDetails: (User) -> Unit
) {

    LazyColumn(
        modifier = Modifier.padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(userList ?: listOf()) { user ->
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(color = Pink80)
                    .clickable {
                        navigationToDetails(user)
                    }
                    .padding(20.dp)) {
                val userDta = user.user
                Text(
                    text = "UserName :  ${userDta?.username}",
                    modifier = Modifier.padding(2.dp)
                )
                Text(
                    text = "Gender : ${userDta?.gender} ",
                    modifier = Modifier.padding(2.dp)
                )
                Text(
                    text = "Phone : ${userDta?.phone}",
                    modifier = Modifier.padding(2.dp)
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SingleActivityAppTheme {
        AllGenderList {

        }
    }
}