package de.rogallab.android.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.rogallab.android.R

@Composable
fun SearchBar(
   search:String = "",                 // State ↓
   onSearchChanged: (String) -> Unit,  // Event ↑
   modifier: Modifier = Modifier
) {
   OutlinedTextField(
      value = search,
      onValueChange = onSearchChanged,
      leadingIcon = {
         Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null
         )
      },
      colors = TextFieldDefaults.textFieldColors(
         backgroundColor = MaterialTheme.colors.surface
      ),
      placeholder = {
         Text(text = stringResource(R.string.search))
      },
      singleLine = true,
      modifier = modifier
         .fillMaxWidth()
         .heightIn(min = 56.dp)
   )
}