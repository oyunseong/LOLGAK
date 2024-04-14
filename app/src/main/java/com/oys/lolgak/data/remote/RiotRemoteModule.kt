package com.oys.lolgak.data.remote

import com.oys.lolgak.data.remote.di.RiotGamesAccountApi
import com.oys.lolgak.data.remote.di.RiotGamesSpectatorApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RiotRemoteModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /***
     * @Provides 어노테이션이 붙은 메서드는 매개변수를 통해 다른 의존성을 주입받을 수 있습니다.
     * @Singleton으로 어노테이션된 의존성을 애플리케이션 수명 주기 동안 단 하나의 인스턴스로 관리합니다.
     * 이는 불필요한 객체 생성을 방지하고 리소스를 효율적으로 관리할 수 있게 해줍니다.
     *
     * @Binds는 인터페이스의 구현체를 제공할 때 사용되는 Hilt의 어노테이션입니다.
     * @Provides와 달리 @Binds는 추상 메서드에만 사용할 수 있으며, 모듈 클래스는 반드시 추상 클래스여야 합니다.
     */
    @Provides
    @Singleton
    fun provideRiotGamesAccountApi(okHttpClient: OkHttpClient): RiotGamesAccountApi {
        return Retrofit.Builder()
            .baseUrl("https://asia.api.riotgames.com/riot/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RiotGamesAccountApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRiotGamesSpectatorV5Api(okHttpClient: OkHttpClient): RiotGamesSpectatorApi {
        return Retrofit.Builder()
            .baseUrl("https://kr.api.riotgames.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RiotGamesSpectatorApi::class.java)
    }
}