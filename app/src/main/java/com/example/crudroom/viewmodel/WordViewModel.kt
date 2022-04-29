package com.example.crudroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudroom.Database.Word
import com.example.crudroom.Database.WordDatabase
import com.example.crudroom.Database.WordDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(val database: WordDatabaseDao, application: Application) : AndroidViewModel(application) {

    val wordDao = WordDatabase.getInstance(application).wordDatabaseDao
    fun insert(word:Word) {
        viewModelScope.launch(Dispatchers.IO) {
            wordDao.insert(word)
        }
    }
    fun clear() {
        viewModelScope.launch(Dispatchers.IO) {
            wordDao.clear()
        }
    }
    fun display() {
        viewModelScope.launch(Dispatchers.IO) {
            wordDao.getAllWords()
        }
    }
}