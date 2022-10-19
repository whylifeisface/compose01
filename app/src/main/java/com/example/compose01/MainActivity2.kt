
package com.example.compose01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose01.ui.theme.Compose01Theme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose01Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting2("Android")
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview(showBackground = true)
@Composable
fun SearchView() {
    var inputValue by remember {
        mutableStateOf("")//输入文本
    }
    val keyboard = LocalSoftwareKeyboardController.current
    OutlinedTextField(

        value = inputValue,
        onValueChange = {
            inputValue = it
        },
        modifier = Modifier.onFocusChanged {
            if (!it.isFocused){
                keyboard?.hide()
            }
        },
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.textFieldColors(Color.White),
        placeholder = { Text(text = "请输入内容") },
        leadingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.vector_drawable_search),
                    contentDescription = ""
                )
            }
        },
        trailingIcon = {
            if (inputValue.isNotEmpty())
                (IconButton(onClick = {
                    inputValue = ""
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.vector_drawable_cancel),
                        contentDescription = ""
                    )
                })
        },
        keyboardActions = KeyboardActions(onDone = {
            keyboard?.hide()
        })
    )

}


@Preview(showBackground = true)
@Composable
fun List(){
    val list:List<Int> ?= null;
    Row {
        repeat(list!!.size){
            Card(
                content = {
                    Image(painter = painterResource(id = R.drawable.vector_drawable_search), contentDescription ="")
                }
            )
        }
    }
}

@Composable
fun History(){
    val list:List<Int> ?= null;
    Column {
        repeat(list!!.size){
            Card(
                content = {
                    Button(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.vector_drawable_top_right), contentDescription ="")
                        Text(text = "")
                        Icon(painter = painterResource(id = R.drawable.vector_drawable_top_right), contentDescription ="")
                    }
                }
            )
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    Compose01Theme {
        Greeting2("Android")
    }
}