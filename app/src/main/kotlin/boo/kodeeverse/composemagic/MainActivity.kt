package boo.kodeeverse.composemagic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import boo.kodeeverse.composemagic.realworld.ColorFilterCallArgumentDemo
import boo.kodeeverse.composemagic.realworld.ColorFilterConstructorArgumentDemo
import boo.kodeeverse.composemagic.restartability.AbstractFinalComposableDemo
import boo.kodeeverse.composemagic.restartability.AbstractOpenComposableDemo
import boo.kodeeverse.composemagic.restartability.IFinalComposableDemo
import boo.kodeeverse.composemagic.restartability.IOpenComposableDemo
import boo.kodeeverse.composemagic.restartability.InlineComposableDemo
import boo.kodeeverse.composemagic.restartability.InlineLambdaComposableDemo
import boo.kodeeverse.composemagic.restartability.LocalComposableDemo
import boo.kodeeverse.composemagic.restartability.LocalFunctionalComposableDemo
import boo.kodeeverse.composemagic.restartability.NoInlineLambdaComposableDemo
import boo.kodeeverse.composemagic.restartability.NonInlineComposableDemo
import boo.kodeeverse.composemagic.restartability.NonInlineLambdaComposableDemo
import boo.kodeeverse.composemagic.restartability.OpenFinalComposableDemo
import boo.kodeeverse.composemagic.restartability.OpenOpenComposableDemo
import boo.kodeeverse.composemagic.restartability.ReturnValueComposableDemo
import boo.kodeeverse.composemagic.stability.CompanionObjectArgumentDemo
import boo.kodeeverse.composemagic.stability.ConstArgumentDemo
import boo.kodeeverse.composemagic.stability.EmptyListCallDemo
import boo.kodeeverse.composemagic.stability.EmptyMapCallDemo
import boo.kodeeverse.composemagic.stability.EmptySetCallDemo
import boo.kodeeverse.composemagic.stability.EnumEntryArgumentDemo
import boo.kodeeverse.composemagic.stability.ImmutableClassArgumentDemo
import boo.kodeeverse.composemagic.stability.ImmutableClassParameterIntoArgumentDemo
import boo.kodeeverse.composemagic.stability.ImmutableClassPropertyIntoArgumentDemo
import boo.kodeeverse.composemagic.stability.ImmutableWithNonStaticArgumentClassArgumentDemo
import boo.kodeeverse.composemagic.stability.ImmutableWithStaticArgumentClassArgumentDemo
import boo.kodeeverse.composemagic.stability.ListOfCallDemo
import boo.kodeeverse.composemagic.stability.MapOfCallDemo
import boo.kodeeverse.composemagic.stability.PairOfCallDemo
import boo.kodeeverse.composemagic.stability.RememberStableVariableArgumentDemo
import boo.kodeeverse.composemagic.stability.RememberUnstableVariableArgumentDemo
import boo.kodeeverse.composemagic.stability.SetOfCallDemo
import boo.kodeeverse.composemagic.stability.StableBoxingClassArgumentDemo
import boo.kodeeverse.composemagic.stability.StableCallArgumentDemo
import boo.kodeeverse.composemagic.stability.StableCallWithUnstableTypeArgumentDemo
import boo.kodeeverse.composemagic.stability.StableClassArgumentDemo
import boo.kodeeverse.composemagic.stability.StableClassParameterIntoArgumentDemo
import boo.kodeeverse.composemagic.stability.StableClassPropertyIntoArgumentDemo
import boo.kodeeverse.composemagic.stability.StableObjectArgumentDemo
import boo.kodeeverse.composemagic.stability.StableValueInCompanionObjectPropertyArgumentDemo
import boo.kodeeverse.composemagic.stability.StableValueLocalPropertyArgumentDemo
import boo.kodeeverse.composemagic.stability.StableValuePropertyArgumentDemo
import boo.kodeeverse.composemagic.stability.StableVariableInCompanionObjectPropertyArgumentDemo
import boo.kodeeverse.composemagic.stability.StableVariablePropertyArgumentDemo
import boo.kodeeverse.composemagic.stability.StaticVariableArgumentDemo
import boo.kodeeverse.composemagic.stability.UnstableBoxingClassArgumentDemo
import boo.kodeeverse.composemagic.stability.UnstableCallArgumentDemo
import boo.kodeeverse.composemagic.stability.UnstableClassArgumentDemo
import boo.kodeeverse.composemagic.stability.UnstableObjectArgumentDemo
import boo.kodeeverse.composemagic.stability.UnstableValueBoundsStableTypePropertyArgumentDemo
import boo.kodeeverse.composemagic.stability.VariablePropertyArgumentDemo
import java.lang.System.currentTimeMillis

private enum class Demo(val category: String, val content: @Composable () -> Unit) {
  // DelegatingComposable(category = "restartability", content = { DelegatingComposableDemo() }),
  InlineLambdaComposable(category = "restartability - lambda", content = { InlineLambdaComposableDemo() }),
  NoInlineLambdaComposable(category = "restartability - lambda", content = { NoInlineLambdaComposableDemo() }),
  NonInlineLambdaComposable(category = "restartability - lambda", content = { NonInlineLambdaComposableDemo() }),

  InlineComposable(category = "restartability - inline", content = { InlineComposableDemo() }),
  NonInlineComposable(category = "restartability - inline", content = { NonInlineComposableDemo() }),

  IFinalComposable(category = "restartability - virtual", content = { IFinalComposableDemo() }),
  IOpenComposable(category = "restartability - virtual", content = { IOpenComposableDemo() }),
  AbstractFinalComposable(category = "restartability - virtual", content = { AbstractFinalComposableDemo() }),
  AbstractOpenComposable(category = "restartability - virtual", content = { AbstractOpenComposableDemo() }),
  OpenFinalComposable(category = "restartability - virtual", content = { OpenFinalComposableDemo() }),
  OpenOpenComposable(category = "restartability - virtual", content = { OpenOpenComposableDemo() }),

  LocalComposable(category = "restartability", content = { LocalComposableDemo() }),
  LocalFunctionalComposable(category = "restartability", content = { LocalFunctionalComposableDemo() }),
  ReturnValueComposable(category = "restartability", content = { ReturnValueComposableDemo() }),

  StableCallArgument(category = "stability - CallArguments", content = { StableCallArgumentDemo() }),
  StableCallWithUnstableTypeArgument(category = "stability - CallArguments", content = { StableCallWithUnstableTypeArgumentDemo() }),
  UnstableCallArgument(category = "stability - CallArguments", content = { UnstableCallArgumentDemo() }),

  ConstArgument(category = "stability - ExpressionArguments", content = { ConstArgumentDemo() }),
  StaticVariableArgument(category = "stability - ExpressionArguments", content = { StaticVariableArgumentDemo() }),
  EnumEntryArgument(category = "stability - ExpressionArguments", content = { EnumEntryArgumentDemo() }),
  CompanionObjectArgument(category = "stability - ExpressionArguments", content = { CompanionObjectArgumentDemo() }),
  StableObjectArgument(category = "stability - ExpressionArguments", content = { StableObjectArgumentDemo() }),
  UnstableObjectArgument(category = "stability - ExpressionArguments", content = { UnstableObjectArgumentDemo() }),
  StableValuePropertyArgument(category = "stability - ExpressionArguments", content = { StableValuePropertyArgumentDemo() }),
  StableValueLocalPropertyArgument(category = "stability - ExpressionArguments", content = { StableValueLocalPropertyArgumentDemo() }),
  StableValueInCompanionObjectPropertyArgument(category = "stability - ExpressionArguments", content = { StableValueInCompanionObjectPropertyArgumentDemo() }),
  UnstableValueBoundsStableTypePropertyArgument(category = "stability - ExpressionArguments", content = { UnstableValueBoundsStableTypePropertyArgumentDemo() }),
  StableVariablePropertyArgument(category = "stability - ExpressionArguments", content = { StableVariablePropertyArgumentDemo() }),
  StableVariableInCompanionObjectPropertyArgument(category = "stability - ExpressionArguments", content = { StableVariableInCompanionObjectPropertyArgumentDemo() }),
  VariablePropertyArgument(category = "stability - ExpressionArguments", content = { VariablePropertyArgumentDemo() }),
  RememberStableVariableArgument(category = "stability - ExpressionArguments", content = { RememberStableVariableArgumentDemo() }),
  RememberUnstableVariableArgument(category = "stability - ExpressionArguments", content = { RememberUnstableVariableArgumentDemo() }),

  UnstableClassArgument(category = "stability - ConstructorArguments", content = { UnstableClassArgumentDemo() }),
  StableClassArgument(category = "stability - ConstructorArguments", content = { StableClassArgumentDemo() }),
  ImmutableClassArgument(category = "stability - ConstructorArguments", content = { ImmutableClassArgumentDemo() }),
  ImmutableWithStaticArgumentClassArgument(category = "stability - ConstructorArguments", content = { ImmutableWithStaticArgumentClassArgumentDemo() }),
  ImmutableWithNonStaticArgumentClassArgument(category = "stability - ConstructorArguments", content = { ImmutableWithNonStaticArgumentClassArgumentDemo() }),
  StableClassParameterIntoArgument(category = "stability - ConstructorArguments", content = { StableClassParameterIntoArgumentDemo() }),
  StableClassPropertyIntoArgument(category = "stability - ConstructorArguments", content = { StableClassPropertyIntoArgumentDemo() }),
  ImmutableClassParameterIntoArgument(category = "stability - ConstructorArguments", content = { ImmutableClassParameterIntoArgumentDemo() }),
  ImmutableClassPropertyIntoArgument(category = "stability - ConstructorArguments", content = { ImmutableClassPropertyIntoArgumentDemo() }),
  StableBoxingClassArgument(category = "stability - ConstructorArguments", content = { StableBoxingClassArgumentDemo() }),
  UnstableBoxingClassArgument(category = "stability - ConstructorArguments", content = { UnstableBoxingClassArgumentDemo() }),

  EmptyListCall(category = "stability - KnownStableCallArguments", content = { EmptyListCallDemo() }),
  ListOfCall(category = "stability - KnownStableCallArguments", content = { ListOfCallDemo() }),
  EmptyMapCall(category = "stability - KnownStableCallArguments", content = { EmptyMapCallDemo() }),
  MapOfCall(category = "stability - KnownStableCallArguments", content = { MapOfCallDemo() }),
  EmptySetCall(category = "stability - KnownStableCallArguments", content = { EmptySetCallDemo() }),
  SetOfCall(category = "stability - KnownStableCallArguments", content = { SetOfCallDemo() }),
  PairOfCall(category = "stability - KnownStableCallArguments", content = { PairOfCallDemo() }),

  ColorFilterCallArgument(category = "stability - RealWorldSample", content = { ColorFilterCallArgumentDemo() }),
  ColorFilterConstructorArgument(category = "stability - RealWorldSample", content = { ColorFilterConstructorArgumentDemo() });

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

      when {
        demo == null -> {
          LazyColumn(
            modifier = Modifier
              .fillMaxSize()
              .background(color = Color.Gray)
              .statusBarsPadding()
              .background(color = Color.White),
            state = scrollState,
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

              item {
                Spacer(Modifier.height(20.dp))
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

            item {
              Spacer(Modifier.height(20.dp))
            }
          }
        }

        demo != null -> {
          Box(
            modifier = Modifier
              .fillMaxSize()
              .background(color = Color.White)
              .systemBarsPadding(),
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

val currentRecomposeScopeHash: String
  @Composable @ReadOnlyComposable
  inline get() = currentRecomposeScope.toString().substringAfterLast('@')

@Suppress("NOTHING_TO_INLINE")
@Composable inline fun CurrentMsText(
  text: String,
  modifier: Modifier = Modifier,
  textStyle: TextStyle = remember { TextStyle.Default.copy(fontSize = 20.sp) },
  customMs: Long? = null,
) {
  var msPath: Path? by remember { mutableStateOf(null) }

  Text(
    modifier = modifier
      .padding(horizontal = 5.dp)
      .drawBehind {
        val msPath = msPath ?: return@drawBehind
        drawPath(
          path = msPath,
          color = Color.Red,
          style = Stroke(width = 2.dp.toPx()),
        )
      },
    text = "$text @ ${customMs ?: currentTimeMillis()}",
    style = textStyle,
    textAlign = TextAlign.Center,
    onTextLayout = { layout ->
      msPath = layout.getPathForRange(
        start = layout.layoutInput.text.lastIndexOf('@') + 2,
        end = layout.layoutInput.text.length,
      )
    },
  )
}
