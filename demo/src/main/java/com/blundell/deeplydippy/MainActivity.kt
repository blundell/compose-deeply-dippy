package com.blundell.deeplydippy

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeeplyDippy()
        }
    }
}

@Preview
@Composable
fun PreviewDeeplyDippy() {
    DeeplyDippy()
}

@Composable
private fun DeeplyDippy() {
    Column(
        modifier = Modifier.padding(4.dip)
    ) {
        // TODO add a fun animation using DIP
        Text(
            modifier = Modifier.padding(16.dip),
            text = "Hello World 01",
        )
        Text(
            modifier = Modifier.padding(16.dip.plus(8.dp)),
            text = "Hello World 02",
        )
        Text(
            modifier = Modifier.padding(16.dip.times(2)),
            text = "Hello World 03",
        )
        Text(
            modifier = Modifier.padding(Dip(16F).plus(8.dip)),
            text = "Hello World 04",
        )
        Text(
            modifier = Modifier.padding(Dip(16F).plus(Dip(8F))),
            text = "Hello World 05",
        )
        Text(
            modifier = Modifier.padding(Dip(16F).plus(Dip(8F).plus(4.dip))),
            text = "Hello World 06",
        )
        Text(
            modifier = Modifier.padding(3.times(2.dip)),
            text = "Hello World 07",
        )
        Text(
            modifier = Modifier.padding(Dip(3F).takeOrElse { 3.dip }),
            text = "Hello World 08",
        )
        Text(
            modifier = Modifier.padding(Dip(3F).compareTo(Dip(1F)).dip),
            text = "Hello World 09",
        )
        Text(
            modifier = Modifier.padding(Dip.Hairline),
            text = "Hello World 10",
        )
        Text(
            modifier = Modifier.padding(Dip(3F).coerceIn(Dip(1F), Dip(3F))),
            text = "Hello World 11",
        )
    }
}
