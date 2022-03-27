package nguyenxuanthu.demo.themovie.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nguyenxuanthu.demo.themovie.network.NetworkInterface
import nguyenxuanthu.demo.themovie.repository.MovieRepository
import nguyenxuanthu.demo.themovie.repository.PersonRepository


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideMovieRepository(networkInterface: NetworkInterface): MovieRepository {
        return MovieRepository(networkInterface)
    }

    @Provides
    fun providePersonRepository(networkInterface: NetworkInterface): PersonRepository {
        return PersonRepository(networkInterface)
    }

}