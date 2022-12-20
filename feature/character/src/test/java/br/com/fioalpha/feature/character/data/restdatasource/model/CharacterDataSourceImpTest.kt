package br.com.fioalpha.feature.character.data.restdatasource.model

import br.com.fioalpha.core.network.ApiService
import br.com.fioalpha.core.network.di.BASE_URL
import br.com.fioalpha.core.network.di.networkModule
import br.com.fioalpha.test.convertTo
import br.com.fioalpha.test.getFileMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterDataSourceImpTest : KoinTest {

    private val characterResponseMock by lazy {
        "CharactersRequestMock.json".getFileMock(this::class.java.classLoader)
    }
    private val apiService: ApiService by inject<ApiService>()
    private val mockWebServe = MockWebServer()

    @Before
    fun setup() {
        mockWebServe.start()
        startKoin {
            modules(
                networkModule,
                module(createdAtStart = true) {
                    factory(qualifier = named(BASE_URL)) { mockWebServe.url("/").toString() }
                }
            )
        }
    }

    @After
    fun tearDown() {
        mockWebServe.shutdown()
    }

    @Test
    fun `when called fetchCharacter`() = runTest {
        mockWebServe.enqueue(MockResponse().setResponseCode(200).setBody(characterResponseMock))
        apiService.fetchCharacter(10, 20).run {
            assertEquals(this, characterResponseMock.convertTo())
        }
    }
}
