package com.example.crudroom.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDatabaseDao {

    @Insert
    fun insert(word:Word)

    @Query("Delete from word_list_table")
    fun clear()

    @Query("Select * from word_list_table order by wordId desc")
    fun getAllWords(): LiveData<List<Word>>
}