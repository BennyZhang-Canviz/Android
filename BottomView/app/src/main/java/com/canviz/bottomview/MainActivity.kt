package com.canviz.bottomview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.account_icon_layout.*
import kotlinx.android.synthetic.main.contact_icon_layout.*
import kotlinx.android.synthetic.main.explorer_icon_layout.*
import kotlinx.android.synthetic.main.message_icon_layout.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.fragment)

        initClickEvent()


    }

    private fun initClickEvent() {
        //下面演示集合mapOf的使用。有了它之后代码更加简洁
        var destinationMap  = mapOf(
            R.id.messageFragment to messageMotionLayout,
            R.id.accountFragment to accountMotionLayout,
            R.id.contactFragment to contactMotionLayout,
            R.id.explorerFragment to explorerMotionLayout
        )
        setupActionBarWithNavController(navController,
            //以下是简单写法
            //AppBarConfiguration(setOf(R.id.messageFragment,R.id.contactFragment,R.id.explorerFragment,R.id.accountFragment))
            AppBarConfiguration(destinationMap.keys)
        )

        destinationMap.forEach{map->
            map.value.setOnClickListener{navController.navigate(map.key)}
        }
//       messageMotionLayout.setOnClickListener{navController.navigate(R.id.messageFragment)}
//       accountMotionLayout.setOnClickListener{navController.navigate(R.id.accountFragment)}
//       contactMotionLayout.setOnClickListener{navController.navigate(R.id.contactFragment)}
//       explorerMotionLayout.setOnClickListener{navController.navigate(R.id.explorerFragment)}

        navController.addOnDestinationChangedListener{controller, destination, arguments ->
            controller.popBackStack()//清空返回栈

            destinationMap.values.forEach{
                it.progress = 0f
            }
//            messageMotionLayout.progress = 0f
//            accountMotionLayout.progress = 0f
//            contactMotionLayout.progress = 0f
//            explorerMotionLayout.progress = 0f


            destinationMap[destination.id]?.transitionToEnd()
//            when(destination.id){
//                R.id.messageFragment -> messageMotionLayout.transitionToEnd()
//                R.id.accountFragment -> accountMotionLayout.transitionToEnd()
//                R.id.contactFragment -> contactMotionLayout.transitionToEnd()
//                R.id.explorerFragment -> explorerMotionLayout.transitionToEnd()
//            }
        }


    }
}