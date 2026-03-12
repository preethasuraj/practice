package com.example.practice2.network

import retrofit2.http.GET

interface EmployeeApi {

    @GET("employees.json")
    suspend fun fetchEmployees(): EmployeesList
}