package com.example.singleactivityapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.singleactivityapp.R
import com.example.singleactivityapp.data.newtwork.User

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun FemaleListScreen(
    femaleViewModel: UserViewModel = hiltViewModel(),
    navigateToDetails: (User) -> Unit,
) {

    LaunchedEffect(Unit) {
        femaleViewModel.getGenderListUser()
    }
    val femaleListState = femaleViewModel.uiState.collectAsState()

    val userList = femaleListState.value.genderList
    Column(modifier = Modifier.fillMaxSize()) {
        AppToolBar("Female Only", R.drawable.male1)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            ShowingListOfUser(
                userList = userList,
                navigationToDetails = navigateToDetails
            )
        }
    }
}