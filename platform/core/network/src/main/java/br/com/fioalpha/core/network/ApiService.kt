package br.com.fioalpha.core.network

import br.com.fioalpha.core.network.model.CharacterDataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/public/characters")
    suspend fun fetchCharacter(@Query("offset") offset: Int, @Query("limit") limit: Int): CharacterDataWrapperResponse

}
