package com.example.shopping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopping.adapter.ShoppingAdapter
import com.example.shopping.data.ShoppingDatabase
import com.example.shopping.data.ShoppingItem
import com.example.shopping.repositories.ShoppingRepository
import com.example.shopping.ui.AddDialogCallback
import com.example.shopping.ui.AddShoppingItemDialog
import com.example.shopping.viewmodel.ShoppingViewModel
import com.example.shopping.viewmodel.ShoppingViewModelFactory
import com.example.shopping.viewmodel.ViewModelInjector
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity() , KodeinAware {

    override val kodein: Kodein by kodein()


    private val shoppingViewModel:ShoppingViewModel by viewModels{
        ViewModelInjector.getHomeViewModelFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var adapter = ShoppingAdapter(listOf(),shoppingViewModel)
        rvShopping.adapter = adapter
        rvShopping.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        shoppingViewModel.getShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        floatingActionButton.setOnClickListener(){
            AddShoppingItemDialog(this,object : AddDialogCallback {
                override fun saveAddShoppingItem(shoppingItem: ShoppingItem) {
                    shoppingViewModel.insert(shoppingItem)
                }

            }).show()
        }
    }


}
