package com.example.room

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.room.Room.AppViewModel
import com.example.room.Room.User
import kotlinx.android.synthetic.main.fragment_add_user.*

/**
 * A simple [Fragment] subclass.
 */
class AddUserFragment : Fragment(), TextWatcher {

    private val appViewModel: AppViewModel by lazy {
        ViewModelProvider(this)[AppViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_user, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnAdd.isEnabled = false
        etFirstName.requestFocus()
        var imm: InputMethodManager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(etFirstName,0)
        btnAdd.setOnClickListener(){
            var firstName = etFirstName.text.toString()
            var lastName = etLastName.text.toString()
            var age = etAge.text.toString().toInt()
            appViewModel.insert(User(firstName = firstName,lastName = lastName,age = age,showAge = true))
            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_addUserFragment_to_usersFragment) }
            imm.hideSoftInputFromWindow(it.windowToken,0)
        }

        etFirstName.addTextChangedListener (this)
        etLastName.addTextChangedListener(this)
        etAge.addTextChangedListener(this)
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        var firstName = etFirstName.text.trim()
        var lastName = etLastName.text.trim()
        var age = etAge.text.toString().trim()
        btnAdd.isEnabled = (!firstName.isNullOrEmpty()) && (!lastName.isNullOrEmpty()) && (!age.isNullOrEmpty())
    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }
}
