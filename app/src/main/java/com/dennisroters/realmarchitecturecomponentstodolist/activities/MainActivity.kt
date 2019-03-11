package com.dennisroters.realmarchitecturecomponentstodolist.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dennisroters.realmarchitecturecomponentstodolist.R
import com.dennisroters.realmarchitecturecomponentstodolist.components.todo.TodoListFragment
import com.dennisroters.realmarchitecturecomponentstodolist.utils.TransactionType

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openFragment(TodoListFragment(), TransactionType.REPLACE)
    }

    fun openFragment(fragment: Fragment, type: TransactionType = TransactionType.REPLACE_WITH_BACKSTACK) {
        val transaction = supportFragmentManager.beginTransaction()

        // Animation
        //transaction.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)

        when (type) {
            TransactionType.ADD -> {
                transaction.add(R.id.mainFragmentContainer, fragment)
            }

            TransactionType.REPLACE -> {
                transaction.replace(R.id.mainFragmentContainer, fragment)
            }

            TransactionType.ADD_WITH_BACKSTACK -> {
                transaction.add(R.id.mainFragmentContainer, fragment)
                transaction.addToBackStack(null)
            }

            TransactionType.REPLACE_WITH_BACKSTACK -> {
                transaction.replace(R.id.mainFragmentContainer, fragment)
                transaction.addToBackStack(null)
            }
        }

        transaction.commitAllowingStateLoss()
    }
}
