package com.blogsetyaaji.moviecatalogue.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.MovieEntity
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.TvEntity

@Database(entities = [MovieEntity::class, TvEntity::class],
    version = 1,
    exportSchema = false)
abstract class ContentDatabase : RoomDatabase() {
    abstract fun MovieDao(): MovieDao
    abstract fun TvDao(): TvDao

    companion object {

        @Volatile
        private var INSTANCE: ContentDatabase? = null

        fun getInstance(context: Context): ContentDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ContentDatabase::class.java,
                    "Content.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}