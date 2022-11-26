package de.rogallab.android.presentation

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DEFAULT_ARGS_KEY
import androidx.lifecycle.ViewModel
import de.rogallab.android.R
import de.rogallab.android.model.Dog
import java.util.*

class MainViewModel(

): ViewModel() {

   var initialDogs: MutableList<Dog> = mutableListOf()

   // state: observable for a MutableList<Dog>
   var dogs:SnapshotStateList<Dog>  = mutableStateListOf()
      private set
   fun onDogsChange(value: MutableList<Dog>) {
     dogs.clear()
     dogs.addAll(value)
   }
}