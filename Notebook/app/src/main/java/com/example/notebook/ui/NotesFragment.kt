package com.example.notebook.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.util.Log

import android.view.*
import android.widget.SearchView

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController

import com.example.notebook.R
import com.example.notebook.adapter.NoteListAdapter
import kotlinx.android.synthetic.main.fragment_notes.*

import com.example.notebook.util.InjectorUtils
import com.example.notebook.viewmodel.NoteListViewModel
import com.example.notebook.databinding.FragmentNotesBinding

import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass.
 */
class NotesFragment : Fragment() {

    private val viewModel : NoteListViewModel by viewModels {
        InjectorUtils.provideNoteBookViewModelFactory(requireContext(),this)
    }
    private lateinit var notesAdapter: NoteListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentNotesBinding.inflate(inflater,container,false)
        context?: return binding.root
         notesAdapter = NoteListAdapter()

        binding.ivNotes.adapter = notesAdapter

        bindData()
        setHasOptionsMenu(true)


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_note,menu)
        var menuMethod = menu.javaClass.getDeclaredMethod("setOptionalIconsVisible", java.lang.Boolean.TYPE)
        menuMethod.isAccessible = true
        menuMethod.invoke(menu, true)
        var search = menu.findItem(R.id.search_menu)
        val searchView = search.actionView as SearchView
        searchView.queryHint = getString(R.string.Search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    val updateHandler = Handler()
                    val runnable = Runnable {
                        viewModel.searchNotes(newText)
                        bindData()
                    }
                    updateHandler.postDelayed(runnable, 300)

                }
                return true
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_all_note){
            deleteAllNotes()
        }
        if(item.itemId == R.id.about){
            var direction = NotesFragmentDirections.actionNotesFragmentToAboutFragment()
            findNavController().navigate(direction)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllNotes() {
        var alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(getString(R.string.AreYouSure))
        alertDialogBuilder.setMessage(getString(R.string.DeleteAllMessage))
        alertDialogBuilder.setNegativeButton(getString(R.string.Yes), DialogInterface.OnClickListener()
        { _: DialogInterface, i: Int ->

            viewModel.deleteAll()
        })
        alertDialogBuilder.setPositiveButton(getString(R.string.No), DialogInterface.OnClickListener()
        { dialogInterface: DialogInterface, i: Int ->

        })
        alertDialogBuilder.show()
    }

    private fun bindData() {
        viewModel.notes.observe(viewLifecycleOwner) { notesToSubmit ->
            notesAdapter.submitList(notesToSubmit)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bar = (activity as MainActivity).supportActionBar.let {
            it?.setDisplayHomeAsUpEnabled(false)
        }
        floatingActionButton.setOnClickListener{
            it.findNavController().navigate(NotesFragmentDirections.actionNotesFragmentToNewNoteFragment())
        }
    }

}
