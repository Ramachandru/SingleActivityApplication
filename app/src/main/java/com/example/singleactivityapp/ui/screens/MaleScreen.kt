package com.example.singleactivityapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.singleactivityapp.data.newtwork.User

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MaleListScreen(
    femaleViewModel: UserViewModel = hiltViewModel(),
    navigateToDetails: (User) -> Unit,
) {
    val femaleListState = femaleViewModel.uiState
    val userList = femaleListState.value.userList
    ShowingListOfUser(
        userList = userList,
        navigationToDetails = navigateToDetails
    )
}