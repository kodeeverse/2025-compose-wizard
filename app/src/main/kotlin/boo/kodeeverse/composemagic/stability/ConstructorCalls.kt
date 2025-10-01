package boo.kodeeverse.composemagic.stability

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import boo.kodeeverse.composemagic.currentRecomposeScopeHash
import java.lang.System.currentTimeMillis

abstract class UnstableMarker() {
  var createdAt: Long = currentTimeMillis()
}

class UnstableAndAlwaysSameClass : UnstableMarker() {
  override fun equals(other: Any?): Boolean = true
  override fun hashCode(): Int = 42
}

@Stable class StableClass : UnstableMarker()

@Stable class StableAndAlwaysSameClass : UnstableMarker() {
  override fun equals(other: Any?): Boolean = true
  override fun hashCode(): Int = 42
}

@Immutable class ImmutableClass : UnstableMarker()

@Composable fun GivenUnstableAndAlwaysSameClassDemo() {
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

    GivenUnstableAndAlwaysSameClass(UnstableAndAlwaysSameClass())
  }
}

@Composable fun GivenStableAndAlwaysSameClassDemo() {
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

    GivenStableAndAlwaysSameClass(StableAndAlwaysSameClass())
  }
}

@Composable fun GivenStableClassViaArgumentDemo(value: StableClass = StableClass()) {
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

    GivenStableClass(value)
  }
}

@Composable fun GivenStableClassViaPropDemo() {
  var count by remember { mutableIntStateOf(0) }
  val value = StableClass()

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

    GivenStableClass(value)
  }
}

@Composable fun GivenImmutableClassViaArgumentDemo(value: ImmutableClass = ImmutableClass()) {
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

    GivenImmutableClass(value)
  }
}

@Composable fun GivenImmutableClassViaPropDemo() {
  var count by remember { mutableIntStateOf(0) }
  val value = ImmutableClass()

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

    GivenImmutableClass(value)
  }
}

@Composable private fun GivenUnstableAndAlwaysSameClass(value: UnstableAndAlwaysSameClass) {
  Text(
    "GivenUnstableAndAlwaysSameClass @ $currentRecomposeScopeHash\n(${value.createdAt})",
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentWidth(),
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center,
  )
}

@Composable private fun GivenStableAndAlwaysSameClass(value: StableAndAlwaysSameClass) {
  Text(
    "GivenStableAndAlwaysSameClass @ $currentRecomposeScopeHash\n(${value.createdAt})",
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentWidth(),
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center,
  )
}

@Composable private fun GivenStableClass(value: StableClass) {
  Text(
    "GivenStableClass @ $currentRecomposeScopeHash (${value.createdAt})",
    fontWeight = FontWeight.Bold,
  )
}

@Composable private fun GivenImmutableClass(value: ImmutableClass) {
  Text(
    "GivenImmutableClass @ $currentRecomposeScopeHash (${value.createdAt})",
    fontWeight = FontWeight.Bold,
  )
}

internal fun used(a: Any) {}
