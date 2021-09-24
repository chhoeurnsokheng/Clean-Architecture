package com.example.myapplication.data.mapper.base

interface Mapper<E,D> {
    fun mapFromEntity(types: E):D

    fun mapToEntity(types: D):E
}