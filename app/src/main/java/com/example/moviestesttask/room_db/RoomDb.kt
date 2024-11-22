package com.example.moviestesttask.room_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviestesttask.room_db.dao.DaoMovie
import com.example.moviestesttask.room_db.entities.GenreIdsConverter
import com.example.moviestesttask.room_db.entities.RoomEntityMovie

@Database(entities = [RoomEntityMovie::class], version = 1, exportSchema = false)
@TypeConverters(GenreIdsConverter::class)
abstract class RoomDb : RoomDatabase() {
	abstract fun moviesDao(): DaoMovie

	companion object {
		@Volatile
		private var INSTANCE: RoomDb? = null

		fun getDatabase(context: Context): RoomDb {
			return INSTANCE ?: synchronized(this) {
				val instance = Room.databaseBuilder(
					context.applicationContext,
					RoomDb::class.java,
					"note_database"
				).addTypeConverter(GenreIdsConverter()).build()
				INSTANCE = instance
				instance
			}
		}
	}
}