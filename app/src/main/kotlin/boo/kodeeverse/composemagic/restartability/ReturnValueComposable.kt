@file:Suppress("ComposableNaming")

package boo.kodeeverse.composemagic.restartability

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import boo.kodeeverse.composemagic.CurrentMsText

@Composable fun ReturnValueComposableDemo() {
  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT")
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    ReturnValueComposable()
  }
}

@Composable private fun ReturnValueComposable(): Int {
  var count by remember { mutableIntStateOf(0) }

  CurrentMsText("ReturnValueContent")
  Text(
    "count: $count",
    modifier = Modifier
      .clip(RoundedCornerShape(10.dp))
      .clickable { count++ }
      .background(color = Color.Green)
      .padding(horizontal = 20.dp, vertical = 10.dp),
    fontSize = 20.sp,
  )

  return 0
}
