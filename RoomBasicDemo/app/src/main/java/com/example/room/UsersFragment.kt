package com.example.room

import android.content.Context
import android.os.Bundle
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.Room.AppViewModel
import com.example.room.Room.User
import kotlinx.android.synthetic.main.fragment_users.*

/**
 * A simple [Fragment] subclass.
 */
class UsersFragment : Fragment() {
    private var userCard:Boolean = false
    private val appViewModel: AppViewModel by lazy {
        ViewModelProvider(this)[AppViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvUsers.layoutManager = LinearLayoutManager(context)
        var adapter: UserAdapter = UserAdapter(appViewModel)
        adapter.userCardView(userCard)
        rvUsers.adapter = adapter

        appViewModel.getAllUsers().observe(viewLifecycleOwner, Observer<List<User>>(){
            var temp = adapter.itemCount
            adapter.setUsers(it)
            if(temp != it.count()){
                adapter.notifyDataSetChanged()
            }

        })

        floatingActionButton.setOnClickListener(){
            Navigation.findNavController(it).navigate(R.id.action_usersFragment_to_addUserFragment)
        }


    }

    override fun onResume() {
        super.onResume()
        var imm: InputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken,0)
    }
}
