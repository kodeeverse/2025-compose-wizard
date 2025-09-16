package boo.kodeeverse.composemagic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import boo.kodeeverse.composemagic.restartability.DelegatingComposableDemo
import boo.kodeeverse.composemagic.restartability.FinalComposableDemo
import boo.kodeeverse.composemagic.restartability.FunctionalComposableDemo
import boo.kodeeverse.composemagic.restartability.InlineComposableDemo
import boo.kodeeverse.composemagic.restartability.LocalComposableDemo
import boo.kodeeverse.composemagic.restartability.NonInlineComposableDemo
import boo.kodeeverse.composemagic.restartability.OpenComposableDemo
import boo.kodeeverse.composemagic.restartability.ReturnValueComposableDemo

private enum class RestartabilityDemo(val content: @Composable () -> Unit) {
  DelegatingComposable(content = { DelegatingComposableDemo() }),
  FunctionalComposable(content = { FunctionalComposableDemo() }),
  InlineComposable(content = { InlineComposableDemo() }),
  NonInlineComposable(content = { NonInlineComposableDemo() }),
  LocalComposable(content = { LocalComposableDemo() }),
  ReturnValueComposable(content = { ReturnValueComposableDemo() }),
  OpenComposable(content = { OpenComposableDemo() }),
  FinalComposable(content = { FinalComposableDemo() }),
}

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge()
    super.onCreate(savedInstanceState)

    setContent {
      var restartabilityDemo by remember { mutableStateOf<RestartabilityDemo?>(null) }
      val scrollState = rememberLazyListState()

      BackHandler(restartabilityDemo != null) {
        restartabilityDemo = null
      }

      Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        when {
          restartabilityDemo == null -> {
            LazyColumn(
              modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding),
              state = scrollState,
              verticalArrangement = Arrangement.spacedBy(5.dp),
              horizontalAlignment = Alignment.CenterHorizontally,
            ) {
              stickyHeader {
                Text(
                  RestartabilityDemo::class.simpleName!!,
                  modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray)
                    .padding(vertical = 15.dp, horizontal = 30.dp),
                  fontSize = 20.sp,
                  fontWeight = FontWeight.Bold,
                )
              }

              items(RestartabilityDemo.entries) { demo ->
                Button(onClick = { restartabilityDemo = demo }) {
                  Text(demo.name)
                }
              }
            }
          }

          restartabilityDemo != null -> {
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
              restartabilityDemo!!.content()
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

