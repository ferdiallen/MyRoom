package com.allen.room

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDisplay(viewModel())
        }
    }
}


@Composable
fun MyDisplay(vm: DBViewModels) {
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    val context = LocalContext.current
    val responded by vm.collected.collectAsState(initial = emptyList())
    val scafState = SnackbarHostState()
    val scope = rememberCoroutineScope()
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.Black), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Room Database",
                    fontFamily = FontFamily.Serif,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp))
            TextField(
                value = firstname,
                onValueChange = { firstname = it },
                label = { Text(text = "First Name") },
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Green, backgroundColor = Color.White
                ), singleLine = true, maxLines = 1
            )
            Spacer(modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp))
            TextField(
                value = lastname,
                onValueChange = { lastname = it },
                label = { Text(text = "Last Name") },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Green, backgroundColor = Color.White
                ), singleLine = true, maxLines = 1
            )
            Spacer(modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp))
            Button(
                onClick = {
                    if (firstname.isNotEmpty() && lastname.isNotEmpty()) {
                        vm.tambahUsers(DB(0, first = firstname, lasts = lastname))
                        Toast.makeText(
                            context,
                            "Data Berhasil dimasukkan",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Salah satu Field Masih kosong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }, modifier = Modifier
                    .fillMaxWidth(0.5F)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Color.Green)
            ) {
                Text(text = "Click to save data", color = Color.White)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(bottom = 12.dp, end = 8.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                        scafState.showSnackbar("Data available : ${responded.size}")
                        }
                    },
                    backgroundColor = Color.Green,
                    modifier = Modifier.align(End)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Icons for floating button"
                    )
                }
                SnackbarHost(hostState = scafState)
            }

        }
    }

}

