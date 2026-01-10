package com.example.singleactivityapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
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
    ShowingListOfUser(
        userList = userList,
        navigationToDetails = navigateToDetails
    )
}