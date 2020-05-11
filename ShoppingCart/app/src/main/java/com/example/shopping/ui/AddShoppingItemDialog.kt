package com.example.shopping.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shopping.R
import com.example.shopping.data.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*

interface AddDialogCallback{
    fun saveAddShoppingItem(shoppingItem: ShoppingItem)
}

class AddShoppingItemDialog (context: Context, private val addDialogCallback: AddDialogCallback): AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)
        btnAdd.setOnClickListener(){
            var name = tvName.text.toString()
            var amount = tvAmount.text.toString()
            when(true){
                name.isNullOrEmpty() ->{
                    Toast.makeText(context,"Name can't be null",Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                amount.isNullOrEmpty() ->{
                    Toast.makeText(context,"Amount can't be null",Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                else->{
                    var item = ShoppingItem(name = name, amount = amount.toInt())
                    addDialogCallback.saveAddShoppingItem(item)
                    dismiss()
                }
            }
        }
        btnCancel.setOnClickListener(){
            cancel()
        }
    }
}