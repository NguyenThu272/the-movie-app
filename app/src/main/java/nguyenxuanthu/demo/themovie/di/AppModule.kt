package nguyenxuanthu.demo.themovie.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nguyenxuanthu.demo.themovie.config.Config
import nguyenxuanthu.demo.themovie.network.NetworkInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule
{

    @Provides
    @Singleton
    fun provideRetrofitService():Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(Config.BaseURl) // domain chính của web
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideNetworkInterface(retrofit: Retrofit): NetworkInterface {
        return retrofit.create(NetworkInterface::class.java)
    }

}