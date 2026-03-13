package com.example.practice2.repository

import com.example.practice2.database.EmployeeDao
import com.example.practice2.database.EmployeeEntity
import com.example.practice2.network.Employee
import com.example.practice2.network.EmployeeApi
import com.example.practice2.network.EmployeesList
import com.example.practice2.util.toEmployee
import com.example.practice2.util.toEmployeeEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class EmployeeListRepository @Inject constructor(
    val employeeApi: EmployeeApi,
    val employeeDao: EmployeeDao,
) {
    val employees = employeeDao.getEmployees()
    suspend fun fetchEmployees(): Result<Unit> {
         return try {
            val result = employeeApi.fetchEmployees()
             if(result.employees.isNotEmpty()) {
                 employeeDao.addEmployees(result.employees.toEmployeeEntities())
             }
             Result.success(Unit)

        } catch (e: Exception) {
             Result.failure<Unit>(Exception(e.message))
        }
    }

    suspend fun fetchEmployee(uuid: String): Result<Employee?> {
        return try {
                val result = employeeDao.getEmployee(uuid)
            Result.success(result?.toEmployee())

        } catch (e: Exception) {
            Result.failure<Employee>(Exception(e.message))
        }
    }

}

private fun List<Employee>.toEmployeeEntities(): List<EmployeeEntity> {
    return this.map { it.toEmployeeEntity() }
}
