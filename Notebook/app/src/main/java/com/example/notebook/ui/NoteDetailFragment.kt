package com.example.notebook.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notebook.R
import com.example.notebook.databinding.FragmentNoteDetailBinding
import com.example.notebook.util.InjectorUtils
import com.example.notebook.viewmodel.NoteViewModel
import java.lang.Boolean

/**
 * A simple [Fragment] subclass.
 */
class NoteDetailFragment : Fragment() {
    private val args: NoteDetailFragmentArgs by navArgs()
    private val detailViewModel: NoteViewModel by viewModels {
        InjectorUtils.provideNoteDetailViewModelFactory(args.noteId,requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding =
            DataBindingUtil.inflate<FragmentNoteDetailBinding>(
                inflater, R.layout.fragment_note_detail, container, false
            ).apply {
                noteViewModel = detailViewModel
                lifecycleOwner =viewLifecycleOwner
            }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar.let {
            it?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): kotlin.Boolean {
        when(item.itemId){
            android.R.id.home ->
                findNavController().navigateUp()

            R.id.delete_note ->{
                deleteNote()
            }
            R.id.edit_note ->{
                navigationToEdit()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigationToEdit() {

        val direction =
            NoteDetailFragmentDirections.actionNoteDetailFragmentToEditNoteFragment(
                args.noteId
            )
        findNavController().navigate(direction)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_detail_menu,menu)
        var menuMethod = menu.javaClass.getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE)
        menuMethod.isAccessible = true
        menuMethod.invoke(menu, true)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun deleteNote(){
        var alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(getString(R.string.AreYouSure))
        alertDialogBuilder.setMessage(getString(R.string.DeleteNoteMessage))
        alertDialogBuilder.setNegativeButton(getString(R.string.Yes), DialogInterface.OnClickListener()
        { _: DialogInterface, i: Int ->
            detailViewModel.delete()
            findNavController().navigateUp()
        })
        alertDialogBuilder.setPositiveButton(getString(R.string.No), DialogInterface.OnClickListener()
        { dialogInterface: DialogInterface, i: Int ->

        })
        alertDialogBuilder.show()
    }



}
