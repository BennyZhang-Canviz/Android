package com.example.calculation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.calculation.databinding.FragmentQuestionsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val scoreViewMode: ScoreViewModel by lazy {
        ViewModelProvider(this)[ScoreViewModel::class.java]
    }
    private var result: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scoreViewMode.generate()
        var binding: FragmentQuestionsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_questions,container,false)
        binding.model = scoreViewMode
        binding.lifecycleOwner = this
        updateDisplayRults(0,binding,true)
        bindClickEvents(binding)

        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_questions, container, false)
    }

    private fun updateDisplayRults(x:Int, binding: FragmentQuestionsBinding , clearAll:Boolean = false){
        result += x.toString()
        if(clearAll){
            result = ""
        }
        binding.textViewUserResult.text = result
    }
    private fun bindClickEvents(binding: FragmentQuestionsBinding) {
        binding.btn0.setOnClickListener() {
            updateDisplayRults(0,binding)
        }
        binding.btn1.setOnClickListener() {
            updateDisplayRults(1,binding)
        }
        binding.btn2.setOnClickListener() {
            updateDisplayRults(2,binding)
        }
        binding.btn3.setOnClickListener() {
            updateDisplayRults(3,binding)
        }
        binding.btn4.setOnClickListener() {
            updateDisplayRults(4,binding)
        }
        binding.btn5.setOnClickListener() {
            updateDisplayRults(5,binding)
        }
        binding.btn6.setOnClickListener() {
            updateDisplayRults(6,binding)
        }
        binding.btn7.setOnClickListener() {
            updateDisplayRults(7,binding)
        }
        binding.btn8.setOnClickListener() {
            updateDisplayRults(8,binding)
        }
        binding.btn9.setOnClickListener() {
            updateDisplayRults(9,binding)
        }
        binding.btnClear.setOnClickListener() {
            updateDisplayRults(0,binding,true)
        }

        binding.btnSubmit.setOnClickListener(){
            if(result.isNullOrEmpty()){
                Toast.makeText(activity,"先输入结果!",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
             if(result.toInt() == scoreViewMode.answer.value){ //Answer correct
                 scoreViewMode.answerCorrect()
                 updateDisplayRults(0,binding,true)
             }else
             {
                 var highestScore = (scoreViewMode.highestScore.value?:0)
                 var answerCorrectCount = (scoreViewMode.answerCorrectCount.value?:0)
                 if( highestScore>=answerCorrectCount){
                     //Go to lose window
                     findNavController().navigate(R.id.action_questionsFragment_to_lostFragment)
                 }else{
                     //Go to  succeed window.
                     findNavController().navigate(R.id.action_questionsFragment_to_WinFragment)
                 }
             }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuestionsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestionsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
