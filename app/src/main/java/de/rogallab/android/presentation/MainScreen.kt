package de.rogallab.android.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.rogallab.android.R
import de.rogallab.android.model.Dog
import de.rogallab.android.presentation.base.LogComp
import de.rogallab.android.presentation.base.LogFun
import de.rogallab.android.presentation.composables.ImageItem
import de.rogallab.android.presentation.composables.SearchBar
import de.rogallab.android.presentation.composables.SelectLazyRow
import de.rogallab.android.presentation.theme.paddings
import java.util.*

// https://developer.android.com/codelabs/jetpack-compose-layouts

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
   viewModel: MainViewModel = viewModel()

) {
   val tag: String = "ok>MainScreen         ."
   LogComp(tag, "Start")

   var selectedDog: Dog by remember {
      mutableStateOf(value = Dog(R.string.dog_01, R.drawable.dog_01))
   }

   var searchText: String by remember { mutableStateOf("") }

   Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier
         .fillMaxWidth()
         .padding(all = MaterialTheme.paddings.small)
   ) {

      SearchBar(
         search = searchText,        // State ↓
         onSearchChanged = {         // Event
            searchText = it
            filter(
               searchText = it,
               dogs = viewModel.dogs,
               onDogsFiltered = { dogs:List<Dog> ->
                  viewModel.onDogsChange(dogs as MutableList<Dog>)
               }
            )
         }
      )

      Spacer(modifier = Modifier.padding(MaterialTheme.paddings.small))

      SelectLazyRow(
         dogs = viewModel.dogs,
         onDogSelect = {
            selectedDog = it
            searchText = ""
            viewModel.onDogsChange(viewModel.initialDogs)
         },
         tag = tag
      )

      Spacer(modifier = Modifier.padding(MaterialTheme.paddings.large))

      Column(
         horizontalAlignment = Alignment.CenterHorizontally,
         modifier = Modifier.fillMaxWidth()
      ) {
         ImageItem(
            dog = selectedDog,
            onClick = {},
            modifier = Modifier
               .size(width = 200.dp, height = 200.dp)
         )
      }
   }
}

private fun filter(
   searchText: String,
   dogs: List<Dog>,                      // State ↓
   onDogsFiltered: (List<Dog>) -> Unit,  // Event ↑
) {
   if (searchText.trim().isEmpty()) {
      onDogsFiltered(dogs)
   } else {
      dogs
         .filter { dog: Dog ->
            val dogNameLower = dog.name!!.lowercase(Locale.getDefault())
            val searchLower  = searchText.lowercase(Locale.getDefault())
            dogNameLower.startsWith(searchLower)  // ^filter
         }
         .apply { // this:List<Dog>
            onDogsFiltered(this)
         }
   }
}