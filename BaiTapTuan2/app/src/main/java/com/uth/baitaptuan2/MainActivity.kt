package com.uth.baitaptuan2

import android.R.attr.x
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaiTapTuan2()
        }
    }
}

@Composable
fun BaiTapTuan2() {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var showResult by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Tiêu đề
        Text(
            text = "THỰC HÀNH 01",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            //modifier = Modifier.offset(x = 0.dp, y = 0.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Khung xám
        Box(
            modifier = Modifier
                //.offset(x = 0.dp, y = 0.dp)
                .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(12.dp))
                .padding(40.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Dòng 1: Họ và tên + TextField
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Họ và tên",
                        modifier = Modifier.width(120.dp),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        singleLine = true,
                        modifier = Modifier.width(200.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        )
                    )
                }
                // Dòng 2: Tuổi + TextField
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Tuổi",
                        modifier = Modifier.width(120.dp),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    OutlinedTextField(
                        value = age,
                        onValueChange = { age = it },
                        singleLine = true,
                        modifier = Modifier.width(200.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Nút kiểm tra
        Button(
            onClick = {
                var tuoi = age.toIntOrNull()
                result =  if (tuoi == null) {
                    "Tuổi không hợp lệ"
                } else if( tuoi > 65) {
                    "Người già"
                } else if (tuoi > 6) {
                    "Người trẻ"
                 } else if(tuoi > 2){
                    "Trẻ em"
                } else {
                    "Em bé"
                }
                showResult = true
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0D47A1)
            ),
            modifier = Modifier
                .width(150.dp)
                .height(48.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Kiểm tra",
                fontSize = 18.sp,
                color = Color.White)
        }
        Spacer(modifier = Modifier.height(20.dp))

        if (showResult) {
            Text (
                color = Color.Green,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = "Xin chào $name, bạn là: $result"
            )
        }
    }
}

