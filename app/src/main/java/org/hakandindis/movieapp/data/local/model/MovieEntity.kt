package org.hakandindis.movieapp.data.local.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo("movie_name") val movieName: String,
    @ColumnInfo("movie_overview") val movieOverview: String,
    @ColumnInfo("movie_vote") val movieVote: Double,
    @ColumnInfo("thumbnail_url") val thumbnailUrl: String,
): Parcelable
