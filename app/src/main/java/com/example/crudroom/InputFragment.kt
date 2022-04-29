package com.example.crudroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.crudroom.Database.Word
import com.example.crudroom.Database.WordDatabase
import com.example.crudroom.databinding.FragmentInputBinding
import com.example.crudroom.viewmodel.WordViewModel
import com.example.crudroom.viewmodel.WordViewModelFactory

class InputFragment : Fragment() {

    private  var wordInput = Word()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentInputBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_input, container, false)

        wordInput.word = binding.inputText.text.toString()

//        val wordViewmodel = ViewModelProvider(requireActivity()).get(WordViewModel::class.java)

        val application = requireNotNull(this.activity).application

        val dataSource = WordDatabase.getInstance(application).wordDatabaseDao

        val viewModelFactory = WordViewModelFactory(dataSource, application)

        val wordViewModel = ViewModelProvider(this, viewModelFactory).get(WordViewModel::class.java)

        binding.wordViewModel = wordViewModel

        binding.setLifecycleOwner(this)

        binding.addButton.setOnClickListener {
            wordViewModel.insert(wordInput)
            binding.inputText.text.clear()
        }

        binding.clearButton.setOnClickListener {
            wordViewModel.clear()
        }

        binding.displayButton.setOnClickListener { view:View ->
            Navigation.findNavController(view).navigate(R.id.action_inputFragment_to_resultFragment)
//            wordViewmodel.display()
        }

        return binding.root


    }
}