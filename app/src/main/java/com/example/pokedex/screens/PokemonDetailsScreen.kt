package com.example.pokedex.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.pokedex.R
import com.example.pokedex.components.SkeletonAnimation
import com.example.pokedex.ui.theme.COLOR_BACKGROUND
import com.example.pokedex.ui.theme.COLOR_FONT
import com.example.pokedex.utils.dominantColor

@Composable
fun PokemonDetailsScreen(
    navController: NavController,
    pokemonId: String,
    innerPadding: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(COLOR_BACKGROUND)
    ) {
        val context = LocalContext.current
        val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$pokemonId.png"
        var isLoading by remember { mutableStateOf(true) }
        var backgroundColor by remember { mutableStateOf(Color.LightGray) }

        LaunchedEffect(imageUrl) {
            backgroundColor = dominantColor(context, imageUrl)
            isLoading = false
        }

        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .size(32.dp)
                .absoluteOffset(x = 32.dp, y = 40.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "Voltar",
                tint = COLOR_FONT
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp, bottom = 40.dp, start = 32.dp, end = 32.dp)
        ) {

            Header(id = pokemonId, name = "Fakename")


            PokemonContainer(
                imageUrl = imageUrl,
                isLoading = isLoading,
                backgroundColor = backgroundColor
            )

            Spacer(modifier = Modifier.height(32.dp))

            Tabs()
        }
    }
}

@Composable
fun Header(id: String, name: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = name,
                color = COLOR_FONT,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            val formattedId = id.padStart(3, '0')
            Text(
                text = formattedId,
                color = COLOR_FONT,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
fun PokemonContainer(
    imageUrl: String,
    isLoading: Boolean,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxHeight(0.60f)
            .fillMaxWidth()
            .padding(top = 32.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            SkeletonAnimation(Modifier.fillMaxSize())
        } else {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Pokemon",
                modifier = Modifier.fillMaxSize(0.9f)
            )
        }
    }
}

@Composable
fun Tabs() {
    val tabs = listOf("Detalhes", "Tipos", "Evoluções")
    var selectedTab by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(COLOR_BACKGROUND)
    ) {
        TabRow(
            modifier = Modifier
                .background(COLOR_BACKGROUND),
            selectedTabIndex = selectedTab,
            indicator = {},
            divider = {}
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    modifier = Modifier
                        .background(COLOR_BACKGROUND)
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .padding(0.dp),
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = title,
                            color = if (selectedTab == index) COLOR_FONT else Color.Gray,
                            textAlign = TextAlign.Start,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                )
            }
        }

        when (selectedTab) {
            0 -> Text("Detalhes dos vagabundos", Modifier.padding(16.dp))
            1 -> Text("Tipos dos vagabundos", Modifier.padding(16.dp))
            2 -> Text("Linha de evolução dos cachorros", Modifier.padding(16.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PokemonDetailsPreview() {
    val navController = rememberNavController()
    PokemonDetailsScreen(
        navController = navController,
        pokemonId = "1",
        innerPadding = PaddingValues()
    )
}
