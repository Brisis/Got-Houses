package com.example.gothouses.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gothouses.models.House
import com.example.gothouses.navigation.Route

@Composable
fun  HomePage(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
){
    val ctx = LocalContext.current

    val progress = remember {
        mutableStateOf(true)
    }

    val houses = remember {
        mutableStateOf(listOf<House>())
    }

    getHouses(viewModel = viewModel, progress = progress, houses = houses)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {
        Box{
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "GOT Houses",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }
        Spacer(modifier = Modifier.padding(10.dp))
        if (progress.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
        else {
            HouseList(houses = viewModel.houseListResponse, navController)
        }

    }
}


fun getHouses(
    viewModel: MainViewModel,
    houses: MutableState<List<House>>,
    progress: MutableState<Boolean>,
){
    houses.value = viewModel.getHouseList()
   if (houses.value.isNotEmpty()){
       progress.value = false
   }
}



@Composable
fun HouseList(
    houses: List<House>,
    navController: NavHostController,
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    )    {
        items(houses) { house ->
            HouseRow(house, navController)
        }
    }
}

@Composable
fun HouseRow(
    house: House,
    navController: NavHostController
){
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(Route.HouseDetail)
            },
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(5.dp))
    ){
        Row{
            Column( modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.CenterVertically)) {
                Text(text = house.name, style = typography.h6)
                Spacer(modifier = Modifier.padding(bottom = 5.dp))
                Text(text = "VIEW DETAIL", style = typography.caption)
            }
        }
    }

}
