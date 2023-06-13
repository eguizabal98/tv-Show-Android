package com.eem.data.database.model.tvshow

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eem.data.repository.base.DomainMapper
import com.eem.domain.model.tvshow.TvShowGenre

@Entity
data class TvShowGenreEntity(
    @PrimaryKey
    val id: Int,
    val name: String
) : DomainMapper<TvShowGenre> {

    override fun mapToDomainModel(): TvShowGenre = TvShowGenre(id, name)
}
