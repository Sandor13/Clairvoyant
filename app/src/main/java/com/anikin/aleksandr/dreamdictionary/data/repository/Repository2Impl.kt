package com.anikin.aleksandr.dreamdictionary.data.repository

import com.anikin.aleksandr.dreamdictionary.data.datasource.DataSource2

class Repository2Impl(val definitionDataSource: DataSource2) : Repository2 {

    override fun getDefinition(): String {
        return definitionDataSource.getDefinition()
    }
}