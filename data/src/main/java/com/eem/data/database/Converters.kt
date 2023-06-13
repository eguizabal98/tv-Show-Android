package com.eem.data.database

import androidx.room.TypeConverter
import com.eem.data.database.model.tvshow.TvShowGenreEntity
import com.eem.data.database.model.tvshow.TvShowSeasonEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun tvShowGenreEntityToString(value: List<TvShowGenreEntity>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun stringToTvShowGenre(string: String): List<TvShowGenreEntity> {
        val listType = object : TypeToken<List<TvShowGenreEntity>>() {}.type
        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    fun tvTvShowSeasonToString(value: List<TvShowSeasonEntity>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun stringToTvShowSeason(string: String): List<TvShowSeasonEntity> {
        val listType = object : TypeToken<List<TvShowSeasonEntity>>() {}.type
        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    fun tvIntToString(value: List<Int>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun stringToInt(string: String): List<Int> {
        val listType = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(string, listType)
    }
}
