package com.allen.room

import android.os.Bundle
import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.allen.room.ui.theme.RoomTryTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var myVM:DBViewModels
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myVM = ViewModelProvider(this).get(DBViewModels::class.java)
        setContent {
                // A surface container using the 'background' color from the theme
                  MyDisplay()
        }
        val readed = myVM.readAllData.observe(this, Observer {
            it.forEach{ it2->
                Log.d("TAG","Nama awal : ${it2.first} dan Nama Akhir : ${it2.lasts} ")
            }

        })

    }
    @Composable
  fun MyDisplay(){
        var firstname by remember {mutableStateOf("")}
        var lastname by remember {mutableStateOf("")}
            MaterialTheme {
                val typo = MaterialTheme.typography
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(Color.Black)
                ) {
                    Spacer(modifier = Modifier.padding(0.dp,20.dp, 0.dp, 0.dp))
                    Text(text = "Room Database ",modifier = Modifier.padding(150.dp,0.dp,0.dp,0.dp),fontFamily = FontFamily.Serif,fontSize = 20.sp
                        ,color = Color.White
                    )
                    Spacer(modifier = Modifier.padding(0.dp,20.dp, 0.dp, 0.dp))
                    TextField(value = firstname
                    ,onValueChange = {firstname = it},
                        label = { Text(text = "First Name")},
                        modifier = Modifier.padding(80.dp,0.dp,0.dp,0.dp),colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.Green,backgroundColor = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.padding(0.dp,20.dp, 0.dp, 0.dp))
                    TextField(value = lastname
                        ,onValueChange = {lastname = it},
                        label = { Text(text = "Last Name")},
                        modifier = Modifier.padding(80.dp,0.dp,0.dp,0.dp),colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.Green,backgroundColor = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.padding(0.dp,20.dp, 0.dp, 0.dp))
                    Button(onClick = {
                        if(!firstname.isEmpty() && !lastname.isEmpty()){
                            myVM.tambahUsers(DB(0,firstname,lastname))
                            Toast.makeText(this@MainActivity, "Data Berhasil dimasukkan", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this@MainActivity, "Salah satu Field Masih kosong", Toast.LENGTH_SHORT).show()
                        }
                         },modifier = Modifier
                        .padding(130.dp, 0.dp, 130.dp, 0.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                        ,
                        colors = ButtonDefaults.buttonColors(Color.Green)
                    ) {
                        Text(text = "Click to save data",color = Color.White)
                    }
                }
            }

    }
}

