package boo.kodeeverse.composemagic.stableCall

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

/*@Stable */private interface IStable

@JvmInline private value class StableInlineClass(val value: Int = 0) : IStable
@JvmInline private value class UnstableInlineClass(val value: Any = Any()) : IStable

@Stable private class StableClass : IStable
@Immutable private class ImmutableClass : IStable

private class UnstableClass(var value: Any = Any())

@Composable fun StableInlineConstructorCallDemo() {
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

    StableInlineConstructorCall()
  }
}

@Composable fun UnstableInlineConstructorCallDemo() {
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

    UnstableInlineConstructorCall()
  }
}

@Composable fun StableConstructorCallDemo() {
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

    StableConstructorCall()
  }
}

@Composable fun ImmutableConstructorCallDemo() {
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

    ImmutableConstructorCall()
  }
}

@Composable fun UnstableConstructorCallDemo() {
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

    UnstableConstructorCall()
  }
}

@Composable private fun StableInlineConstructorCall(value: IStable = StableInlineClass()) {
  used(value)

  Text(
    "StableInlineConstructorCall @ $currentRecomposeScopeLabel (${currentTimeMillis()})",
    fontWeight = FontWeight.Bold,
  )
}

@Composable private fun UnstableInlineConstructorCall(value: IStable = UnstableInlineClass()) {
  used(value)

  Text(
    "UnstableInlineConstructorCall @ $currentRecomposeScopeLabel (${currentTimeMillis()})",
    fontWeight = FontWeight.Bold,
  )
}

@Composable private fun StableConstructorCall(value: IStable = StableClass()) {
  used(value)

  Text(
    "StableConstructorCall @ $currentRecomposeScopeLabel (${currentTimeMillis()})",
    fontWeight = FontWeight.Bold,
  )
}

@Composable private fun ImmutableConstructorCall(value: IStable = ImmutableClass()) {
  used(value)

  Text(
    "ImmutableConstructorCall @ $currentRecomposeScopeLabel (${currentTimeMillis()})",
    fontWeight = FontWeight.Bold,
  )
}

@Composable private fun UnstableConstructorCall(value: UnstableClass = UnstableClass()) {
  used(value)

  Text(
    "UnstableConstructorCall @ $currentRecomposeScopeLabel (${currentTimeMillis()})",
    fontWeight = FontWeight.Bold,
  )
}

private fun used(a: Any) {}
