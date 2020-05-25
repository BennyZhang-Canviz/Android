package com.example.notebook.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.notebook.R
import com.example.notebook.databinding.FragmentEditNoteBinding
import com.example.notebook.util.InjectorUtils
import com.example.notebook.util.ToastHelper
import com.example.notebook.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_new_note.*

/**
 * A simple [Fragment] subclass.
 */
class EditNoteFragment : Fragment() {
    private val args: NoteDetailFragmentArgs by navArgs()
    private val detailViewModel: NoteViewModel by viewModels {
        InjectorUtils.provideNoteDetailViewModelFactory(args.noteId,requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding =
            FragmentEditNoteBinding.inflate(inflater,container,false)

        detailViewModel.note.observe(viewLifecycleOwner){
            binding.note = it

        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_note,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_save_note){
            saveNote()
        }
        if(item.itemId == android.R.id.home){
            confirmDoNotSave()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmDoNotSave(){
        var title = tvTitle.text.toString()
        var content = tvContent.text.toString()
        detailViewModel.note.let {
            if(detailViewModel.note.value!!.title != title || detailViewModel.note.value!!.content != content){
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

    }

    private fun saveNote() {
        var title = tvTitle.text.toString()
        var content = tvContent.text.toString()
        when (true) {
            title.isEmpty() -> {
                ToastHelper.showToast(getString(R.string.title_empty_message), requireContext())
            }
//            content.isEmpty() ->{
//                showToast("Please input content")
//            }
            else -> {
                detailViewModel.update(title, content)
                var services =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                services.hideSoftInputFromWindow(
                    view?.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
                findNavController().navigateUp()
            }
        }
    }

}
