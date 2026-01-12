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
import com.example.singleactivityapp.data.User

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
    Column(modifier = Modifier.fillMaxSize()) {
        AppToolBar("Male Only", R.drawable.female1)
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