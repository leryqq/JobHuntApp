package com.example.jobhuntapp

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jobhuntapp.api.MainAPI
import junit.framework.TestCase.fail
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

@RunWith(AndroidJUnit4::class)
class ApiServiceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: MainAPI

    @BeforeEach
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        // Настройка Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/")) // Используйте URL MockWebServer
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(MainAPI::class.java)

        val mockResponse = MockResponse()
            .setBody(File("src/test/resources/mock_response.json").readText())
            .setResponseCode(200)

        mockWebServer.enqueue(mockResponse)
        Log.d("MyLog", "mock: $mockResponse")
    }

    @Test
    fun testApiResponse() = runBlocking {
        // Выполнение запроса и проверка ответа
        try {
            val response = apiService.getOffers()
            Log.d("MyLog", "response @test: $response")
            //assert(response.isSuccessful)
            //assert(response.body()?.title == "Вакансии рядом с вами")
        } catch (e: Exception) {
            fail("Exception was thrown: ${e.localizedMessage}")
        }

    }

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }
}