package com.example.room

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.icu.lang.UCharacter
import android.os.Bundle
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.Room.AppViewModel
import com.example.room.Room.User
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_users.*
import kotlin.properties.Delegates

/**
 * A simple [Fragment] subclass.
 */
class UsersFragment : Fragment() {
    private var  userCard by Delegates.notNull<Boolean>()
    private val appViewModel: AppViewModel by lazy {
        ViewModelProvider(this)[AppViewModel::class.java]
    }
    private lateinit var searchedUsers: LiveData<List<User>>
    private lateinit var  adapter: UserAdapter
    private lateinit var dividerItemDecoration: DividerItemDecoration


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    private lateinit var users:List<User>

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.miClear){
            var builder: AlertDialog.Builder = AlertDialog.Builder(activity)
            builder.setTitle("确定要删除所有数据吗？")
            builder.setNegativeButton("取消") { _, _ ->  }
            builder.setPositiveButton("确定") { _, _ ->
                appViewModel.deleteAll()
            }
            builder.create().show()
        }
        if(item.itemId == R.id.miChangeView){
            userCard = activity?.let { SharedPreferenceHelper(it).getShowCard() }!!
           userCard = !userCard
           adapter.userCardView(userCard)
           rvUsers.adapter = adapter
            addBorder()

            activity?.let { SharedPreferenceHelper(it).setShowCard(userCard) }!!
        }
        return super.onOptionsItemSelected(item)

    }

    private fun addBorder() {
        if (!userCard) {
            rvUsers.addItemDecoration(dividerItemDecoration)
        } else {
            rvUsers.removeItemDecoration(dividerItemDecoration)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu,menu)
        var searchBar = menu.findItem(R.id.app_bar_search).actionView as SearchView
        searchBar.maxWidth = 600
        searchBar.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchedUsers.removeObservers(viewLifecycleOwner)
                searchedUsers = if (newText != null) {
                    appViewModel.findByName(newText.trim())
                }else{
                    appViewModel.getAllUsers()
                }
                observeUsers(adapter)
                return true
            }

        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userCard = activity?.let { SharedPreferenceHelper(it).getShowCard() }!!
        dividerItemDecoration = DividerItemDecoration(context,DividerItemDecoration.VERTICAL)
        rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UserAdapter(appViewModel)
        adapter.userCardView(userCard)
        rvUsers.adapter = adapter
        addBorder()
        searchedUsers = appViewModel.getAllUsers()
        observeUsers(adapter)

        floatingActionButton.setOnClickListener(){
            Navigation.findNavController(it).navigate(R.id.action_usersFragment_to_addUserFragment)
        }

        var simpleCallback = object: SimpleCallback(0, ItemTouchHelper.START or ItemTouchHelper.END){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
               return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var userToDelete: User = users[viewHolder.adapterPosition]
                appViewModel.delete(userToDelete)
                Snackbar.make(activity!!.findViewById(R.id.recyclever_container),"删除了一个用户",Snackbar.LENGTH_LONG)
                    .setAction("撤销",View.OnClickListener {
                        appViewModel.insert(userToDelete)
                    }).show()
            }
        }

        val touchHelper = ItemTouchHelper(simpleCallback)
        touchHelper.attachToRecyclerView(rvUsers)
    }

    private fun observeUsers(adapter: UserAdapter) {
        searchedUsers.observe(viewLifecycleOwner, Observer<List<User>>() {
            var temp = adapter.itemCount
            users = it
            if (temp != it.count()) {
                if(temp < it.count()){
                    rvUsers.smoothScrollBy(0,-200)
                }
                adapter.submitList(it)
            }

        })
    }

    override fun onResume() {
        super.onResume()
        var imm: InputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken,0)
    }
}
