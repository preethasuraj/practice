package com.example.sqproject2.repository

import com.example.sqproject2.network.Employee
import com.example.sqproject2.network.EmployeeApi
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    val employeeApi: EmployeeApi
) {

    suspend fun fetchEmployees(): Result<List<Employee>> {
        return try {
            val response = employeeApi.getEmployees()
            if (response.employees.isEmpty()) {
                Result.failure(Exception("Empty"))
            } else {
                Result.success(response.employees)
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error"))
        }
    }

}