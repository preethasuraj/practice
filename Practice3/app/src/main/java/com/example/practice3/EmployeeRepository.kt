package com.example.practice3

import com.example.practice3.network.EmployeeApi
import com.example.practice3.network.EmployeeResponse
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    val employeeApi: EmployeeApi
){

    suspend fun fetchEmployees(): Result<EmployeeResponse> {
        return try {
            val result = employeeApi.fetchEmployees()
             if (result.employees.isEmpty()) {
                Result.failure(Exception("Empty"))
            } else {
                Result.success(result)
            }
        } catch(e: Exception) {
            Result.failure(e)
        }
    }
}