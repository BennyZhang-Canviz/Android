package com.example.room

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.Room.AppViewModel
import com.example.room.Room.User
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

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
            activity?.let { SharedPreferenceHelper(it).setShowCard(userCard) }!!
        }
        return super.onOptionsItemSelected(item)

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
                searchedUsers.removeObservers(this@UsersFragment)
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
        rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UserAdapter(appViewModel)
        adapter.userCardView(userCard)
        rvUsers.adapter = adapter
        searchedUsers = appViewModel.getAllUsers()
        observeUsers(adapter)

        floatingActionButton.setOnClickListener(){
            Navigation.findNavController(it).navigate(R.id.action_usersFragment_to_addUserFragment)
        }


    }

    private fun observeUsers(adapter: UserAdapter) {
        searchedUsers.observe(viewLifecycleOwner, Observer<List<User>>() {
            var temp = adapter.itemCount
            adapter.setUsers(it)
            if (temp != it.count()) {
                adapter.notifyDataSetChanged()
            }

        })
    }

    override fun onResume() {
        super.onResume()
        var imm: InputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken,0)
    }
}
