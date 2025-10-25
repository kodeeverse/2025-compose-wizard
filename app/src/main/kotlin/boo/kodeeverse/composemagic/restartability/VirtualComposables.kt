@file:Suppress("RedundantModalityModifier")

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

private interface IVirtualComposable {
  @Composable fun Content()
}

private abstract class AbstractVirtualComposable {
  @Composable abstract fun Content()
}

private open class OpenVirtualComposable {
  @Composable open fun Content() {}
}

@Composable fun IFinalComposableDemo() {
  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT")
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    remember { IFinalComposable() as IVirtualComposable }.Content()
  }
}

@Composable fun IOpenComposableDemo() {
  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT")
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    remember { IOpenComposable() as IVirtualComposable }.Content()
  }
}

@Composable fun AbstractFinalComposableDemo() {
  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT")
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    remember { AbstractFinalComposable() }.Content()
  }
}

@Composable fun AbstractOpenComposableDemo() {
  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT")
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    remember { AbstractOpenComposable() }.Content()
  }
}

@Composable fun OpenFinalComposableDemo() {
  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT")
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    remember { OpenFinalComposable() }.Content()
  }
}

@Composable fun OpenOpenComposableDemo() {
  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CurrentMsText("ROOT")
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    remember { OpenOpenComposable() }.Content()
  }
}

private class IFinalComposable : IVirtualComposable {
  @Composable final override fun Content() {
    var count by remember { mutableIntStateOf(0) }

    CurrentMsText("IFinalContent")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
      fontSize = 20.sp,
    )
  }
}

private open class IOpenComposable : IVirtualComposable {
  @Composable open override fun Content() {
    var count by remember { mutableIntStateOf(0) }

    CurrentMsText("IOpenContent")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
      fontSize = 20.sp,
    )
  }
}

private class AbstractFinalComposable : AbstractVirtualComposable() {
  @Composable final override fun Content() {
    var count by remember { mutableIntStateOf(0) }

    CurrentMsText("AbstractFinalContent")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
      fontSize = 20.sp,
    )
  }
}

private open class AbstractOpenComposable : AbstractVirtualComposable() {
  @Composable open override fun Content() {
    var count by remember { mutableIntStateOf(0) }

    CurrentMsText("AbstractOpenContent")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
      fontSize = 20.sp,
    )
  }
}

private class OpenFinalComposable : OpenVirtualComposable() {
  @Composable final override fun Content() {
    var count by remember { mutableIntStateOf(0) }

    CurrentMsText("OpenFinalContent")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
      fontSize = 20.sp,
    )
  }
}

private open class OpenOpenComposable : OpenVirtualComposable() {
  @Composable open override fun Content() {
    var count by remember { mutableIntStateOf(0) }

    CurrentMsText("OpenOpenContent")
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
      fontSize = 20.sp,
    )
  }
}
