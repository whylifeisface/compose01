package com.example.compose01

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import com.example.compose01.bean.IconButtonKt
import com.example.compose01.bean.IconMore
import com.example.compose01.ui.theme.Compose01Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private var unit: @Composable() (() -> Unit?)? = null
        get() = field
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.unit.observe(this, Observer {

        })
        setContent {
            Compose01Theme {
                // A surface container using the 'background' color from the theme
                Surface {
                    mian(viewModel)

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun init(sheetState: ModalBottomSheetState,viewModel: MainActivityViewModel): @Composable () -> Unit {
    return {
        Column {
            MySearchView()
            Grid()
            LazyH()
            Button(onClick = {
                viewModel.set_Unit {
                    Greeting2(name = "asasa")
                }
            }) {
                Text(text = "asas")
            }
           Tool(sheetState = sheetState, viewModel = viewModel)

        }

    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun mian(viewModel: MainActivityViewModel) {
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    viewModel.set_Unit(init(sheetState,viewModel))

    ActionSheet( sheetState = sheetState)
}

fun ss(): List<IconButtonKt> {

    return listOf(
        IconButtonKt(R.drawable.vector_drawable_leftarrow, false),
        IconButtonKt(R.drawable.vector_drawable_left_arrow111, false),
        IconButtonKt(R.drawable.vector_drawable_home, true),
        IconButtonKt(R.drawable.vector_drawable_more, true),
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Tool(sheetState: ModalBottomSheetState, viewModel: MainActivityViewModel) {
    val current: Context = LocalContext.current

    val scope = rememberCoroutineScope()
    val ss = ss()
    Compose01Theme(false) {
        Scaffold(
            bottomBar = {
                CompositionLocalProvider(
                    LocalContentColor provides Color.Black,
//                LocalContentAlpha.provides()
                ) {
                    BottomNavigation(
                        backgroundColor = Color.White,
//                    contentColor = Color.White
                    ) {

                        repeat(ss.size) { it ->
                            val enable = ss.get(it).enable
                            BottomNavigationItem(
                                selected = false,
                                enabled = enable,
                                unselectedContentColor =
                                if (enable) {
                                    Color.Black
                                } else {
                                    Color(R.color.ccc)
                                },
                                onClick = {
                                    Log.e(TAG, "Tool: $it")
                                    when (it) {
                                        1 -> {}
                                        2 -> {}
                                        3 -> {}
                                        4 -> {
                                            viewModel.set_Unit {
                                                init(sheetState = sheetState,viewModel)
                                            }
                                        }
                                        5 -> {
                                            scope.launch {
                                                sheetState.show()
                                            }
                                        }
                                    }
                                },
                                icon = {
                                    Icon(
                                        painter = painterResource(id = ss[it].painter),
                                        contentDescription = ""
                                    )
                                }
                            )
                            if (it == 1) {
                                Text(text = "java")
                            }
                        }
                    }
                }
            }, content = {


            }
        )

    }
}

@Composable
fun ColumnH() {
    val horizontal = rememberScrollState()

    LazyColumn(content = {
        items(100) {

            Image(
                painter = painterResource(id = R.drawable.vector_drawable_more),
                contentDescription = ""
            )

        }
    })
}

@Composable
fun LazyH() {
    val scroll = rememberScrollState()

    LazyRow(
        modifier = Modifier
            .height(100.dp),
        content = {
            items(20) {

                Image(

                    painter = painterResource(id = R.drawable.vector_drawable_home),
                    contentDescription = ""
                )
            }
        })
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun ActionSheet( sheetState: ModalBottomSheetState) {
    val LocalContext = LocalContext
    val list: List<IconMore> = IconMore.myIniy()
    val LazyContent: @Composable ColumnScope.() -> Unit = {
        IconButton(onClick = { /*TODO*/ }) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.vector_drawable_user_jurassic),
                    contentDescription = ""
                )
                Text(text = "")
//                CompositionLocalProvider(LocalCOLOR provides Color.Red) {
                Button(
                    modifier = Modifier.absoluteOffset(280.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text(
                        text = "点击登录", color = Color.White
                    )
                }
//                }

            }
        }
        Row {
            LazyVerticalGrid(cells = GridCells.Fixed(5), content = {
                items(list.size) { it ->
                    IconToggleButton(
                        checked = (LocalContext.current.packageName != "com.example.compose01"),
                        onCheckedChange = {
                        }, enabled = (LocalContext.current.packageName != "com.example.compose01")
                    ) {
                        Column {
//                   Icon(painter = painterResource(id =), contentDescription = "")
                            Text(text = list[it].text)
                        }
                    }
                }
            })
        }

    }
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetContent = LazyContent, sheetState = sheetState, content =
    )
    BackHandler(
        enabled = (sheetState.currentValue == ModalBottomSheetValue.Expanded)
                || (sheetState.currentValue == ModalBottomSheetValue.HalfExpanded), onBack = {
            scope.launch {
                sheetState.hide()
            }
        }
    )
}

@Composable
fun MySearchView() {
    val context = LocalContext.current
    Button(
        onClick = {
            context.startActivity(Intent("search"))
        },
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
    ) {
        Text(text = "输入搜索", color = Color(R.color.ccc))
        Icon(
            painter = painterResource(id = R.drawable.vector_drawable_photo),
            contentDescription = ""
        )
    }

}


@Preview(showBackground = true)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Grid() {
    val listData = (0..9).toList()
    LazyVerticalGrid(cells = GridCells.Fixed(5),
        content = {
            items(listData) {
                Card {
                    Column {
                        Text(text = "$it")
                    }
                }
            }
        })
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose01Theme {
        Greeting("Android")
    }
}