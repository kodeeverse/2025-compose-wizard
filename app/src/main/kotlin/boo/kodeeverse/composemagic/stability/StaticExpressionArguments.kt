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

private val topLevelStableObject = MyStableClass()

private enum class MyEnum {
  A,
}

@Stable private class MyStableClass

private open class MyRegularClass {
  var a: Any = listOf(1) // unstable maker

  companion object : MyRegularClass()
}

@Composable fun StaticArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      "ROOT constArgument @ $currentRecomposeScopeHash\n" +
        "(${currentTimeMillis()})",
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

    StaticExpressionArgument(constArgumentCall(1))
  }
}

@Composable fun EnumEntryArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      "ROOT enumEntryArgument @ $currentRecomposeScopeHash\n" +
        "(${currentTimeMillis()})",
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

    StaticExpressionArgument(enumEntryArgumentCall(MyEnum.A))
  }
}

@Composable fun CompanionObjectArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      "ROOT companionObjectArgument @ $currentRecomposeScopeHash\n" +
        "(${currentTimeMillis()})",
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

    StaticExpressionArgument(companionObjectArgumentCall(MyRegularClass.Companion))
  }
}

@Composable fun TopLevelStableObjectArgumentDemo() {
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      "ROOT topLevelStableObjectArgument @ $currentRecomposeScopeHash\n" +
        "(${currentTimeMillis()})",
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

    StaticExpressionArgument(topLevelStableObjectArgumentCall(topLevelStableObject))
  }
}

@Composable fun StaticVariableArgumentDemo() {
  val myStaticValue = 1234
  var count by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      "ROOT staticVariableArgument @ $currentRecomposeScopeHash\n" +
        "(${currentTimeMillis()})",
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

    StaticExpressionArgument(staticVariableArgumentCall(myStaticValue))
  }
}

@Composable private fun StaticExpressionArgument(value: Long) {
  Text(
    "StaticExpressionArgument @ $currentRecomposeScopeHash\n($value)",
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center,
  )
}

@Stable private fun constArgumentCall(value: Int): Long =
  currentTimeMillis() + value

@Stable private fun enumEntryArgumentCall(value: MyEnum): Long =
  currentTimeMillis() + value.ordinal

@Stable private fun companionObjectArgumentCall(value: MyRegularClass): Long =
  currentTimeMillis() + value.hashCode()

@Stable private fun topLevelStableObjectArgumentCall(value: MyStableClass): Long =
  currentTimeMillis() + value.hashCode()

@Stable private fun staticVariableArgumentCall(value: Any): Long =
  currentTimeMillis() + value.hashCode()
