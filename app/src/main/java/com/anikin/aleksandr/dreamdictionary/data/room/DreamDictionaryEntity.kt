package com.anikin.aleksandr.dreamdictionary.data.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(indices = [(Index(value = ["keyword"], unique = true))])
class DreamDictionaryEntity(@field:ColumnInfo(name = "keyword")
                            val keyword: String, @field:ColumnInfo(name = "definition")
                            val definition: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
