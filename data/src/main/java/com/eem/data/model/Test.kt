package com.eem.data.model

import com.eem.domain.model.Test
import com.eem.domain.model.base.DomainMapper

data class Test(
    val name: String
) : DomainMapper<Test>() {

    override fun mapToDomainModel(): Test = Test(name)
}
