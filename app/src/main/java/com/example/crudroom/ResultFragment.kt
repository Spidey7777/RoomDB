package com.example.crudroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.crudroom.Database.WordDatabase
import com.example.crudroom.databinding.FragmentResultBinding
import com.example.crudroom.viewmodel.WordViewModel
import com.example.crudroom.viewmodel.WordViewModelFactory

class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentResultBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = WordDatabase.getInstance(application).wordDatabaseDao

        val viewModelFactory = WordViewModelFactory(dataSource, application)

        val wordViewModel = ViewModelProvider(this, viewModelFactory).get(WordViewModel::class.java)

        binding.wordViewModel = wordViewModel

        binding.setLifecycleOwner(this)

        val outputs = wordViewModel.display()

        binding.textView.text = outputs.toString()

        binding.backButton.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_resultFragment_to_inputFragment)
        }
        return binding.root
    }
}