package com.apps.notesapp

import android.app.Application
import com.apps.notesapp.data.NotesDataSource
import com.apps.notesapp.data.NotesRepository
import com.apps.notesapp.data.storage.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NotesApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { AppDatabase.getDatabase(this, applicationScope) }

    private val dataSource by lazy {
        NotesDataSource(
            noteDao = database.userDao(),
            service = null
        )
    }

    val repository by lazy {
        NotesRepository(dataSource)
    }
}
