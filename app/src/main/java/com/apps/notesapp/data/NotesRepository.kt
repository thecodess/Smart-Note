package com.apps.notesapp.data

import com.apps.notesapp.domain.Note

class NotesRepository(private val dataSource: NotesDataSource) {
    fun list() = dataSource.list()
    suspend fun get(note: Note) = dataSource.get(note)
    suspend fun insert(note: Note) = dataSource.insert(note)
    suspend fun update(note: Note) = dataSource.update(note)
    suspend fun delete(note: Note) = dataSource.delete(note)
}
