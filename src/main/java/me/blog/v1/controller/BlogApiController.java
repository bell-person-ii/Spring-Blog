package me.blog.v1.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.blog.v1.domain.Article;
import me.blog.v1.dto.AddArticleRequest;
import me.blog.v1.dto.ArticleResponse;
import me.blog.v1.dto.UpdateArticleRequest;
import me.blog.v1.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

@RequestMapping("/api/articles")
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @GetMapping
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = blogService.findAll().stream().map(ArticleResponse::new).toList();
        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse>findArticle(@PathVariable Long id){
        Article article = blogService.findById(id);
        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteArticle(@PathVariable Long id){
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article>updateArticle(@PathVariable Long id, @RequestBody UpdateArticleRequest request){
        Article updatedArticle = blogService.update(id,request);
        return ResponseEntity.ok().body(updatedArticle);
    }
}
