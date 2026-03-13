package com.example.practice3.repository

import com.example.practice3.network.EmployeeDto
import com.example.practice3.network.EmployeeService
import javax.inject.Inject

data class EmployeeListRepository  @Inject constructor(
    private val employeeService: EmployeeService
){
    suspend fun fetchEmployees(): Result<List<EmployeeDto>> {
        return try {
            val result =employeeService.fetchEmployees().employees
            Result.success(result)
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}