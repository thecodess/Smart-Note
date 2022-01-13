package com.apps.notesapp.data.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.apps.notesapp.domain.Note
import com.apps.notesapp.domain.getNotesColorsValues
import com.apps.notesapp.domain.getNotesHeightsValues
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "notes_database"
                ).addCallback(NotesDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class NotesDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val userDao = database.userDao()
                    userDao.deleteAll()
                    for (i in 0 until (8..19).random()) {
                        val note = Note(title = loremTitle.random(), content = loremContent.random(), color = loremColor.random(), height = loremHeight.random())
                        userDao.insert(note)
                    }
                }
            }
        }

        val loremColor = getNotesColorsValues()

        val loremHeight = getNotesHeightsValues()

        val loremTitle = listOf(
            "App architecture",
            "Save data in a local database using Room",
            "Jetpack Compose",
            "Dependency Injection with Hilt"

        )

        val loremContent = listOf(
            "App architecture design i an important consideration for ensuring that your apps are robust, testable and maintable. Android provides a set of libraries and components to help you put together your app according to best practices",
            "The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.",
            "Jetpack Compose is Android's modern toolkit for building native UI. It simplifies and accelerates UI development on Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs.",
            "Hilt is a depndency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project. Doing manual dependency injection requires you to construct evry class and its dependencies by hand, and to use containers to reuse and manage dependencies."

        )
    }
}
