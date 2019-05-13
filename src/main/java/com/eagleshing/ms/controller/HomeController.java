package com.eagleshing.ms.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eagleshing.ms.model.ArticleLink;
import com.eagleshing.ms.model.HomeBlock;
import com.eagleshing.ms.model.repository.ArticleLinkRepository;
import com.eagleshing.ms.model.repository.HomeBlockRepository;

@RestController
@RequestMapping("/api/link")
public class HomeController {

    @Autowired
    private HomeBlockRepository homeBlockRepository;

    @Autowired
    private ArticleLinkRepository articleLinkRepository;

    @PostMapping("/saveblock")
    public ResponseEntity<?> saveBlock(@Valid @RequestBody HomeBlock block){
        return ResponseEntity.ok(homeBlockRepository.save(block));
    }

    @DeleteMapping("/deleteblock/{id}")
    @Transactional
    public ResponseEntity<?> deleteBlock(@PathVariable  int id){
        Optional<HomeBlock> _block = homeBlockRepository.findById(id);
        if(_block.get()==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The entity is not found!");
        }

        List<ArticleLink> articleLinks = articleLinkRepository.findByBlockId(id);
        for (ArticleLink link:articleLinks){
            articleLinkRepository.delete(link);
        }
        homeBlockRepository.delete(_block.get());
        return ResponseEntity.ok("Delete Success!");
    }

    @GetMapping("/findblocks")
    public ResponseEntity<?> findBlocks(){
        return ResponseEntity.ok(homeBlockRepository.findAll());
    }

    @PostMapping("/savelink")
    public ResponseEntity<?> saveArticleLink(@Valid @RequestBody ArticleLink articleLink){
        return ResponseEntity.ok(articleLinkRepository.save(articleLink));
    }

    @DeleteMapping("/deletelink/{id}")
    @Transactional
    public ResponseEntity<?> deleteArticleLink(@PathVariable int id){
        if(!articleLinkRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The entity was not found!");
        }
        articleLinkRepository.findById(id).map(a->{
            articleLinkRepository.delete(a);
            return null;
        });
        return ResponseEntity.ok("Delete Success!");
    }

    @GetMapping("/findarticlelinks")
    public ResponseEntity<?> findArticleLinks(int blockId){
        return ResponseEntity.ok(articleLinkRepository.findByBlockId(blockId));
    }
}
