package com.example.sqproject1.network

import retrofit2.http.GET

interface EmployeeApi {

    @GET("employees.json")
    suspend fun getEmployees() : EmployeeResponse
}