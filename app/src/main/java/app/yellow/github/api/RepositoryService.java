package app.yellow.github.api;

import java.util.List;

import app.yellow.github.base.BaseResponse;
import app.yellow.github.bean.home.explore.RepositoryBean;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RepositoryService {
    @Headers("Cache-Control: public, max-age=180")
    @GET("/search/{type}")
    Observable<BaseResponse<RepositoryBean>> getRepositoryListsByParams(@Path("type") String type,
                                                                        @Query("q") String q,
                                                                        @Query("page") int pageIndex,
                                                                        @Query("per_page") int pageSize,
                                                                        @Query("sort") String sort,
                                                                        @Query("order") String order);

    @Headers("Cache-Control: public, max-age=180")
    @GET("/users/{username}/repos")
    Observable<List<RepositoryBean>> getRepositoryList(@Path("username") String username, @Query("page") int pageIndex,
                                                       @Query("per_page") int pageSize);

    @Headers("Cache-Control: public, max-age=180")
    @GET("/users/{username}/starred")
    Observable<List<RepositoryBean>> getStaredRepositoryList(@Path("username") String username, @Query("page") int pageIndex,
                                                       @Query("per_page") int pageSize);
}