@file:Suppress("ClassName")

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import boo.kodeeverse.composemagic.CurrentMsText

class UnstableClass_ {
  var createdAt: Any = listOf<Nothing>() // unstable marker
}

@Stable class StableClass_

@JvmInline private value class StableBoxingClass(val value: Int)

@JvmInline private value class UnstableBoxingClass(val value: Any)

@Immutable class ImmutableClass_

@Immutable private class ImmutableClassWithAnyParameter(val value: Any)

@Composable fun UntableClassArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT unstableClassArgument")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
      fontSize = 20.sp,
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    ConstructorCallArgument(UnstableClass_())
  }
}

@Composable fun StableClassArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT stableClassArgument")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
      fontSize = 20.sp,
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    ConstructorCallArgument(StableClass_())
  }
}

@Composable fun ImmutableClassArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT immutableClassArgument")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
      fontSize = 20.sp,
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    ConstructorCallArgument(ImmutableClass_())
  }
}

@Composable fun ImmutableWithNonStaticArgumentClassArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT immutableClassWithNonStaticArgument")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
      fontSize = 20.sp,
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    ConstructorCallArgument(ImmutableClassWithAnyParameter(Any()))
  }
}

@Composable fun ImmutableWithStaticArgumentClassArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT immutableClassWithStaticArgument")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
      fontSize = 20.sp,
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    ConstructorCallArgument(ImmutableClassWithAnyParameter(123))
  }
}

@Composable fun StableClassParameterIntoArgumentDemo(value: StableClass_ = StableClass_()) {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT stableClassParameterIntoArgument")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
      fontSize = 20.sp,
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    ConstructorCallArgument(value)
  }
}

@Composable fun StableClassPropertyIntoArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }
  val value = StableClass_()

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT stableClassPropertyIntoArgument")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
      fontSize = 20.sp,
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    ConstructorCallArgument(value)
  }
}

@Composable fun ImmutableClassParameterIntoArgumentDemo(value: ImmutableClass_ = ImmutableClass_()) {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT immutableClassParameterIntoArgument\n")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
      fontSize = 20.sp,
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    ConstructorCallArgument(value)
  }
}

@Composable fun ImmutableClassPropertyIntoArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    val value = ImmutableClass_()

    CurrentMsText("ROOT immutableClassPropertyIntoArgument\n")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
      fontSize = 20.sp,
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    ConstructorCallArgument(value)
  }
}

@Composable fun StableBoxingClassArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT stableBoxingArgument")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
      fontSize = 20.sp,
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    ConstructorCallArgument(StableBoxingClass(1))
  }
}

@Composable fun UnstableBoxingClassArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT unstableBoxingArgument")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
      fontSize = 20.sp,
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    ConstructorCallArgument(UnstableBoxingClass(Any()))
  }
}

@Composable private fun ConstructorCallArgument(value: Any) {
  used(value)
  CurrentMsText("ConstructorCallArgument")
}

internal fun used(a: Any) {}
