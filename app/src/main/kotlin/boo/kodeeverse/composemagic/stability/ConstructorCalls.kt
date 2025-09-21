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
import androidx.compose.runtime.Immutable
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
import androidx.compose.ui.unit.dp
import boo.kodeeverse.composemagic.currentRecomposeScopeLabel
import java.lang.System.currentTimeMillis

abstract class UnstableMarker {
  var createdAt: Long = currentTimeMillis()
}

class UnstableButAlwaysSame : UnstableMarker() {
  override fun equals(other: Any?): Boolean = true
  override fun hashCode(): Int = 0
}

@Stable class StableClass : UnstableMarker() {
  override fun equals(other: Any?): Boolean = true
  override fun hashCode(): Int = 0
}

@Immutable class ImmutableClass : UnstableMarker() {
  override fun equals(other: Any?): Boolean = true
  override fun hashCode(): Int = 0
}

@Composable fun UnstableAndAlwaysSameCallDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text("ROOT @ $currentRecomposeScopeLabel (${currentTimeMillis()})")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    UnstableButAlwaysSameCall(UnstableButAlwaysSame())
  }
}

@Composable fun StableAndAlwaysSameCallDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text("ROOT @ $currentRecomposeScopeLabel (${currentTimeMillis()})")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    StableCall(StableClass())
  }
}

@Composable fun GivenStableValueViaArgumentDemo(value: UnstableMarker = StableClass()) {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text("ROOT argument @ $currentRecomposeScopeLabel (${currentTimeMillis()})")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    GivenStableValue(value)
  }
}

@Composable fun GivenStableValueViaPropDemo() {
  var count by remember { mutableIntStateOf(0) }
  val value: UnstableMarker = remember { StableClass() }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text("ROOT prop @ $currentRecomposeScopeLabel (${currentTimeMillis()})")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    GivenStableValue(value)
  }
}

@Composable fun GivenImmutableValueViaArgumentDemo(value: UnstableMarker = ImmutableClass()) {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text("ROOT argumemt @ $currentRecomposeScopeLabel (${currentTimeMillis()})")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    GivenImmutableValue(value)
  }
}

@Composable fun GivenImmutableValueViaPropDemo() {
  var count by remember { mutableIntStateOf(0) }
  val value: UnstableMarker = remember { ImmutableClass() }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text("ROOT prop @ $currentRecomposeScopeLabel (${currentTimeMillis()})")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    GivenImmutableValue(value)
  }
}

@Composable private fun UnstableButAlwaysSameCall(value: UnstableButAlwaysSame) {
  Text(
    "UnstableButAlwaysSameCall @ $currentRecomposeScopeLabel (${value.createdAt})",
    fontWeight = FontWeight.Bold,
  )
}

@Composable private fun StableCall(value: StableClass) {
  Text(
    "StableCall @ $currentRecomposeScopeLabel (${value.createdAt})",
    fontWeight = FontWeight.Bold,
  )
}

@Composable private fun GivenStableValue(value: UnstableMarker) {
  Text(
    "GivenStableValue @ $currentRecomposeScopeLabel (${value.createdAt})",
    fontWeight = FontWeight.Bold,
  )
}

@Composable private fun GivenImmutableValue(value: UnstableMarker) {
  Text(
    "GivenImmutableValue @ $currentRecomposeScopeLabel (${value.createdAt})",
    fontWeight = FontWeight.Bold,
  )
}

internal fun used(a: Any) {}
