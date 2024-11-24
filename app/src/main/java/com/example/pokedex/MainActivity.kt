package com.example.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.screens.PokemonDetailsScreen
import com.example.pokedex.ui.theme.PokedexTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexTheme {
                val navController = rememberNavController()
                Scaffold { innerPadding ->
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(navController, innerPadding)
                        }
                        composable("details/{pokemonId}") { backStackEntry ->
                            val pokemonId = backStackEntry.arguments?.getString("pokemonId")
                            pokemonId?.let {
                                PokemonDetailsScreen(navController = navController, pokemonId = it, innerPadding)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController, innerPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            val randomId = Random.nextInt(1, 151).toString()
            navController.navigate("details/$randomId")
        }) {
            Text(text = "Ir para detalhes")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController, PaddingValues())
}