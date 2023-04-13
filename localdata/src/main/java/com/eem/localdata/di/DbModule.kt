package com.eem.localdata.di

import android.content.Context
import com.eem.localdata.Database
import com.eem.localdata.database.TestDao
import com.eem.localdata.database.TestDaoImpl
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.dsl.bind
import org.koin.dsl.module

private const val DB_NAME = "TvShow.db"

val dbModule = module {

    fun provideAndroidDriver(context: Context): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, context, DB_NAME)
    }

    fun provideSqlBase(sqlDriver: SqlDriver): Database = Database(sqlDriver)

    fun provideTestEntityQueries(database: Database) = database.testEntityQueries

    single { provideAndroidDriver(get()) }
    single { provideSqlBase(get()) }
    factory { provideTestEntityQueries(get()) }
    factory { TestDaoImpl(get()) } bind TestDao::class
}
