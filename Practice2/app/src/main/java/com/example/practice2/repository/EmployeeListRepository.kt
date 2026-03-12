package com.example.practice2.repository

import com.example.practice2.network.EmployeeApi
import com.example.practice2.network.EmployeesList
import javax.inject.Inject

class EmployeeListRepository @Inject constructor(
    val employeeApi: EmployeeApi,
) {
    suspend fun fetchEmployees(): Result<EmployeesList> {
        return try {
            val result = employeeApi.fetchEmployees()
            Result.success(result)
        } catch (e: Exception) {
             Result.failure(e)
        }
    }

}