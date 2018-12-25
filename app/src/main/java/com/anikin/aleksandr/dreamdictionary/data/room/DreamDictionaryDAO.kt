package com.anikin.aleksandr.dreamdictionary.data.room

import android.arch.persistence.room.*

@Dao
interface DreamDictionaryDAO {

    @get:Query("SELECT * FROM DreamDictionaryEntity")
    val all: List<DreamDictionaryEntity>

    @Query("SELECT * FROM DreamDictionaryEntity WHERE id = :id")
    fun getDefinitionById(id: Int): DreamDictionaryEntity

    @Query("SELECT * FROM DreamDictionaryEntity WHERE keyword LIKE :keyword")
    fun getDefinitionByKeyword(keyword: String): DreamDictionaryEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: DreamDictionaryEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg entities: DreamDictionaryEntity)

    @Update
    fun update(entity: DreamDictionaryEntity)

    @Delete
    fun delete(entity: DreamDictionaryEntity)
}
