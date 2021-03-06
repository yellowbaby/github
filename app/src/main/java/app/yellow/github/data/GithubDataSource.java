package app.yellow.github.data;


import app.yellow.github.bean.home.explore.SearchParams;
import rx.Observable;

public interface GithubDataSource {

    Observable getRepositoryListByParams(SearchParams params);

    Observable getUsersRepositoryList(String username, int page, String seachType);

    Observable getUserListByParams(SearchParams params);

    Observable getUserByName(String name);

    Observable getRepostitoryByUrl(String url, int page);

    Observable loginWithAuth(String baseAuth);

    Observable getFollowing(String username, int page);

    Observable getFollowers(String username, int page);

    Observable getUsersByUrl(String url,int page);

    Observable getEvents(String username, int i, String seachType, String reposname);

    Observable getRepositoryByFullName(String name);

}
