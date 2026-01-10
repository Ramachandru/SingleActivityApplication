package com.example.singleactivityapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.singleactivityapp.data.newtwork.User

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MaleListScreen(
    maleViewModel: UserViewModel = hiltViewModel(),
    navigateToDetails: (User) -> Unit,
) {
    val maleListState = maleViewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        maleViewModel.getGenderListUser("male")
    }

    val userList = maleListState.value.genderList
    ShowingListOfUser(
        userList = userList,
        navigationToDetails = navigateToDetails
    )
}