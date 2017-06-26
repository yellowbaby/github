package app.yellow.github.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import app.yellow.github.base.BaseSaveAction;
import app.yellow.github.bean.home.explore.RepositoryBean;
import app.yellow.github.bean.home.explore.SearchParams;
import app.yellow.github.bean.home.explore.UserBean;
import app.yellow.github.data.db.KeyFactory;
import rx.Observable;

public class GithubDataRepository implements GithubDataSource {

    @Nullable
    protected static GithubDataRepository INSTANCE = null;

    @NonNull
    protected GithubDataSource mRemoteDataSource;

    @NonNull
    protected GithubDataSource mLocalDataSource;

    // Prevent direct instantiation.
    private GithubDataRepository(GithubDataSource remoteDataSource,
                                 GithubDataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    public static GithubDataRepository getInstance(GithubDataSource remoteDataSource,
                                                   GithubLocalDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new GithubDataRepository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }


    public Observable getRepositoryListByParams(SearchParams params) {
        //  https://api.github.com/search/repositories?q=language:java&page=1&per_page=10
        final String url = KeyFactory.getKeyByParams(params);

        Observable localTask = mLocalDataSource.getRepositoryListByParams(params);

        Observable remoteTask = mRemoteDataSource.getRepositoryListByParams(params).doOnNext(new BaseSaveAction<List<RepositoryBean>>(url));

        return Observable.concat(localTask, remoteTask).first();
    }

    @Override
    public Observable getUserListByParams(SearchParams params) {

        final String url = KeyFactory.getKeyByParams(params);

        Observable localTask = mLocalDataSource.getUserListByParams(params);

        Observable remoteTask = mRemoteDataSource.getUserListByParams(params).doOnNext(new BaseSaveAction<List<UserBean>>(url));

        return Observable.concat(localTask, remoteTask).first();

    }


    @Override
    public Observable getUsersRepositoryList(String username, int page, String seachType) {
        return mRemoteDataSource.getUsersRepositoryList(username, page, seachType);
    }



    @Override
    public Observable getUserByName(String name) {
        return mRemoteDataSource
                .getUserByName(name);
    }

    @Override
    public Observable loginWithAuth(String baseAuth) {
        return mRemoteDataSource.loginWithAuth(baseAuth);
    }

    @Override
    public Observable getFollowing(String username, int page) {
        return mRemoteDataSource.getFollowing(username, page);
    }

    @Override
    public Observable getFollowers(String username, int page) {
        return mRemoteDataSource.getFollowers(username, page);
    }
}
