package de.rogallab.android.presentation.base

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import de.rogallab.android.BuildConfig

// https://www.jetpackcompose.app/articles/how-can-I-debug-recompositions-in-jetpack-compose

@Composable
fun LogComp(tag: String, msg: String) {
   if (BuildConfig.DEBUG) {
      val ref = remember { Ref(1) }
      SideEffect { ref.value++ }
      Log.d(tag, "Compositions ${ref.value}: $msg")
   }
}

fun LogFun(tag: String, msg: String) {
   if (BuildConfig.DEBUG) {
      Log.d(tag, msg)
   }
}

class Ref(var value: Int)