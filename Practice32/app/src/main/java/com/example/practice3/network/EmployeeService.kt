package com.example.practice3.network

import retrofit2.http.GET

interface EmployeeService {
    @GET("employees.json")
    suspend fun fetchEmployees(): EmployeeResponse
}