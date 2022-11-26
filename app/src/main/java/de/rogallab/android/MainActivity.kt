package de.rogallab.android

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import de.rogallab.android.model.Dog
import de.rogallab.android.presentation.MainScreen
import de.rogallab.android.presentation.MainViewModel
import de.rogallab.android.presentation.base.BaseComponentActivity
import de.rogallab.android.presentation.theme.AppTheme

class MainActivity : BaseComponentActivity(_tag) {

   override fun onCreate(savedInstanceState: Bundle?) {

      val sortedDogs = initDogs()

      super.onCreate(savedInstanceState)
      setContent {

         // Start of composables ...
         val viewModel: MainViewModel = viewModel()
         viewModel.initialDogs = sortedDogs
         viewModel.onDogsChange( sortedDogs )


         AppTheme {
            // A surface container using the 'background' color from the theme
            Surface(
               modifier = Modifier.fillMaxSize(),
               color = MaterialTheme.colors.background
            ) {
//               ImageItem(
//                  dog = Dog(R.string.dog_01, R.drawable.dog_01),
//                  modifier = Modifier.size(200.dp),
//                  onClicked = { Unit }
//               )
               MainScreen()
            }
         }
      }
   }

   private fun initDogs(): MutableList<Dog>  {
      // create Dog objects
      val dogs = mutableListOf<Dog>()
      dogs.add(Dog(R.string.dog_01, R.drawable.dog_01))
      dogs.add(Dog(R.string.dog_02, R.drawable.dog_02))
      dogs.add(Dog(R.string.dog_03, R.drawable.dog_03))
      dogs.add(Dog(R.string.dog_04, R.drawable.dog_04))
      dogs.add(Dog(R.string.dog_05, R.drawable.dog_05))
      dogs.add(Dog(R.string.dog_06, R.drawable.dog_06))
      dogs.add(Dog(R.string.dog_07, R.drawable.dog_07))
      dogs.add(Dog(R.string.dog_08, R.drawable.dog_08))
      // create name as String
      dogs.forEach { dog ->
         dog.name = applicationContext.resources.getString(dog.resourceName)
      }
      // sort dogs by name
      val sortedDogs = dogs.sortedBy { it.name } as MutableList<Dog>
      return sortedDogs
   }

   companion object {
      private val _tag = "ok>MainActivity       ."
   }
}
