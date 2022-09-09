package com.blogsetyaaji.moviecatalogue.data.source.local.room

import androidx.room.TypeConverter
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.GenresItem
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.ProductionCompaniesItem
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.ProductionCountriesItem
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.SpokenLanguagesItem
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.movie.BelongsToCollection
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.tv.*
import com.google.gson.Gson

class RoomConverter {
    //Detail Movie Table
    @TypeConverter
    fun genreToJson(value: List<GenresItem>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToGenre(value: String) =
        Gson().fromJson(value, Array<GenresItem>::class.java)
            .toList()

    @TypeConverter
    fun productionCompanyToJson(value: List<ProductionCompaniesItem>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToProductionCompany(value: String) =
        Gson().fromJson(value, Array<ProductionCompaniesItem>::class.java)
            .toList()

    @TypeConverter
    fun productionCountryToJson(value: List<ProductionCountriesItem>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToProductionCountry(value: String) =
        Gson().fromJson(value, Array<ProductionCountriesItem>::class.java)
            .toList()

    @TypeConverter
    fun spokenLanguageToJson(value: List<SpokenLanguagesItem>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToSpokenLanguage(value: String) =
        Gson().fromJson(value, Array<SpokenLanguagesItem>::class.java)
            .toList()

    @TypeConverter
    fun belongsToCollectionToJson(value: BelongsToCollection?): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToBelongsToCollection(value: String): BelongsToCollection? =
        Gson().fromJson(value, BelongsToCollection::class.java)

    //Detail Tv Show Table
    @TypeConverter
    fun createdByItemToJson(value: List<CreatedByItem>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToCreatedByItem(value: String) =
        Gson().fromJson(value, Array<CreatedByItem>::class.java)
            .toList()

    @TypeConverter
    fun nextEpisodeToAirToJson(value: NextEpisodeToAir?): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToNextEpisodeToAir(value: String): NextEpisodeToAir? =
        Gson().fromJson(value, NextEpisodeToAir::class.java)

    @TypeConverter
    fun lastEpisodeToAirToJson(value: LastEpisodeToAir?): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToLastEpisodeToAir(value: String): LastEpisodeToAir? =
        Gson().fromJson(value, LastEpisodeToAir::class.java)

    @TypeConverter
    fun networkToJson(value: List<NetworksItem>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToNetwork(value: String) =
        Gson().fromJson(value, Array<NetworksItem>::class.java)
            .toList()

    @TypeConverter
    fun seasonToJson(value: List<SeasonsItem>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToSeason(value: String) =
        Gson().fromJson(value, Array<SeasonsItem>::class.java)
            .toList()

    //General
    @TypeConverter
    fun integerToJson(value: List<Int>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToInteger(value: String) =
        Gson().fromJson(value, Array<Int>::class.java)
            .toList()

    @TypeConverter
    fun stringToJson(value: List<String>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToString(value: String) =
        Gson().fromJson(value, Array<String>::class.java)
            .toList()
}