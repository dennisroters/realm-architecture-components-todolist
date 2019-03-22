package com.dennisroters.realmarchitecturecomponentstodolist.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class Coroutines {

    companion object {

        val MAIN_CTX = Dispatchers.Main
        val BACKGROUND_CTX = Dispatchers.IO

        val main = CoroutineScope(MAIN_CTX)
        val background = CoroutineScope(BACKGROUND_CTX)
    }

}
