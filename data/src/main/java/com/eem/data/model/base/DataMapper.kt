package com.eem.data.model.base

abstract class DataMapper<out R> {

    abstract fun mapToDataModel(): R
}
