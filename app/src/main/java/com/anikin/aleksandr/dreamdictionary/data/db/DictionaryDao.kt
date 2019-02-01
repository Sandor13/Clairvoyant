package com.anikin.aleksandr.dreamdictionary.data.db

import android.arch.persistence.room.*
import com.google.common.collect.ImmutableList

/**
 * @author Aleksandr Anikin
 */
@Dao
interface DictionaryDao {

    @get:Query("SELECT * FROM DictionaryEntity")
    val all: List<DictionaryEntity>?

    @Query("SELECT * FROM DictionaryEntity WHERE keyword LIKE :keyword")
    fun getDescriptionByKeyword(keyword: String): DictionaryEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: DictionaryEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(entities: ImmutableList<DictionaryEntity>)

    @Update
    fun update(entity: DictionaryEntity)

    @Delete
    fun delete(entity: DictionaryEntity)
}
