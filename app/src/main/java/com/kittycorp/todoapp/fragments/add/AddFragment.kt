package com.kittycorp.todoapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kittycorp.todoapp.R
import com.kittycorp.todoapp.data.Converter
import com.kittycorp.todoapp.data.models.Priority
import com.kittycorp.todoapp.data.models.ToDoData
import com.kittycorp.todoapp.data.viewmodels.SharedViewModel
import com.kittycorp.todoapp.data.viewmodels.ToDoViewModel
import com.kittycorp.todoapp.databinding.FragmentAddBinding
import com.kittycorp.todoapp.databinding.FragmentListBinding

class AddFragment : Fragment() {

    private var _binding : FragmentAddBinding? = null
    private val binding  get() = _binding!!

    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        binding.prioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_add) {
            insertDataToDatabase()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDatabase() {
        val mTitle = binding.titleEt.text.toString()
        val mPriority = binding.prioritiesSpinner.selectedItem.toString()
        val mDescription = binding.descriptionEt.text.toString()

        if((TextUtils.isEmpty(mTitle) || TextUtils.isEmpty(mDescription))) {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_SHORT).show()
            return
        }

        val newData = ToDoData(
                0,
                mTitle,
                mSharedViewModel.StringToPriority(mPriority),
                mDescription
        )

        mToDoViewModel.insertData(newData)
        Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }
}