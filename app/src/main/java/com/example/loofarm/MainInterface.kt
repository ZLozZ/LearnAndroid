package com.example.loofarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.loofarm.databinding.FragmentLoginBinding
import com.example.loofarm.databinding.FragmentMainInterfaceBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainInterface.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainInterface : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentMainInterfaceBinding
    private lateinit var itemAdapter:CustomAdapterRV
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        initEvents()
    }

    private fun initEvents() {
//        var databaseReference = Firebase.database.reference
////        val users = DataHelper.fakeData()
//        databaseReference.child("users").setValue(users)
//        Toast.makeText(requireActivity(), "thad", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainInterfaceBinding.inflate(layoutInflater, container, false)
        initViews()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initViews() {
        val listText = listOf("app1", "app2", "app3", "app4")
        binding.rcFarmView.layoutManager = GridLayoutManager(requireActivity(), 2)
        itemAdapter = CustomAdapterRV(listText)
        binding.rcFarmView.adapter = itemAdapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainInterface.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainInterface().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}