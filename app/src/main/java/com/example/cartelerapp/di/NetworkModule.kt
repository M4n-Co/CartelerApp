package com.example.cartelerapp.di


import com.example.cartelerapp.BuildConfig.BASE_URL
import com.example.cartelerapp.home.billboard.network.GetBillboardService
import com.example.cartelerapp.home.location.network.GetLocationService
import com.example.cartelerapp.home.profile.network.ProfileService
import com.example.cartelerapp.signUp.network.SignUpService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ServiceScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @Named("shift")
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("geolocalizacion")
    fun provideRetrofitLocation(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("https://nominatim.openstreetmap.org/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient{
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun signUpUser(@Named("shift") retrofit: Retrofit) : SignUpService{
        return retrofit.create(SignUpService::class.java)
    }

    @Singleton
    @Provides
    fun getProfileUser(@Named("shift") retrofit: Retrofit) : ProfileService{
        return retrofit.create(ProfileService::class.java)
    }

    @Singleton
    @Provides
    fun getBillboard(@Named("shift") retrofit: Retrofit) : GetBillboardService{
        return retrofit.create(GetBillboardService::class.java)
    }

    @Singleton
    @Provides
    fun getLocation(@Named("geolocalizacion") retrofit: Retrofit) : GetLocationService {
        return retrofit.create(GetLocationService::class.java)
    }
}