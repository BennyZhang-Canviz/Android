package com.example.notebook.ui

import android.annotation.SuppressLint
import android.app.ActionBar
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.example.notebook.R
import com.example.notebook.util.InjectorUtils
import com.example.notebook.util.ToastHelper
import com.example.notebook.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_new_note.*
import kotlin.math.log

/**
 * A simple [Fragment] subclass.
 */
class NewNoteFragment : Fragment() {
    private val viewmodel: NoteViewModel by viewModels {
        InjectorUtils.provideNoteDetailViewModelFactory(0,requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_new_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bar = (activity as MainActivity).supportActionBar.let {
            it?.setDisplayHomeAsUpEnabled(true)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_note,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_save_note){
            saveNote()
        }
        if(item.itemId == android.R.id.home){
            editCheck()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun editCheck() {
        var title = tvTitle.text.toString()
        var content = tvContent.text.toString()
        if(title.isNotEmpty() || content.isNotEmpty()){
            var alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.setTitle(getString(R.string.AreYouSure))
            alertDialogBuilder.setMessage(getString(R.string.are_you_sure_not_save))
            alertDialogBuilder.setNegativeButton(getString(R.string.Yes), DialogInterface.OnClickListener()
            { _: DialogInterface, i: Int ->
                findNavController().navigateUp()
            })
            alertDialogBuilder.setPositiveButton(getString(R.string.No), DialogInterface.OnClickListener()
            { dialogInterface: DialogInterface, i: Int ->

            })
            alertDialogBuilder.show()
        }else{
            findNavController().navigateUp()
        }
    }

    private fun saveNote() {
        var title = tvTitle.text.toString()
        var content = tvContent.text.toString()
        when(true){
            title.isEmpty() ->{
                ToastHelper.showToast(getString(R.string.title_empty_message),requireContext())
            }
//            content.isEmpty() ->{
//                showToast("Please input content")
//            }
            else ->{
                viewmodel.create(title,content)
                var services =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                services.hideSoftInputFromWindow(view?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                findNavController().navigateUp()
            }
        }

    }


//    private fun showToast(str: String){
//        var toast =Toast.makeText(requireContext(),str,Toast.LENGTH_LONG).also {
//            val toastLayout = it.view as LinearLayout
//            val toastTV = toastLayout.getChildAt(0) as TextView
//            toastTV.textSize = 30f
//            toastTV.setTextColor(resources.getColor(R.color.toast_color,null))
//        }
//        toast.setGravity(Gravity.CENTER,0,0)
//
//        toast.show()
//
//    }

}
