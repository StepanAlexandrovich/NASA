package fsa.android.nasa.screenmain.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fsa.android.nasa.BuildConfig

import fsa.android.nasa.screenmain.model.PODRetrofitImpl
import fsa.android.nasa.screenmain.model.PODServerResponseData
import fsa.android.nasa.util.stringDateToday
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureOfTheDayViewModel (
    private val liveDataForViewToObserve: MutableLiveData<PictureOfTheDayData> = MutableLiveData(),
    private val retrofitImpl: PODRetrofitImpl = PODRetrofitImpl()
) :
    ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getData(): LiveData<PictureOfTheDayData> {
        sendServerRequest(stringDateToday())
        return liveDataForViewToObserve
    }

    fun sendServerRequest(dateString:String) {
        liveDataForViewToObserve.value = PictureOfTheDayData.Loading(null)

        val apiKey: String = BuildConfig.NASA_API_KEY

        if (apiKey.isBlank()) {
            PictureOfTheDayData.Error(Throwable("You need API key"))
        } else {
            retrofitImpl.getRetrofitImpl().getPictureOfTheDate("/planetary/apod?api_key=5cJxkUYnDHzdFK4MdxzscUJ0jE6TpvJjWNEA5OIS&date=${dateString}").enqueue(object :
                Callback<PODServerResponseData> {
                override fun onResponse(
                    call: Call<PODServerResponseData>,
                    response: Response<PODServerResponseData>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataForViewToObserve.value =
                            PictureOfTheDayData.Success(response.body()!!)
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveDataForViewToObserve.value =
                                PictureOfTheDayData.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataForViewToObserve.value =
                                PictureOfTheDayData.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
                    liveDataForViewToObserve.value = PictureOfTheDayData.Error(t)
                }
            })
        }
    }
}