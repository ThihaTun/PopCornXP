package com.example.gl62m7rdx.sqlite_multi_database_test.mvp.view;

import com.example.gl62m7rdx.sqlite_multi_database_test.data.vo.MovieVO;

import java.util.List;

/**
 * Created by GL62M 7RDX on 02-Jan-18.
 */

public interface SearchView {

    void showSearchResultMovie(List<MovieVO> searchResultList);

    void showMsgFailInSearch(String message);

    void showLoading();

    void showMsgSpecialCharacterDetected();

    void showMsgNoNetwork();
}
