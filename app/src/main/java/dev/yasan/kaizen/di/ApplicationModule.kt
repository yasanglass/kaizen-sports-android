package dev.yasan.kaizen.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yasan.kaizen.const.NetworkConstants
import dev.yasan.kaizen.data.repo.FavoritesRepositoryImp
import dev.yasan.kaizen.data.repo.SportsRepositoryImp
import dev.yasan.kaizen.data.source.local.FavoritesDatabase
import dev.yasan.kaizen.data.source.local.SportEventDao
import dev.yasan.kaizen.data.source.remote.KaizenApi
import dev.yasan.kaizen.domain.repo.FavoritesRepository
import dev.yasan.kaizen.domain.repo.SportsRepository
import dev.yasan.kit.core.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(NetworkConstants.TIMEOUT_CONNECT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(NetworkConstants.TIMEOUT_READ_MINUTES, TimeUnit.MINUTES)
            .writeTimeout(NetworkConstants.TIMEOUT_WRITE_MINUTES, TimeUnit.MINUTES)
            .addInterceptor(provideHttpLoggingInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
    }

    @Singleton
    @Provides
    fun provideKaizenApi(): KaizenApi = provideRetrofit().create(KaizenApi::class.java)

    @Singleton
    @Provides
    fun provideSportsRepository(kaizenApi: KaizenApi): SportsRepository =
        SportsRepositoryImp(kaizenApi = kaizenApi)

    @Singleton
    @Provides
    fun provideFavoritesRepository(sportEventDao: SportEventDao): FavoritesRepository =
        FavoritesRepositoryImp(dao = sportEventDao)

    @Singleton
    @Provides
    fun provideFavoritesDatabase(
        app: Application,
        callback: FavoritesDatabase.CallBack
    ) = Room.databaseBuilder(
        app,
        FavoritesDatabase::class.java,
        FavoritesDatabase.DATABASE_NAME
    ).fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Provides
    fun provideSportEventDao(favoritesDatabase: FavoritesDatabase) = favoritesDatabase.sportEventDao()

}