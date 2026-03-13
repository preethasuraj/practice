package com.example.practice2.ui

import com.example.practice2.database.EmployeeEntity
import com.example.practice2.network.Employee
import com.example.practice2.network.EmployeesList
import com.example.practice2.repository.EmployeeListRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description


class EmployeeListViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    // add a fake impl in
    private val repository: EmployeeListRepository = mockk()
    private lateinit var viewModel: EmployeeListViewModel

    @Test
    fun `ui state will be success with employees when repository returns data`() = runTest {
        val mockEmployee = Employee(
            "name", ",", "", ""
        )
        coEvery { repository.employees }.returns(
            flowOf(listOf(
                EmployeeEntity("","","")
            ))
        )
        viewModel = EmployeeListViewModel(repository)
        assertEquals(UiState.Success(EmployeesList(listOf(mockEmployee))), viewModel.uiState.value)
    }
}

class MainDispatcherRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
