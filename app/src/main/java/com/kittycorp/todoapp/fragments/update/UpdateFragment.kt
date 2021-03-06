package com.kittycorp.todoapp.fragments.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kittycorp.todoapp.R
import com.kittycorp.todoapp.data.models.ToDoData
import com.kittycorp.todoapp.data.viewmodels.SharedViewModel
import com.kittycorp.todoapp.data.viewmodels.ToDoViewModel
import com.kittycorp.todoapp.databinding.FragmentListBinding
import com.kittycorp.todoapp.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    var _binding: FragmentUpdateBinding? = null
    val binding get() = _binding!!

    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        binding.currentTitleEt.setText(args.currentItem.title)
        binding.currentDescriptionEt.setText(args.currentItem.description)
        binding.currentPrioritiesSpinner.setSelection(args.currentItem.priority.ordinal)
        binding.currentPrioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener


        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_save) {
            updateItem()
        }
        else if(item.itemId == R.id.menu_delete) {
            deleteItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateItem() {
        val title = binding.currentTitleEt.text.toString()
        val description = binding.currentDescriptionEt.text.toString()
        val getPriority = binding.currentPrioritiesSpinner.selectedItem.toString()

        if(title.isEmpty() || description.isEmpty()) {
            Toast.makeText(context, "Please fill all fields!", Toast.LENGTH_SHORT).show()
            return
        }

        mToDoViewModel.updateData(ToDoData(
                args.currentItem.id,
                title,
                mSharedViewModel.StringToPriority(getPriority),
                description
        ))
        Toast.makeText(context, "Update successful!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }

    private fun deleteItem() {
        mToDoViewModel.deleteData(args.currentItem)
        Toast.makeText(context, "Delete successful!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }
}