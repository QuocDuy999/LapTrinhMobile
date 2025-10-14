package com.uth.baitaptuan3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uth.baitaptuan3.ui.theme.BaiTapTuan3Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaiTapTuan3Theme {
                UIApp()
            }
        }
    }
}

@Composable
fun UIApp(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("list") { ComponentListScreen(navController) }
        composable("detail/{type}") { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type")
            DetailScreen(type?:"",navController)
        }

    }
}


@Composable
fun WelcomeScreen(navController: NavHostController) {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(id = R.drawable.jetpack_compose),
                contentDescription = "Jetpack Compose",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                text = "Jetpack Compose",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Jetpack Compose is a modern toolkit for building native Android applications using a declarative programming programming approach.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(200.dp))
            Button(
                onClick = { navController.navigate("list")},
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue
                )

            ){
                Text(
                    text = "I'M READY",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
@Composable
fun ComponentListScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        // Tiêu đề
        Text(
            text = "UI Components List",
            color = Color(0xFF42A5F5),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Display
        Text(
            text = "Display",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        ComponentItem("Text", "Displays text", navController)
        ComponentItem("Image", "Displays an image", navController)
        ComponentItem("Card", "Container with rounded corners", navController)
        // Input
        Text(
            text = "Input",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        ComponentItem("TextField", "Input field for text", navController)
        ComponentItem("PasswordField", "Input field for passwords", navController)
        ComponentItem("Checkbox", "Toggle between checked/unchecked", navController)
        // Playout
        Text(
            text = "Layout",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        ComponentItem("Column", "Arranges elements vertically", navController)
        ComponentItem("Row", "Arranges elements horizontally", navController)
        ComponentItem("Box", "Stacks elements on top of each other", navController)
        ComponentItem("Spacer", "Adds empty space between items", navController)

        // Interaction
        Text(
            text = "Interaction",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        ComponentItem("SnackBar", "Shows brief message at bottom", navController)
        ComponentItem("Dialog", "Displays popup dialog", navController)
    }
}

@Composable
fun ComponentItem(title: String, desc: String, navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color(0xFFD0E8FF), shape = RoundedCornerShape(8.dp))
            .clickable { navController.navigate("detail/$title") }
            .padding(12.dp)
    ) {
        Column {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = desc,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}


@Composable
fun DetailScreen(type: String, navController: NavHostController) {
    val detailName = when (type) {
        "Text" -> "TextDetail"
        "Image" -> "ImageDetail"
        "TextField" -> "TextFieldDetail"
        "PasswordField" -> "PasswordFieldDetail"
        "Column" -> "ColumnLayout"
        "Row" -> "RowLayout"
        "Card" -> "CardDetailScreen"
        "Checkbox" -> "CheckboxDetail"
        "Box" -> "BoxDetail"
        "Spacer" -> "SpacerDetail"
        "Dialog" -> "DialogDetail"
        "SnackBar" -> "SnackBarDetail"
        else -> "Không tìm thấy component"
    }
        // Thanh tiêu đề
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
        ) {
            // Nút quay lại
            IconButtonWithBorder(
                icon = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "Back",
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterStart)
            )

            // Tiêu đề căn giữa
            Text(
                text = detailName,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF42A5F5),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Gọi nội dung chi tiết tương ứng
        when (type) {
            "Text" -> TextDetail()
            "Image" -> ImageDetail()
            "TextField" -> TextFieldDetail()
            "PasswordField" -> PasswordFieldDetail()
            "Column" -> ColumnLayoutDemo()
            "Row" -> RowLayoutDemo()
            "Card" -> CardDetailScreen()
            "Checkbox" -> CheckboxDetail()
            "Box" -> BoxDetail()
            "Spacer" -> SpacerDetail()
            "Dialog" -> DialogDetail()
            "SnackBar" -> SnackBarDetail()
            else -> Text("Không tìm thấy component")
        }
    }
@Composable
fun IconButtonWithBorder(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    iconColor: Color = Color(0xFF42A5F5)
) {
    Box(
        modifier = modifier
            .size(54.dp)
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = iconColor, // chỉ cần tint cho icon
                modifier = Modifier.size(200.dp)
            )
        }
    }
}
@Composable
fun TextDetail() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            buildAnnotatedString {
                append(" The ")
                withStyle(
                    style = SpanStyle(
                        textDecoration = TextDecoration.LineThrough
                    )
                ) {
                    append(" quick ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF8B4513),
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(" Brown\n\n")
                }
                append(" fox jumps ")
                withStyle(
                    style = SpanStyle(
                        fontStyle = FontStyle.Italic
                    )
                ) {
                    append(" over\n\n")
                }
                append(" the ")
                withStyle(
                    style = SpanStyle(
                        textDecoration = TextDecoration.Underline,
                        fontStyle = FontStyle.Italic
                    )
                ) {
                    append(" lazy ")
                }
                append(" dog.")
            },
            fontSize = 35.sp,

        )
    }
}
@Composable
fun ImageDetail() {
    val context = LocalContext.current
    Column (
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Image(
            painter = painterResource(id = R.drawable.anhuth),
            contentDescription = "UTH Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp)
                .clickable {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://upload.wikimedia.org/wikipedia/commons/3/36/H%C3%ACnh_%E1%BA%A3nh_UTH.jpg")
                    )
                    context.startActivity(intent)
                }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "https://upload.wikimedia.org/wikipedia/commons/3/36/H%C3%ACnh_%E1%BA%A3nh_UTH.jpg",
            fontSize = 16.sp,
            color = Color.Blue,
            modifier = Modifier
                .clickable{
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://upload.wikimedia.org/wikipedia/commons/3/36/H%C3%ACnh_%E1%BA%A3nh_UTH.jpg")
                )
                context.startActivity(intent)
                }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.anhsauuth),
            contentDescription = "In App",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "In App",
            fontSize = 24.sp,
            color = Color.Gray
        )
    }
}
@Composable
fun CardDetailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8FF))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Simple Information Card",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "This card only shows text content.",
                    color = Color.Gray
                )
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.anhuth),
                    contentDescription = "Example image",
                    modifier = Modifier
                        .size(120.dp)
                        .background(Color(0xFFBBDEFB), RoundedCornerShape(12.dp))
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = "Card with Image",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "This card combines image and text.",
                        color = Color.Gray
                    )
                }
            }
        }

        // 🔹 Card 3: Có nút hành động
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Interactive Card",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "This card contains a button for interaction.",
                    color = Color.Gray
                )
                Button(
                    onClick = { /* TODO: handle click */ },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Learn More")
                }
            }
        }
    }
}
@Composable
fun TextFieldDetail() {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Thông tin nhập") }
        )
        Text("$text",
            color = Color.Red,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}
@Composable
fun PasswordFieldDetail() {
    var password by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(false) }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Nhập mật khẩu") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            visualTransformation = if (isVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (isVisible)
                    Icons.Default.Visibility
                else
                    Icons.Default.VisibilityOff
                IconButton(
                    onClick = { isVisible = !isVisible }
                ) {
                    Icon(imageVector = image, contentDescription = null)
                }
            }
        )
    }
}
@Composable
fun CheckboxDetail() {
    var checked1 by remember { mutableStateOf(false) }
    var checked2 by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        // Mô tả
        Text(
            text = "Checkbox được sử dụng để cho phép người dùng bật/tắt một tùy chọn. "
                    + "Bạn có thể sử dụng chúng trong biểu mẫu, cài đặt hoặc danh sách lựa chọn.",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Checkbox đầu tiên
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { checked1 = !checked1 }
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Checkbox(
                checked = checked1,
                onCheckedChange = { checked1 = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF388E3C)
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (checked1) "Đã chọn mục này" else "Chưa chọn",
                fontSize = 18.sp,
                color = if (checked1) Color(0xFF388E3C) else Color.Gray
            )
        }

        // Checkbox thứ hai
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { checked2 = !checked2 }
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Checkbox(
                checked = checked2,
                onCheckedChange = { checked2 = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF1E88E5)
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (checked2) "Thông báo: BẬT" else "Thông báo: TẮT",
                fontSize = 18.sp,
                color = if (checked2) Color(0xFF1E88E5) else Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Gợi ý thêm
        Text(
            text = "💡 Mẹo: Bạn có thể dùng Checkbox để chọn nhiều mục cùng lúc, "
                    + "ví dụ trong danh sách sản phẩm hoặc thiết lập người dùng.",
            fontSize = 18.sp,
            color = Color(0xFF6D4C41),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}

@Composable
fun ColumnLayoutDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        repeat(5) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(10.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            color = Color(0xFF2196F3),
                            shape = RoundedCornerShape(12.dp)
                        )
                )
            }
        }
    }
}


@Composable
fun RowLayoutDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        repeat(4) { // 4 hàng
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.LightGray, // xám nhạt
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(10.dp)
            ) {
                repeat(3) { index -> // 3 ô mỗi hàng
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp)
                            .background(
                                color = if (index == 1) Color(0xFF2196F3) // ô giữa đậm
                                else Color(0xFF90CAF9), // hai ô ngoài nhạt
                                shape = RoundedCornerShape(12.dp)
                            )
                    )
                }
            }
        }
    }
}
@Composable
fun BoxDetail() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        // 🔹 Giải thích ngắn gọn
        Text(
            text = "Box cho phép bạn xếp chồng (stack) các phần tử lên nhau. "
                    + "Nó rất hữu ích khi bạn muốn chồng text lên hình ảnh, hoặc hiển thị overlay (lớp phủ).",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 🔹 Demo 1: Box cơ bản
        Text(
            text = "Ví dụ 1: Box đơn giản với màu",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1E88E5),
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .size(150.dp)
                .background(Color(0xFFBBDEFB), RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Hello Box!",
                color = Color(0xFF0D47A1),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 🔹 Demo 2: Box với chồng lớp (overlay)
        Text(
            text = "Ví dụ 2: Xếp chồng phần tử (Image + Overlay + Text)",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1E88E5),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            // Ảnh nền
            Image(
                painter = painterResource(id = R.drawable.anhuth), // Đổi thành ảnh thật trong project của bạn
                contentDescription = "Sample Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )

            // Lớp mờ phủ lên ảnh
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color(0x99000000))
            )

            // Text hiển thị trên cùng
            Text(
                text = "Overlay Text",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
@Composable
fun SpacerDetail() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        // 🔹 Mô tả
        Text(
            text = "Spacer được sử dụng để thêm khoảng trống giữa các phần tử trong layout. "
                    + "Nó giúp căn chỉnh, tạo khoảng cách và làm cho bố cục thoáng hơn.",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 🔹 Ví dụ 1: Dùng Spacer trong Column
        Text(
            text = "Ví dụ 1: Spacer trong Column",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1E88E5)
        )

        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF1F8E9))
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Item 1", color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(20.dp)) // 👈 Khoảng trống giữa các Text
                Text("Item 2", color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(20.dp))
                Text("Item 3", color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        // 🔹 Ví dụ 2: Dùng Spacer trong Row
        Text(
            text = "Ví dụ 2: Spacer trong Row (đẩy phần tử ra hai bên)",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1E88E5)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE3F2FD))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Trái", color = Color(0xFF0D47A1), fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.weight(1f)) // 👈 Đẩy phần tử phải sang biên
                Text("Phải", color = Color(0xFF0D47A1), fontWeight = FontWeight.Bold)
            }
        }
    }
}
@Composable
fun DialogDetail() {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        // 🔹 Mô tả
        Text(
            text = "Dialog là cửa sổ bật lên (popup) hiển thị nội dung quan trọng hoặc yêu cầu người dùng xác nhận.",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // 🔹 Nút mở Dialog
        Button(
            onClick = { showDialog = true },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF43A047))
        ) {
            Text("Mở Dialog", fontSize = 24.sp)
        }

        Spacer(modifier = Modifier.height(60.dp))

        // 🔹 Hiển thị Dialog khi cần
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text("Xác nhận hành động")
                },
                text = {
                    Text("Bạn có chắc chắn muốn xóa mục này không?")
                },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Đồng ý", color = Color(0xFF1E88E5))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Hủy", color = Color.Gray)
                    }
                }
            )
        }
    }
}
@Composable
fun SnackBarDetail() {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 🔹 Mô tả
            Text(
                text = "Snackbar là thông báo ngắn xuất hiện ở cuối màn hình để hiển thị phản hồi cho người dùng "
                        + "về hành động họ vừa thực hiện (ví dụ: lưu, xóa, gửi…).",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))

            // 🔹 Nút hiển thị Snackbar
            Button(
                onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Dữ liệu đã được lưu thành công!",
                            actionLabel = "OK"
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5))
            ) {
                Text("Hiển thị Snackbar", fontSize = 24.sp)
            }
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .padding(bottom = 60.dp)
                .align (Alignment.BottomCenter)
        )
    }
}

