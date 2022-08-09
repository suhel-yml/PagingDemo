package com.yml.pagingdemo.domain

interface UseCase<T> {
    suspend fun execute(): T
}