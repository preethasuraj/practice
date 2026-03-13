package com.example.practice3.network

import retrofit2.http.GET

interface EmployeeApi {

    @GET("employees.json")
    suspend fun fetchEmployees(): EmployeeResponse
}