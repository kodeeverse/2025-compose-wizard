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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import boo.kodeeverse.composemagic.CurrentMsText
import boo.kodeeverse.composemagic.stability.MyUnstableClass.Companion.stableValuePropertyInCompanionObject
import boo.kodeeverse.composemagic.stability.MyUnstableClass.Companion.stableVariablePropertyInCompanionObject
import java.lang.System.currentTimeMillis

private val stableValueProperty = MyStableClass()

private val unstableValueBoundsStableTypeProperty: MyStableClass = MyUnstableClass()

@Stable private var stableVariableProperty = MyStableClass()
  set(value) {
    field = MyStableClass()
  }
  get() = MyStableClass()

private var variableProperty: MyStableClass = MyStableClass()
  set(value) {
    field = MyStableClass()
  }
  get() = MyStableClass()

private enum class MyEnum {
  A,
}

@Stable private object StableObject

private object UnstableObject {
  var a: Any = listOf(1) // unstable maker
}

@Stable private open class MyStableClass

private open class MyUnstableClass : MyStableClass() {
  var a: Any = listOf(1) // unstable maker

  companion object : MyUnstableClass() {
    val stableValuePropertyInCompanionObject = MyStableClass()

    @Stable var stableVariablePropertyInCompanionObject = MyStableClass()
      set(value) {
        field = MyStableClass()
      }
      get() = MyStableClass()
  }
}

@Composable fun ConstArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT constArgument")
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

    ExpressionArgument(currentMsCall(1))
  }
}

@Composable fun StaticVariableArgumentDemo() {
  val myStaticValue = "가나다라"
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT staticVariableArgument\n")
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

    ExpressionArgument(currentMsCall(myStaticValue))
  }
}

@Composable fun EnumEntryArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT enumEntryArgument\n")
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

    ExpressionArgument(currentMsCall(MyEnum.A))
  }
}

@Composable fun CompanionObjectArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT companionObjectArgument\n")
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

    ExpressionArgument(currentMsCall(MyUnstableClass.Companion))
  }
}

@Composable fun StableObjectArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT stableObjectArgument\n")
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

    ExpressionArgument(currentMsCall(StableObject))
  }
}

@Composable fun UnstableObjectArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT unstableObjectArgument\n")
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

    ExpressionArgument(currentMsCall(UnstableObject))
  }
}

@Composable fun StableValuePropertyArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT stableValuePropertyArgument\n")
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

    ExpressionArgument(currentMsCall(stableValueProperty))
  }
}

@Composable fun StableValueLocalPropertyArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }
  val stableValueProperty = MyStableClass()

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT StableValueLocalPropertyArgument\n")
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

    ExpressionArgument(currentMsCall(stableValueProperty))
  }
}

@Composable fun StableValueInCompanionObjectPropertyArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT\nstableValueInCompanionPropertyArgument\n")
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

    ExpressionArgument(currentMsCall(stableValuePropertyInCompanionObject))
  }
}

@Composable fun UnstableValueBoundsStableTypePropertyArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT\nunstableValueBoundsStableTypePropertyArgument\n")
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

    ExpressionArgument(currentMsCall(unstableValueBoundsStableTypeProperty))
  }
}

@Composable fun StableVariablePropertyArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT stableVariablePropertyArgument\n")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable {
          stableVariableProperty = MyStableClass()
          count++
        }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
      fontSize = 20.sp,
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    ExpressionArgument(currentMsCall(stableVariableProperty))
  }
}

@Composable fun StableVariableInCompanionObjectPropertyArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT\nstableVariableInCompanionPropertyArgument\n")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable {
          stableVariableProperty = MyStableClass()
          count++
        }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
      fontSize = 20.sp,
    )
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    ExpressionArgument(currentMsCall(stableVariablePropertyInCompanionObject))
  }
}

@Composable fun VariablePropertyArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT variablePropertyArgument\n")
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

    ExpressionArgument(currentMsCall(variableProperty))
  }
}

@Composable fun RememberStableVariableArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }
  val value = remember { MyStableClass() }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT rememberStableVariableArgument\n")
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

    ExpressionArgument(currentMsCall(value))
  }
}

@Composable fun RememberUnstableVariableArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }
  val value = remember { Any() }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT rememberUnstableVariableArgument\n")
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

    ExpressionArgument(currentMsCall(value))
  }
}

@Composable private fun ExpressionArgument(value: Any) {
  CurrentMsText("ExpressionArgument", customMs = value as Long)
}

@Stable private fun currentMsCall(value: Any): Long =
  currentTimeMillis() + value.hashCode()
