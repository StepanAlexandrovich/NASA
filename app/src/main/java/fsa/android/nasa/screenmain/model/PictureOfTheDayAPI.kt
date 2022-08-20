package fsa.android.nasa.screenmain.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface PictureOfTheDayAPI {
    @GET
    fun getPictureOfTheDate(@Url url:String): Call<PODServerResponseData>
}