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
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import boo.kodeeverse.composemagic.currentRecomposeScopeHash
import java.lang.System.currentTimeMillis

@Stable private interface StableInterface

@Stable private class StableClass2 : StableInterface

private class UnstableClass : StableInterface {
  var a: Any = Any()
}

// Bug but Feature: strong-skipping으로 인해 list가 변경되어도 리컴포지션되지 않음
@Composable fun MutableButSameInstanceArgumentInUnstableParameterDemo() {
  var count by remember { mutableIntStateOf(0) }
  val list = remember { mutableListOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      "ROOT mutableButSameInstanceArgumentInUnstableParameter\n" +
        "@ $currentRecomposeScopeHash (${list.joinToString()})",
      textAlign = TextAlign.Center,
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

@Composable fun StableArgumentInUnstableParameterDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      "ROOT stableArgumentInUnstableParameter\n" +
        "@ $currentRecomposeScopeHash (${currentTimeMillis()})",
      textAlign = TextAlign.Center,
    )
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    UnstableParameter(StableClass2())
  }
}

@Composable fun StableArgumentInStableParameterDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      "ROOT stableArgumentInStableParameter\n" +
        "@ $currentRecomposeScopeHash (${currentTimeMillis()})",
      textAlign = TextAlign.Center,
    )
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    StableParameter(StableClass2())
  }
}

@Composable fun UnstableArgumentInStableParameterDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      "ROOT stableArgumentInStableParameter\n" +
        "@ $currentRecomposeScopeHash (${currentTimeMillis()})",
      textAlign = TextAlign.Center,
    )
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    StableParameter(UnstableClass())
  }
}

@Composable private fun UnstableParameter(value: Any) {
  Text(
    "UnstableParameter @ $currentRecomposeScopeHash\n" +
      "(${currentTimeMillis()})\n" +
      "(${(value as? List<*>)?.joinToString() ?: value.hashCode()})",
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center,
  )
}

@Composable private fun StableParameter(value: StableInterface) {
  used(value)

  Text(
    "StableParameter @ $currentRecomposeScopeHash (${currentTimeMillis()})",
    fontWeight = FontWeight.Bold,
  )
}
