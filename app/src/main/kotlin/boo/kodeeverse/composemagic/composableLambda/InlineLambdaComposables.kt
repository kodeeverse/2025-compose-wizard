@file:Suppress("NOTHING_TO_INLINE")

package boo.kodeeverse.composemagic.composableLambda

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
import boo.kodeeverse.composemagic.currentRecomposeScopeHash
import java.lang.System.currentTimeMillis

@Composable fun InlineLambdaComposableDemo() {
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

    InlineLambdaComposable {
      Text(
        "InlineLambdaComposable @ $currentRecomposeScopeHash (${currentTimeMillis()})",
        fontWeight = FontWeight.Bold,
      )
    }
  }
}

@Composable fun NoInlineLambdaComposableDemo() {
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

    NoInlineLambdaComposable {
      Text(
        "NoInlineLambdaComposable @ $currentRecomposeScopeHash (${currentTimeMillis()})",
        fontWeight = FontWeight.Bold,
      )
    }
  }
}

@Composable fun NonInlineLambdaComposableDemo() {
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

    NonInlineLambdaComposable {
      Text(
        "NonInlineLambdaComposable @ $currentRecomposeScopeHash (${currentTimeMillis()})",
        fontWeight = FontWeight.Bold,
      )
    }
  }
}

@Composable private inline fun InlineLambdaComposable(content: @Composable () -> Unit) {
  content()
}

@Composable private inline fun NoInlineLambdaComposable(noinline content: @Composable () -> Unit) {
  content()
}

@Composable private fun NonInlineLambdaComposable(content: @Composable () -> Unit) {
  content()
}
