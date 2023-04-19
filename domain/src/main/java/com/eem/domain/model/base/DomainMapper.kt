package com.eem.domain.model.base

abstract class DomainMapper<out R> {

    abstract fun mapToDomainModel(): R
}
