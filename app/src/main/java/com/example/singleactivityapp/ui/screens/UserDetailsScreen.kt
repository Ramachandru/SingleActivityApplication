package com.example.singleactivityapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.singleactivityapp.R
import com.example.singleactivityapp.data.newtwork.User

@Composable
fun UserDetails(user: User) {
    val userDetail = user.user
    Column(modifier = Modifier.fillMaxSize()) {
        AppToolBar("User Details", R.drawable.male1)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            userDetail?.let {
                Text(text = it.username, fontSize = 20.sp, fontStyle = FontStyle.Normal)
                Text(text = it.email, fontSize = 16.sp, fontStyle = FontStyle.Italic)
                Text(text = it.phone, fontSize = 16.sp, fontStyle = FontStyle.Italic)
            }
        }
    }
}