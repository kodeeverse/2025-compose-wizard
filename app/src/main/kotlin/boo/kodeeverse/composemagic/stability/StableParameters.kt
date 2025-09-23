@file:Suppress("SameParameterValue")

package boo.kodeeverse.composemagic.stability

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import boo.kodeeverse.composemagic.currentRecomposeScopeHash
import java.lang.System.currentTimeMillis

@Composable fun StableParameterDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text("ROOT @ $currentRecomposeScopeHash (${currentTimeMillis()})")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    StableParameter(1)
  }
}

private val list = mutableListOf(0)

// Bug but Feature: strong-skipping으로 인해 list가 변경되어도 리컴포지션되지 않음
@Composable fun UnstableParameterDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      "ROOT @ $currentRecomposeScopeHash (${list.joinToString()})",
      modifier = Modifier.padding(horizontal = 30.dp),
    )
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable {
          count++
          list += count
        }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    UnstableParameter(list)
  }
}

@Composable fun UnstableParameterButStableArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text("ROOT @ $currentRecomposeScopeHash (${currentTimeMillis()})")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    UnstableParameterButStableArgument(1)
  }
}

@Composable private fun StableParameter(value: Int) {
  used(value)

  Text(
    "StableParameter @ $currentRecomposeScopeHash (${currentTimeMillis()})",
    fontWeight = FontWeight.Bold,
  )
}

// TODO StableParameterAndUnstableArgument

@Composable private fun UnstableParameter(value: List<*>) {
  Text(
    "UnstableParameter @ $currentRecomposeScopeHash (${value.joinToString()})",
    fontWeight = FontWeight.Bold,
  )
}

@Composable private fun UnstableParameterButStableArgument(value: Any) {
  used(value)

  Text(
    "UnstableParameterButStableArgument @ $currentRecomposeScopeHash (${currentTimeMillis()})",
    fontWeight = FontWeight.Bold,
    fontSize = 12.sp,
  )
}
