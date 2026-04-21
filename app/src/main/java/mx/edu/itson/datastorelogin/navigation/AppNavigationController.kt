package mx.edu.itson.datastorelogin.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.composableLambda
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.edu.itson.datastorelogin.screens.BolsaScreen
import mx.edu.itson.datastorelogin.screens.CapturarScreen
import mx.edu.itson.datastorelogin.screens.HomeScreen
import mx.edu.itson.datastorelogin.screens.LoginScreen
import mx.edu.itson.datastorelogin.viewModel.AuthViewModel
import mx.edu.itson.datastorelogin.viewModel.PokemonViewModel

sealed class Screen(val route: String){
        object Login: Screen("login")
        object Home: Screen("home")
        object Bolsa: Screen("bolsa")
        object Capturar: Screen("capturar")
}


@Composable
fun AppNavigation(
    authViewModel: AuthViewModel,
    pokemonViewModel: PokemonViewModel
) {
    val navController = rememberNavController()

    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()
    val userName by authViewModel.username.collectAsState()

    LaunchedEffect(
        isLoggedIn
    ) {
        if (isLoggedIn) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Login.route) { inclusive = true }
            }
        } else {
            navController.navigate(Screen.Login.route) {
                popUpTo(0) { inclusive = true }
            }
        }
    }

    NavHost(navController = navController, startDestination = if (isLoggedIn) Screen.Home.route else Screen.Login.route) {
        composable(Screen.Login.route) { LoginScreen(authViewModel) }
        composable(Screen.Home.route) {
            HomeScreen(
                userName,
                navController = navController,
                onLogoutClick = { authViewModel.logout() })
        }
        composable(Screen.Bolsa.route){
            BolsaScreen(navController, pokemonViewModel)
        }
        composable(Screen.Capturar.route) {
            CapturarScreen(navController, pokemonViewModel)
        }

    }
}