package com.example.moviestesttask.core

interface ModelMapper<DOMAIN, TO> {
    fun mapTo(model: DOMAIN): TO
    fun mapToDomain(model: TO): DOMAIN
}