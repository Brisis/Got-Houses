package com.example.gothouses.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gothouses.models.House
import com.example.gothouses.navigation.Route

@Composable
fun HouseDetail(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
){
    val ctx = LocalContext.current

    val progress = remember {
        mutableStateOf(true)
    }

    val house = remember {
        mutableStateOf(House(""))
    }


    getHouse(viewModel = viewModel, progress = progress, house = house)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth()
                .clickable { },
            elevation = 2.dp,
            backgroundColor = Color.White,
            shape = RoundedCornerShape(corner = CornerSize(5.dp))
        ){
            Row{
                Column( modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)) {
                    Text(text = house.value.name, style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.padding(bottom = 5.dp))
                    Text(text = "VIEW DETAIL", style = MaterialTheme.typography.caption)
                }
            }
        }

        Button(onClick = {
            navController.navigate(Route.HomePage)
        }) {
            Text(text = "Get Back")
        }
    }

}

fun getHouse(
    viewModel: MainViewModel,
    house: MutableState<House>,
    progress: MutableState<Boolean>,
){
    house.value = viewModel.getHouse()
    if (house.value.name != ""){
        progress.value = false
    }
}