package com.happy.kotlinmodule.bean

data class PersonBean(val name: String, val age: Int? = null) {

    private val mTag: String = "GAOJIAHUAN"

    fun main(args: Array<String>) {
        val personals = listOf(PersonBean("Panda"), PersonBean("Jiahuan", 30))
        val oldest = personals.maxBy { it.age ?: 0 }
        println("Hello Kotlin is: $oldest" + mTag)
    }
}