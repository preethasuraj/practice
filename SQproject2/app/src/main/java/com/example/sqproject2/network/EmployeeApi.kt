package com.example.sqproject2.network

import retrofit2.http.GET

interface EmployeeApi {
    @GET("employees.json")
    suspend fun getEmployees(): EmployeeList
}