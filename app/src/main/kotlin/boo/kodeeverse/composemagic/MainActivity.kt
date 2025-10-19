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
import boo.kodeeverse.composemagic.restartability.AbstractFinalComposableDemo
import boo.kodeeverse.composemagic.restartability.AbstractOpenComposableDemo
import boo.kodeeverse.composemagic.restartability.IFinalComposableDemo
import boo.kodeeverse.composemagic.restartability.IOpenComposableDemo
import boo.kodeeverse.composemagic.restartability.InlineComposableDemo
import boo.kodeeverse.composemagic.restartability.LocalComposableDemo
import boo.kodeeverse.composemagic.restartability.LocalFunctionalComposableDemo
import boo.kodeeverse.composemagic.restartability.NonInlineComposableDemo
import boo.kodeeverse.composemagic.restartability.OpenFinalComposableDemo
import boo.kodeeverse.composemagic.restartability.OpenOpenComposableDemo
import boo.kodeeverse.composemagic.restartability.ReturnValueComposableDemo
import boo.kodeeverse.composemagic.stability.CompanionObjectArgumentDemo
import boo.kodeeverse.composemagic.stability.ConstArgumentDemo
import boo.kodeeverse.composemagic.stability.EmptyListCallDemo
import boo.kodeeverse.composemagic.stability.EmptyMapCallDemo
import boo.kodeeverse.composemagic.stability.EmptySetCallDemo
import boo.kodeeverse.composemagic.stability.EnumEntryArgumentDemo
import boo.kodeeverse.composemagic.stability.ImmutableButNonStaticArgumentClassArgumentDemo
import boo.kodeeverse.composemagic.stability.ImmutableClassArgumentDemo
import boo.kodeeverse.composemagic.stability.ImmutableClassParameterIntoArgumentDemo
import boo.kodeeverse.composemagic.stability.ImmutableClassPropertyIntoArgumentDemo
import boo.kodeeverse.composemagic.stability.ListOfCallDemo
import boo.kodeeverse.composemagic.stability.MapOfCallDemo
import boo.kodeeverse.composemagic.stability.MutableButSameInstanceArgumentInUnstableParameterDemo
import boo.kodeeverse.composemagic.stability.PairOfCallDemo
import boo.kodeeverse.composemagic.stability.SetOfCallDemo
import boo.kodeeverse.composemagic.stability.StableArgumentInStableParameterDemo
import boo.kodeeverse.composemagic.stability.StableArgumentInUnstableParameterDemo
import boo.kodeeverse.composemagic.stability.StableBoxingClassArgumentDemo
import boo.kodeeverse.composemagic.stability.StableCallArgumentDemo
import boo.kodeeverse.composemagic.stability.StableCallWithUnstableTypeArgumentDemo
import boo.kodeeverse.composemagic.stability.StableClassArgumentDemo
import boo.kodeeverse.composemagic.stability.StableClassParameterIntoArgumentDemo
import boo.kodeeverse.composemagic.stability.StableClassPropertyIntoArgumentDemo
import boo.kodeeverse.composemagic.stability.StableObjectArgumentDemo
import boo.kodeeverse.composemagic.stability.StableValuePropertyArgumentDemo
import boo.kodeeverse.composemagic.stability.StableVariablePropertyArgumentDemo
import boo.kodeeverse.composemagic.stability.StaticVariableArgumentDemo
import boo.kodeeverse.composemagic.stability.UnstableArgumentInStableParameterDemo
import boo.kodeeverse.composemagic.stability.UnstableBoxingClassArgumentDemo
import boo.kodeeverse.composemagic.stability.UnstableCallArgumentDemo
import boo.kodeeverse.composemagic.stability.UnstableObjectArgumentDemo
import boo.kodeeverse.composemagic.stability.UntableClassArgumentDemo
import boo.kodeeverse.composemagic.stability.VariablePropertyArgumentDemo

private enum class Demo(val category: String, val content: @Composable () -> Unit) {
  InlineLambdaComposable(category = "restartability - composableLambda", content = { InlineLambdaComposableDemo() }),
  NoInlineLambdaComposable(category = "restartability - composableLambda", content = { NoInlineLambdaComposableDemo() }),
  NonInlineLambdaComposable(category = "restartability - composableLambda", content = { NonInlineLambdaComposableDemo() }),

  // DelegatingComposable(category = "restartability", content = { DelegatingComposableDemo() }),
  InlineComposable(category = "restartability", content = { InlineComposableDemo() }),
  NonInlineComposable(category = "restartability", content = { NonInlineComposableDemo() }),
  LocalComposable(category = "restartability", content = { LocalComposableDemo() }),
  LocalFunctionalComposable(category = "restartability", content = { LocalFunctionalComposableDemo() }),
  ReturnValueComposable(category = "restartability", content = { ReturnValueComposableDemo() }),
  IFinalComposable(category = "restartability", content = { IFinalComposableDemo() }),
  IOpenComposable(category = "restartability", content = { IOpenComposableDemo() }),
  AbstractFinalComposable(category = "restartability", content = { AbstractFinalComposableDemo() }),
  AbstractOpenComposable(category = "restartability", content = { AbstractOpenComposableDemo() }),
  OpenFinalComposable(category = "restartability", content = { OpenFinalComposableDemo() }),
  OpenOpenComposable(category = "restartability", content = { OpenOpenComposableDemo() }),

  StableCallArgument(category = "stability - StableCallArguments", content = { StableCallArgumentDemo() }),
  StableCallWithUnstableTypeArgument(category = "stability - StableCallArguments", content = { StableCallWithUnstableTypeArgumentDemo() }),
  UnstableCallArgument(category = "stability - StableCallArguments", content = { UnstableCallArgumentDemo() }),

  ConstArgument(category = "stability - StaticExpressionArguments", content = { ConstArgumentDemo() }),
  EnumEntryArgument(category = "stability - StaticExpressionArguments", content = { EnumEntryArgumentDemo() }),
  CompanionObjectArgument(category = "stability - StaticExpressionArguments", content = { CompanionObjectArgumentDemo() }),
  StableObjectArgument(category = "stability - StaticExpressionArguments", content = { StableObjectArgumentDemo() }),
  UnstableObjectArgument(category = "stability - StaticExpressionArguments", content = { UnstableObjectArgumentDemo() }),
  StableValuePropertyArgument(category = "stability - StaticExpressionArguments", content = { StableValuePropertyArgumentDemo() }),
  StableVariablePropertyArgument(category = "stability - StaticExpressionArguments", content = { StableVariablePropertyArgumentDemo() }),
  VariablePropertyArgument(category = "stability - StaticExpressionArguments", content = { VariablePropertyArgumentDemo() }),
  StaticVariableArgument(category = "stability - StaticExpressionArguments", content = { StaticVariableArgumentDemo() }),

  MutableButSameInstanceArgumentInUnstableParameter(category = "stability - StableParameters", content = { MutableButSameInstanceArgumentInUnstableParameterDemo() }),
  StableArgumentInUnstableParameter(category = "stability - StableParameters", content = { StableArgumentInUnstableParameterDemo() }),
  StableArgumentInStableParameter(category = "stability - StableParameters", content = { StableArgumentInStableParameterDemo() }),
  UnstableArgumentInStableParameter(category = "stability - StableParameters", content = { UnstableArgumentInStableParameterDemo() }),

  UntableClassArgument(category = "stability - ConstructorCalls", content = { UntableClassArgumentDemo() }),
  StableClassArgument(category = "stability - ConstructorCalls", content = { StableClassArgumentDemo() }),
  ImmutableClassArgument(category = "stability - ConstructorCalls", content = { ImmutableClassArgumentDemo() }),
  StableClassParameterIntoArgument(category = "stability - ConstructorCalls", content = { StableClassParameterIntoArgumentDemo() }),
  StableClassPropertyIntoArgument(category = "stability - ConstructorCalls", content = { StableClassPropertyIntoArgumentDemo() }),
  ImmutableClassParameterIntoArgument(category = "stability - ConstructorCalls", content = { ImmutableClassParameterIntoArgumentDemo() }),
  ImmutableClassPropertyIntoArgument(category = "stability - ConstructorCalls", content = { ImmutableClassPropertyIntoArgumentDemo() }),
  StableBoxingClassArgument(category = "stability - ConstructorCalls", content = { StableBoxingClassArgumentDemo() }),
  UnstableBoxingClassArgument(category = "stability - ConstructorCalls", content = { UnstableBoxingClassArgumentDemo() }),
  ImmutableButNonStaticArgumentClassArgument(category = "stability - ConstructorCalls", content = { ImmutableButNonStaticArgumentClassArgumentDemo() }),

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
