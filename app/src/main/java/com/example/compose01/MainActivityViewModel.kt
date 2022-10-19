package com.example.compose01

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

//        val sheetState by ModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
//            rememberBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)



    private var _unit: MutableLiveData<@Composable() (() -> Unit)> = MutableLiveData({
//       init()
    })

         var unit:LiveData<@Composable () -> Unit> = _unit
            get() = field
    fun set_Unit( _unit: (@Composable () -> Unit)) {
         this._unit.value = _unit
    }
}