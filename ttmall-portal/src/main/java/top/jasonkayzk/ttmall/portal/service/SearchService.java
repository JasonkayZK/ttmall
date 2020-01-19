package top.jasonkayzk.ttmall.portal.service;


import top.jasonkayzk.ttmall.portal.pojo.SearchResult;

public interface SearchService {

    SearchResult search(String queryString, int page);

}
