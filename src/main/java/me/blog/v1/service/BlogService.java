package me.blog.v1.service;

import lombok.RequiredArgsConstructor;
import me.blog.v1.domain.Article;
import me.blog.v1.dto.AddArticleRequest;
import me.blog.v1.dto.UpdateArticleRequest;
import me.blog.v1.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BlogService {

    private final BlogRepository blogRepository;
    @Transactional
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public Article findById(Long id){
        return blogRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("not found: "+id));
    }

    @Transactional
    public void delete(Long id){
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(Long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found + id"));
        article.update(request.getTitle(), request.getContent());
        return article;
    }
}
