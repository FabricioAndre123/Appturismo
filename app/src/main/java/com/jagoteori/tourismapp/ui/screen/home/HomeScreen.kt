package com.jagoteori.tourismapp.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jagoteori.tourismapp.R
import com.jagoteori.tourismapp.di.Injection
import com.jagoteori.tourismapp.model.Tourism
import com.jagoteori.tourismapp.ui.ViewModelFactory
import com.jagoteori.tourismapp.ui.common.UiState
import com.jagoteori.tourismapp.ui.components.PopularPlaceCard
import com.jagoteori.tourismapp.ui.components.RecommendationCard
import com.jagoteori.tourismapp.ui.theme.BlackColor500
import com.jagoteori.tourismapp.ui.theme.GreyColor300
import com.jagoteori.tourismapp.ui.theme.poppinsFamily
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Int) -> Unit,
    navigateToAboutMe: () -> Unit,
    onLogout: () -> Unit
) {
    // Inicia el contador de tiempo cuando se crea la pantalla
    val startTime = remember { System.currentTimeMillis() }

    // Lógica para actualizar el campo DateExit y el tiempo de uso cuando se cierra sesión
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    val currentUser = auth.currentUser
    val currentDate = Timestamp.now()   

    DisposableEffect(Unit) {
        onDispose {
            val endTime = System.currentTimeMillis()
            val usageTime = (endTime - startTime) / 1000 // Convertir a segundos

            currentUser?.let { user ->
                val userDoc = db.collection("users").document(user.uid)

                userDoc.get().addOnSuccessListener { document ->
                    if (document != null) {
                        // Actualizar el tiempo de uso
                        val currentUsageTime = document.getLong("usageTime") ?: 0
                        userDoc.update("usageTime", currentUsageTime + usageTime)
                    } else {
                        userDoc.set(mapOf("usageTime" to usageTime))
                    }
                }

                // Actualizar el campo DateExit
                userDoc.update("DateExit", currentDate)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HomeHeader(
            navigateToAboutMe = navigateToAboutMe,
            onLogout = {
                onLogout()
                // También actualizar el campo DateExit al cerrar sesión
                val currentDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
                currentUser?.let { user ->
                    val userDoc = db.collection("users").document(user.uid)
                    userDoc.update("DateExit", currentDate)
                }
            }
        )
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getAllRewards()
                }
                is UiState.Success -> {
                    HomeContent(
                        tourismList = uiState.data,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail,
                    )
                }
                is UiState.Error -> {}
            }
        }
    }
}

@Composable
fun HomeContent(tourismList: List<Tourism>, modifier: Modifier, navigateToDetail: (Int) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(horizontal = 24.dp),
        content = {
            items(tourismList.size) { index ->
                PopularPlaceCard(
                    tourism = tourismList[index],
                    modifier = modifier,
                    onClickCard = { navigateToDetail(tourismList[index].id) }
                )
            }
        })

    Text(
        text = "Recomendaciones",
        fontSize = 18.sp,
        color = BlackColor500, fontFamily = poppinsFamily,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier.padding(start = 24.dp, top = 30.dp, end = 24.dp, bottom = 16.dp)
    )
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 30.dp),
        modifier = modifier
            .height(500.dp)
            .nestedScroll(
                object : NestedScrollConnection {
                    // Implement callbacks here
                }),
        content = {
            items(tourismList.size) { index ->
                RecommendationCard(
                    modifier = modifier,
                    tourism = tourismList[index],
                    onClickCard = { navigateToDetail(tourismList[index].id) }
                )
            }
        })
}

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    navigateToAboutMe: () -> Unit,
    onLogout: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 30.dp, horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "ECUAEXPLORER",
                lineHeight = 36.sp,
                color = BlackColor500,
                fontFamily = poppinsFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Selecciona el destino que más te llame la atención y te ofrezca la experiencia que buscas",
                fontSize = 16.sp,
                color = GreyColor300,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Light,
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = navigateToAboutMe) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "About Me",
                    tint = MaterialTheme.colors.primary
                )
            }
            IconButton(onClick = onLogout) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Logout",
                    tint = MaterialTheme.colors.primary
                )
            }
        }
    }
}
