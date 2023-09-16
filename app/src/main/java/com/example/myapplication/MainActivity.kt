package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fontFamily = FontFamily(
            Font(R.font.poppins_bold, FontWeight.Bold),
            Font(R.font.poppins_regular, FontWeight.Normal),
            Font(R.font.poppins_thin, FontWeight.Thin),
        )

        val checkboxStateList = mutableListOf<Boolean>()

        setContent {

            Box (modifier = Modifier.background(Color(0xFFFFEFEB)).fillMaxSize()) {
                LazyColumn(modifier = Modifier.padding(20.dp)) {
                    itemsIndexed(
                        listOf("This", "is", "a", "list")
                    ) { index, string ->
                        ChecklistItem(
                            modifier = Modifier,
                            title = string,
                            font = fontFamily,
                            index = index
                        ) {
                            checkboxStateList.plus(it)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun ChecklistItem (
    modifier: Modifier,
    title: String,
    font: FontFamily,
    index: Int,
    checkboxState: (Boolean) -> Unit
){
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 1.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF)
        )
    ) {
        var checkedState by remember { mutableStateOf(false)}
        var titleState by remember { mutableStateOf(title)}
        Row(horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {
            Text(
                text = titleState,
                fontFamily = font,
                modifier = Modifier.weight(3f)
            )

            Checkbox(checked = checkedState, onCheckedChange = {
                checkedState = it
                checkboxState(it)

                if (it){
                    titleState = "$title $index"
                } else {
                    titleState = title
                }
            })
        }

    }
}
/*
@Preview(showBackground = true)
@Composable
fun ChecklistPreview(){
    ChecklistItem(title = "Hello", modifier = Modifier.fillMaxWidth(), font = FontFamily())
}

*/


