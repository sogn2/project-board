package com.bitstudy.app.service;

import com.bitstudy.app.domain.type.SearchType;
import com.bitstudy.app.dto.ArticleDto;
import com.bitstudy.app.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;
/*검색용*/
    @Transactional(readOnly=true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable){
    return Page.empty();
    }
}
