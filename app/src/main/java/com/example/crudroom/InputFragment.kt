package com.example.crudroom

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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





//        val wordViewmodel = ViewModelProvider(requireActivity()).get(WordViewModel::class.java)

        val application = requireNotNull(this.activity).application

        val dataSource = WordDatabase.getInstance(application).wordDatabaseDao

        val viewModelFactory = WordViewModelFactory(dataSource, application)

        val wordViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(WordViewModel::class.java)

        binding.wordViewModel = wordViewModel

        binding.setLifecycleOwner(this)

        binding.addButton.setOnClickListener {
            val word = Word()
            word.word = binding.inputText.text.toString()
            wordViewModel.insert(word)
            binding.inputText.text.clear()


        }


        binding.clearButton.setOnClickListener {
            wordViewModel.clear()
        }

        binding.displayButton.setOnClickListener { view:View ->
            Navigation.findNavController(view).navigate(R.id.action_inputFragment_to_resultFragment)
        }

        return binding.root


    }
}