package com.bitstudy.app.service;

import com.bitstudy.app.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks private     ArticleService sut;

    @Mock private ArticleRepository articleRepository;
}