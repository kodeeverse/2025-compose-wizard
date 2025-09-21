package boo.kodeeverse.composemagic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import boo.kodeeverse.composemagic.composableLambda.InlineLambdaComposableDemo
import boo.kodeeverse.composemagic.composableLambda.NoInlineLambdaComposableDemo
import boo.kodeeverse.composemagic.composableLambda.NonInlineLambdaComposableDemo
import boo.kodeeverse.composemagic.restartability.DelegatingComposableDemo
import boo.kodeeverse.composemagic.restartability.FinalComposableDemo
import boo.kodeeverse.composemagic.restartability.FunctionalComposableDemo
import boo.kodeeverse.composemagic.restartability.InlineComposableDemo
import boo.kodeeverse.composemagic.restartability.LocalComposableDemo
import boo.kodeeverse.composemagic.restartability.NonInlineComposableDemo
import boo.kodeeverse.composemagic.restartability.OpenComposableDemo
import boo.kodeeverse.composemagic.restartability.ReturnValueComposableDemo
import boo.kodeeverse.composemagic.stableCall.GivenImmutableValueViaArgumentDemo
import boo.kodeeverse.composemagic.stableCall.GivenImmutableValueViaPropDemo
import boo.kodeeverse.composemagic.stableCall.GivenStableValueViaArgumentDemo
import boo.kodeeverse.composemagic.stableCall.GivenStableValueViaPropDemo
import boo.kodeeverse.composemagic.stableCall.StableAndAlwaysSameCallDemo
import boo.kodeeverse.composemagic.stableCall.UnstableAndAlwaysSameCallDemo

private enum class Demo(val category: String, val content: @Composable () -> Unit) {
  InlineLambdaComposable(category = "composableLambda", content = { InlineLambdaComposableDemo() }),
  NoInlineLambdaComposable(category = "composableLambda", content = { NoInlineLambdaComposableDemo() }),
  NonInlineLambdaComposable(category = "composableLambda", content = { NonInlineLambdaComposableDemo() }),

  UnstableButAlwaysSameCall(category = "stableCall", content = { UnstableAndAlwaysSameCallDemo() }),
  StableAndAlwaysSameCall(category = "stableCall", content = { StableAndAlwaysSameCallDemo() }),
  GivenStableValueViaArgument(category = "stableCall", content = { GivenStableValueViaArgumentDemo() }),
  GivenStableValueViaProp(category = "stableCall", content = { GivenStableValueViaPropDemo() }),
  GivenImmutableValueViaArgument(category = "stableCall", content = { GivenImmutableValueViaArgumentDemo() }),
  GivenImmutableValueViaProp(category = "stableCall", content = { GivenImmutableValueViaPropDemo() }),

  DelegatingComposable(category = "restartability", content = { DelegatingComposableDemo() }),
  FunctionalComposable(category = "restartability", content = { FunctionalComposableDemo() }),
  InlineComposable(category = "restartability", content = { InlineComposableDemo() }),
  NonInlineComposable(category = "restartability", content = { NonInlineComposableDemo() }),
  LocalComposable(category = "restartability", content = { LocalComposableDemo() }),
  ReturnValueComposable(category = "restartability", content = { ReturnValueComposableDemo() }),
  OpenComposable(category = "restartability", content = { OpenComposableDemo() }),
  FinalComposable(category = "restartability", content = { FinalComposableDemo() });

  companion object {
    val Groups: Map<String, List<Demo>> = entries.groupBy(Demo::category)
  }
}

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge()
    super.onCreate(savedInstanceState)

    setContent {
      var demo by remember { mutableStateOf<Demo?>(null) }
      val scrollState = rememberLazyListState()

      BackHandler(demo != null) {
        demo = null
      }

      Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        when {
          demo == null -> {
            LazyColumn(
              modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding),
              state = scrollState,
              verticalArrangement = Arrangement.spacedBy(5.dp),
              horizontalAlignment = Alignment.CenterHorizontally,
            ) {
              for ((category, demos) in Demo.Groups) {
                stickyHeader {
                  Text(
                    category,
                    modifier = Modifier
                      .fillMaxWidth()
                      .background(color = Color.Gray)
                      .padding(vertical = 15.dp, horizontal = 30.dp),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                  )
                }

                items(demos) { entry ->
                  Button(onClick = { demo = entry }) {
                    Text(entry.name)
                  }
                }

                item {
                  Spacer(Modifier.height(30.dp))
                }
              }
            }
          }

          demo != null -> {
            Box(
              modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding),
              contentAlignment = { size: IntSize, space: IntSize, _: LayoutDirection ->
                val regionHeight = space.height / 3
                val x = (space.width - size.width) / 2
                val y = (regionHeight - size.height) / 2
                IntOffset(x, y)
              },
            ) {
              demo!!.content()
            }
          }
        }
      }
    }
  }
}

val currentRecomposeScopeLabel: String
  @Composable @ReadOnlyComposable
  inline get() = currentRecomposeScope.toString().substringAfterLast('@')
