package com.gustavo.wubalubadubdub.model

class CharacterMapper {
    fun transform(response: CharacterResponse, page:Int): CharacterList{
        return CharacterList(
            count = response.info.count,
            page = page,
            lastPage= response.info.pages,
            charactersList = response.results
        )
    }
}