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
import androidx.compose.runtime.Immutable
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
import kotlin.reflect.KProperty

@Composable fun DelegatingComposableDemo() {
  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text("ROOT @ $currentRecomposeScopeHash (${currentTimeMillis()})")
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    val delegatingContent by DelegatingComposable
    delegatingContent
  }
}

// 이론상(https://github.com/jisungbin/kotlin-2.1.20/blob/735b16bbc2d47b49e2ebc4a324212adc6f43d77e/plugins/compose/compiler-hosted/src/main/java/androidx/compose/compiler/plugins/kotlin/lower/AbstractComposeLowering.kt#L1357)
// RecomposeScope가 안 생겨야 하는데.. 모르겠다! 밑에 DelegatingComposable.getValue() 확장 함수로 만들어도 동일해.
@Immutable private object DelegatingComposable {
  @Composable operator fun getValue(thisRef: Any?, property: KProperty<*>) {
    var count by remember { mutableIntStateOf(0) }

    Text(
      "DelegatingComposable @ $currentRecomposeScopeHash (${currentTimeMillis()})",
      fontWeight = FontWeight.Bold,
    )
    Text(
      "count: $count",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { count++ }
        .background(color = Color.Green)
        .padding(horizontal = 20.dp, vertical = 10.dp),
    )
  }
}

//@Composable private operator fun DelegatingComposable.getValue(thisRef: Any?, property: KProperty<*>) {
//  var count by remember { mutableIntStateOf(0) }
//
//  Text(
//    "DelegatingComposable @ $currentRecomposeScopeHash (${currentTimeMillis()})",
//    fontWeight = FontWeight.Bold,
//  )
//  Text(
//    "count: $count",
//    modifier = Modifier
//      .clip(RoundedCornerShape(10.dp))
//      .clickable { count++ }
//      .background(color = Color.Green)
//      .padding(horizontal = 20.dp, vertical = 10.dp),
//  )
//}
