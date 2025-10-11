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
import boo.kodeeverse.composemagic.restartability.FinalComposableDemo
import boo.kodeeverse.composemagic.restartability.FunctionalComposableDemo
import boo.kodeeverse.composemagic.restartability.InlineComposableDemo
import boo.kodeeverse.composemagic.restartability.LocalComposableDemo
import boo.kodeeverse.composemagic.restartability.NonInlineComposableDemo
import boo.kodeeverse.composemagic.restartability.OpenButFinalComposableDemo
import boo.kodeeverse.composemagic.restartability.OpenComposableDemo
import boo.kodeeverse.composemagic.restartability.ReturnValueComposableDemo
import boo.kodeeverse.composemagic.stability.EmptyListCallDemo
import boo.kodeeverse.composemagic.stability.EmptyMapCallDemo
import boo.kodeeverse.composemagic.stability.EmptySetCallDemo
import boo.kodeeverse.composemagic.stability.GivenImmutableClassViaArgumentDemo
import boo.kodeeverse.composemagic.stability.GivenImmutableClassViaPropDemo
import boo.kodeeverse.composemagic.stability.GivenStableAndAlwaysSameClassDemo
import boo.kodeeverse.composemagic.stability.GivenStableClassViaArgumentDemo
import boo.kodeeverse.composemagic.stability.GivenStableClassViaPropDemo
import boo.kodeeverse.composemagic.stability.GivenUnstableAndAlwaysSameClassDemo
import boo.kodeeverse.composemagic.stability.ListOfCallDemo
import boo.kodeeverse.composemagic.stability.MapOfCallDemo
import boo.kodeeverse.composemagic.stability.NewInstanceAndPersistentUnstableParameterDemo
import boo.kodeeverse.composemagic.stability.PairOfCallDemo
import boo.kodeeverse.composemagic.stability.SameInstanceAndMutableUnstableParameterDemo
import boo.kodeeverse.composemagic.stability.SetOfCallDemo
import boo.kodeeverse.composemagic.stability.StableCallArgumentDemo
import boo.kodeeverse.composemagic.stability.StableParameterButUnstableArgumentDemo
import boo.kodeeverse.composemagic.stability.StableParameterDemo
import boo.kodeeverse.composemagic.stability.UnstableCallArgumentDemo
import boo.kodeeverse.composemagic.stability.UnstableParameterButStableArgumentDemo

private enum class Demo(val category: String, val content: @Composable () -> Unit) {
  InlineLambdaComposable(category = "restartability - composableLambda", content = { InlineLambdaComposableDemo() }),
  NoInlineLambdaComposable(category = "restartability - composableLambda", content = { NoInlineLambdaComposableDemo() }),
  NonInlineLambdaComposable(category = "restartability - composableLambda", content = { NonInlineLambdaComposableDemo() }),

  // DelegatingComposable(category = "restartability", content = { DelegatingComposableDemo() }),
  FunctionalComposable(category = "restartability", content = { FunctionalComposableDemo() }),
  InlineComposable(category = "restartability", content = { InlineComposableDemo() }),
  NonInlineComposable(category = "restartability", content = { NonInlineComposableDemo() }),
  LocalComposable(category = "restartability", content = { LocalComposableDemo() }),
  ReturnValueComposable(category = "restartability", content = { ReturnValueComposableDemo() }),
  OpenComposable(category = "restartability", content = { OpenComposableDemo() }),
  OpenButFinalComposable(category = "restartability", content = { OpenButFinalComposableDemo() }),
  FinalComposable(category = "restartability", content = { FinalComposableDemo() }),

  StableCallArgument(category = "stability - StableCallArguments", content = { StableCallArgumentDemo() }),
  UnstableCallArgument(category = "stability - StableCallArguments", content = { UnstableCallArgumentDemo() }),

  StableParameter(category = "stability - StableParameters", content = { StableParameterDemo() }),
  SameInstanceAndMutableUnstableParameter(category = "stability - StableParameters", content = { SameInstanceAndMutableUnstableParameterDemo() }),
  NewInstanceAndPersistentUnstableParameter(category = "stability - StableParameters", content = { NewInstanceAndPersistentUnstableParameterDemo() }),
  StableParameterButUnstableArgument(category = "stability - StableParameters", content = { StableParameterButUnstableArgumentDemo() }),
  UnstableParameterButStableArgument(category = "stability - StableParameters", content = { UnstableParameterButStableArgumentDemo() }),

  GivenUnstableAndAlwaysSameClass(category = "stability - ConstructorCalls", content = { GivenUnstableAndAlwaysSameClassDemo() }),
  GivenStableAndAlwaysSameClass(category = "stability - ConstructorCalls", content = { GivenStableAndAlwaysSameClassDemo() }),
  GivenStableClassViaArgument(category = "stability - ConstructorCalls", content = { GivenStableClassViaArgumentDemo() }),
  GivenStableClassViaProp(category = "stability - ConstructorCalls", content = { GivenStableClassViaPropDemo() }),
  GivenImmutableClassViaArgument(category = "stability - ConstructorCalls", content = { GivenImmutableClassViaArgumentDemo() }),
  GivenImmutableClassViaProp(category = "stability - ConstructorCalls", content = { GivenImmutableClassViaPropDemo() }),

  EmptyListCall(category = "stability - KnownStableCallArguments", content = { EmptyListCallDemo() }),
  ListOfCall(category = "stability - KnownStableCallArguments", content = { ListOfCallDemo() }),
  EmptyMapCall(category = "stability - KnownStableCallArguments", content = { EmptyMapCallDemo() }),
  MapOfCall(category = "stability - KnownStableCallArguments", content = { MapOfCallDemo() }),
  EmptySetCall(category = "stability - KnownStableCallArguments", content = { EmptySetCallDemo() }),
  SetOfCall(category = "stability - KnownStableCallArguments", content = { SetOfCallDemo() }),
  PairOfCall(category = "stability - KnownStableCallArguments", content = { PairOfCallDemo() });

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

val currentRecomposeScopeHash: String
  @Composable @ReadOnlyComposable
  inline get() = currentRecomposeScope.toString().substringAfterLast('@')
