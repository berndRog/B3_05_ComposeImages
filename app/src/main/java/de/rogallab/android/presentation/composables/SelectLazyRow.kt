package de.rogallab.android.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.rogallab.android.model.Dog
import de.rogallab.android.presentation.base.LogComp
import de.rogallab.android.presentation.base.LogFun
import de.rogallab.android.presentation.theme.paddings

@Composable
fun SelectLazyRow(
   dogs: List<Dog>,               // State ↓
   onDogSelect: (Dog) -> Unit,    // Event ↑
   tag: String
) {

   LogComp(tag, "")

   LazyRow(
      horizontalArrangement = Arrangement.spacedBy(MaterialTheme.paddings.small)
   ) {
      items(
         items = dogs
      ) { dog ->
         ImageItem(
            dog = dog,
            onClick = {
               LogFun(tag, "Click ${it.name}")
               onDogSelect( it )
            },
            modifier = Modifier
               .size(width = 80.dp, height = 80.dp)
         )
      }
   }
}