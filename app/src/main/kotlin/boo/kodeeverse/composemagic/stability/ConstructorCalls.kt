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
import boo.kodeeverse.composemagic.currentRecomposeScopeHash
import java.lang.System.currentTimeMillis

abstract class UnstableMarker() {
  var createdAt: Long = currentTimeMillis()
}

class UnstableAndAlwaysSameClass : UnstableMarker() {
  override fun equals(other: Any?): Boolean = true
  override fun hashCode(): Int = 0
}

@Stable class StableClass : UnstableMarker()

@Stable class StableAndAlwaysSameClass : UnstableMarker() {
  override fun equals(other: Any?): Boolean = true
  override fun hashCode(): Int = 1
}

@Immutable class ImmutableClass : UnstableMarker()

@Composable fun UnstableAndAlwaysSameCallDemo() {
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

    UnstableAndAlwaysSameCall(UnstableAndAlwaysSameClass())
  }
}

@Composable fun StableAndAlwaysSameCallDemo() {
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

    StableAndAlwaysSameCall(StableAndAlwaysSameClass())
  }
}

@Composable fun GivenStableValueViaArgumentDemo(value: StableClass = StableClass()) {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text("ROOT argument @ $currentRecomposeScopeHash (${currentTimeMillis()})")
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
    Text("ROOT prop @ $currentRecomposeScopeHash (${currentTimeMillis()})")
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

@Composable fun GivenImmutableValueViaArgumentDemo(value: ImmutableClass = ImmutableClass()) {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text("ROOT argumemt @ $currentRecomposeScopeHash (${currentTimeMillis()})")
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
    Text("ROOT prop @ $currentRecomposeScopeHash (${currentTimeMillis()})")
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

@Composable private fun UnstableAndAlwaysSameCall(value: UnstableAndAlwaysSameClass) {
  Text(
    "UnstableAndAlwaysSameCall @ $currentRecomposeScopeHash (${value.createdAt})",
    fontWeight = FontWeight.Bold,
  )
}

@Composable private fun StableAndAlwaysSameCall(value: StableAndAlwaysSameClass) {
  Text(
    "StableAndAlwaysSameCall @ $currentRecomposeScopeHash (${value.createdAt})",
    fontWeight = FontWeight.Bold,
  )
}

@Composable private fun StableCall(value: StableClass) {
  Text(
    "StableCall @ $currentRecomposeScopeHash (${value.createdAt})",
    fontWeight = FontWeight.Bold,
  )
}

// value를 StableClass로 받으면 리컴포지션 스킴됨
@Composable private fun GivenStableValue(value: UnstableMarker) {
  Text(
    "GivenStableValue @ $currentRecomposeScopeHash (${value.createdAt})",
    fontWeight = FontWeight.Bold,
  )
}

// value를 ImmutableClass로 받아도 리컴포지션 스킴됨
@Composable private fun GivenImmutableValue(value: UnstableMarker) {
  Text(
    "GivenImmutableValue @ $currentRecomposeScopeHash (${value.createdAt})",
    fontWeight = FontWeight.Bold,
  )
}

internal fun used(a: Any) {}
