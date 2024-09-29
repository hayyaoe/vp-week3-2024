package com.example.week_3_visual_programming.ui.views

import android.annotation.SuppressLint
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week_3_visual_programming.R

data class Note(val title: String, val content: String, val category: String)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@Composable
fun VerySimpleNote(){

    var noteList by rememberSaveable { mutableStateOf(mutableListOf<Note>()) }
    var isAddNote by remember { mutableStateOf(false) }
    var isDetailNote by remember {mutableStateOf(false)}
    var isDropDown by remember { mutableStateOf(false) }
    var titleError by remember { mutableStateOf(false) }
    var contentError by remember { mutableStateOf(false) }
    var categoryError by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var detailNote by remember { mutableStateOf<Note?>(null) }

    val categories = listOf("Daily Life", "Work", "Fun Stuff")

    Column(
        modifier = Modifier
            .background(Color(0xFFF4F4F4))
            .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Note",
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold
            )
            Button(onClick = { isAddNote = true }) {
                Text(
                    text = "Add Note",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (noteList.size == 0){
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ){
                    Text(
                        color = Color.LightGray,
                        text = "Note List Empty"
                    )
                }
            }else{
            noteList.forEachIndexed { index, note ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                        .padding(8.dp)
                        .clickable {
                            detailNote = note
                            isDetailNote = true
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = note.title,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = note.content,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.width(75.dp)
                    ) {
                        Text(
                            text = note.category,
                            modifier = Modifier
                                .background(color = Color.Magenta, shape = RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp),
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        IconButton(
                            onClick = {
                                noteList = noteList.toMutableList().also { it.remove(note) }
                            },
                            modifier = Modifier.size(20.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.baseline_check_24),
                                contentDescription = "Delete Note"
                            )
                        }
                    }
                }
                if (index < noteList.size - 1) {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
            }
        }

        if(isDetailNote && detailNote != null){
            AlertDialog(
                onDismissRequest = { isDetailNote = false},
                title = {
                    Row{
                        Text(
                            text = detailNote!!.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = detailNote!!.category,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .background(color = Color.Magenta, shape = RoundedCornerShape(8.dp))
                                .padding(horizontal = 4.dp),
                            color = Color.White
                        )
                    }
                },
                text = {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                    ){
                        Text(
                            text = detailNote!!.content,
                            modifier = Modifier
                                .padding(vertical = 16.dp, horizontal = 12.dp)
                        )
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            isDetailNote = false
                            noteList = noteList.toMutableList().also { it.remove(detailNote) }
                            detailNote = null
                        },
                        colors = ButtonDefaults.buttonColors(Color.Red)
                    ) {
                        Text(
                            text = "Delete"
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            isDetailNote = false
                            detailNote = null
                        }
                    ) {
                        Text("Close")
                    }
                }
            )
        }


        if (isAddNote) {
            AlertDialog(
                onDismissRequest = { isAddNote = false },
                title = {
                    Text(
                        text = "Add New Note",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                text = {
                    Column {
                        OutlinedTextField(
                            value = title,
                            onValueChange = { title = it },
                            label = { Text(text = "Title") },
                            modifier = Modifier.fillMaxWidth(),
                            isError = titleError
                        )

                        if (titleError) {
                            Text(
                                text = "Title is required",
                                color = Color.Red,
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp)) // Space between fields

                        OutlinedTextField(
                            value = content,
                            onValueChange = { content = it },
                            label = { Text(text = "Content") },
                            modifier = Modifier.fillMaxWidth(),
                            isError = contentError
                        )

                        if (contentError) {
                            Text(
                                text = "Content is required",
                                color = Color.Red,
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp)) // Space between fields

                        // Category selection
                        Box {
                            Button(
                                onClick = { isDropDown = true },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(text = category.ifEmpty { "Select Category" })
                            }

                            DropdownMenu(
                                expanded = isDropDown,
                                onDismissRequest = { isDropDown = false }
                            ) {
                                categories.forEach { item ->
                                    DropdownMenuItem(
                                        text = { Text(text = item, modifier = Modifier.fillMaxWidth()) },
                                        onClick = {
                                            category = item
                                            isDropDown = false
                                        }
                                    )
                                }
                            }
                        }

                        if (categoryError) {
                            Text(
                                text = "Please select a category",
                                color = Color.Red,
                            )
                        }
                    }

                },
                confirmButton = {
                    Button(
                        onClick = {
                            val isTitleValid = title.isNotBlank()
                            val isContentValid = content.isNotBlank()
                            val isCategoryValid = category.isNotBlank()

                            if (isCategoryValid && isContentValid && isTitleValid) {
                                noteList = noteList.toMutableList().also {
                                    it.add(Note(title, content, category))
                                }
                                isAddNote = false
                                titleError = false
                                contentError = false
                                categoryError = false
                                title = ""
                                content = ""
                                category = ""
                            } else {
                                titleError = !isTitleValid
                                contentError = !isContentValid
                                categoryError = !isCategoryValid
                            }
                        }
                    ) {
                        Text(text = "Add Note")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { isAddNote = false },
                        colors = ButtonDefaults.buttonColors(Color.Red)
                    ) {
                        Text(text = "Cancel")

                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun VerySimpleNotePreview(){
    VerySimpleNote()
    //NoteView(Note("Note Sample Glock Nice","Lag Train, Hanarebanare no Machi Wo", "Daily Life"))
}