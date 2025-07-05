package com.example.testapplication.di

import android.content.Context
import androidx.room.Room
import com.example.testapplication.data.local.database.UserDatabase
import com.example.testapplication.data.local.database.dao.UserDao
import com.example.testapplication.data.remote.api.UserService
import com.example.testapplication.data.local.source.UserLocalDataSource
import com.example.testapplication.data.local.source.impl.UserLocalDataSourceImpl
import com.example.testapplication.data.remote.source.impl.UserRemoteDataSourceImpl
import com.example.testapplication.data.remote.source.UserRemoteDataSource
import com.example.testapplication.domain.repository.UserRepository
import com.example.testapplication.data.repository.UserRepositoryImpl
import com.example.testapplication.domain.useCase.GetUsersEntryUseCase
import com.example.testapplication.domain.useCase.UpdateUsersUseCase
import com.example.testapplication.domain.useCase.UserListUseCase
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val contentType = "application/json".toMediaType()

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        prettyPrint = false
        isLenient = true
    }

    @Provides
    @Singleton
    fun provideApiKey(): String = "U9Y1-I9VI-R8LS-AQFO"

    @Provides
    @Singleton
    fun provideOkHttpClient(apiKey: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $apiKey")
                    .build()
                chain.proceed(request)
            }
            .build()
    }


    @Provides
    @Singleton
    fun provideUserService(
        json: Json,
        client: OkHttpClient
    ) : UserService {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(client)
            .build()
            .create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRemoteDateSource(api: UserService) : UserRemoteDataSource {
        return UserRemoteDataSourceImpl(api)
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "users"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(db: UserDatabase): UserDao = db.userDao()

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: UserDao): UserLocalDataSource = UserLocalDataSourceImpl(dao)

    @Provides
    fun provideUserRepository(
        apiRepository: UserRemoteDataSource,
        dbRepository: UserLocalDataSource
    ): UserRepository = UserRepositoryImpl(apiRepository, dbRepository)

    @Provides
    fun provideGetUsersEntryUseCase(
        userRepository: UserRepository
    ): GetUsersEntryUseCase {
        return GetUsersEntryUseCase(userRepository)
    }

    @Provides
    fun provideUpdateUsersUseCase(
        userRepository: UserRepository
    ): UpdateUsersUseCase {
        return UpdateUsersUseCase(userRepository)
    }

    @Provides
    fun provideUserListUseCase(
        getUsersEntryUseCase: GetUsersEntryUseCase,
        updateUsersUseCase: UpdateUsersUseCase
    ): UserListUseCase {
        return UserListUseCase(getUsersEntryUseCase, updateUsersUseCase)
    }
}