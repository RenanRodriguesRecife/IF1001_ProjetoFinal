package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels


class ExploreFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_explore, container, false)
        view.findViewById<Button>(R.id.button).setOnClickListener {

            val editText1 : EditText = view.findViewById(R.id.editTextText)
            val input1 = editText1.text.toString()
            val editText2 : EditText = view.findViewById(R.id.editTextText2)
            val input2 = editText2.text.toString()

            sharedViewModel.selectInicio(input1)
            sharedViewModel.selectDestino(input2)


            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, MapFragment())
                .addToBackStack(null)
                .commit()

        }
        return view
    }


}