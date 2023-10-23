package com.example.cartelerapp.di


import com.example.cartelerapp.BuildConfig.BASE_URL
import com.example.cartelerapp.home.billboard.network.BillboardService
import com.example.cartelerapp.home.billboard.network.GetBillboardService
import com.example.cartelerapp.home.profile.network.ProfileService
import com.example.cartelerapp.signUp.network.SignUpService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
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
    fun provideOkHttpClient() : OkHttpClient{
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun signUpUser(retrofit: Retrofit) : SignUpService{
        return retrofit.create(SignUpService::class.java)
    }

    @Singleton
    @Provides
    fun getProfileUser(retrofit: Retrofit) : ProfileService{
        return retrofit.create(ProfileService::class.java)
    }

    @Singleton
    @Provides
    fun getBillboard(retrofit: Retrofit) : GetBillboardService{
        return retrofit.create(GetBillboardService::class.java)
    }

    @Singleton
    @Provides
    fun billboard( retrofit: Retrofit) : BillboardService {
        return retrofit.create(BillboardService::class.java)
    }
}