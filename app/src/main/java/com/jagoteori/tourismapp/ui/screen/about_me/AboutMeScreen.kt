package com.jagoteori.tourismapp.ui.screen.about_me

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jagoteori.tourismapp.R
import com.jagoteori.tourismapp.ui.components.CustomTopAppBar
import com.jagoteori.tourismapp.ui.theme.BlackColor500
import com.jagoteori.tourismapp.ui.theme.GreyColor300
import com.jagoteori.tourismapp.ui.theme.poppinsFamily
import kotlinx.coroutines.tasks.await

@Composable
fun AboutMeScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {
    val userName = remember { mutableStateOf("") }
    val userEmail = remember { mutableStateOf("") }

    LaunchedEffect(key1 = true) {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            userEmail.value = user.email ?: ""

            // Fetch user's name from Firestore
            val db = FirebaseFirestore.getInstance()
            try {
                val document = db.collection("users").document(user.uid).get().await()
                userName.value = document.getString("displayName") ?: ""
            } catch (e: Exception) {
                // Handle any errors here
                println("Error fetching user data: ${e.message}")
            }
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                elevation = 0.dp,
                icon = Icons.Filled.ArrowBack,
                iconDescription = stringResource(id = R.string.back_icon_info),
                onIconClick = navigateBack,
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 16.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(180.dp)
                    .width(150.dp)
                    .padding(bottom = 16.dp, top = 16.dp)
                    .clip(shape = CircleShape),
                contentDescription = stringResource(id = R.string.profile_image_info),
            )
            Text(
                "ECUAEXPLORER",
                fontFamily = poppinsFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = BlackColor500
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                userName.value,
                fontFamily = poppinsFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.W400,
                color = BlackColor500
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                userEmail.value,
                fontFamily = poppinsFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                color = GreyColor300
            )
        }
    }
}