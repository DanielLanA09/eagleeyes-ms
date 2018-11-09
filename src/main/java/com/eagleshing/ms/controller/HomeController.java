package com.eagleshing.ms.controller;

import com.eagleshing.ms.model.ArticleLink;
import com.eagleshing.ms.model.HomeBlock;
import com.eagleshing.ms.model.repository.ArticleLinkRepository;
import com.eagleshing.ms.model.repository.HomeBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    private HomeBlockRepository homeBlockRepository;

    @Autowired
    private ArticleLinkRepository articleLinkRepository;

    @PostMapping("/saveblock")
    public ResponseEntity<?> saveBlock(@Valid @RequestBody HomeBlock block){
        return ResponseEntity.ok(homeBlockRepository.save(block));
    }

    @DeleteMapping("/deleteblock")
    public ResponseEntity<?> deleteBlock(int id){
        Optional<HomeBlock> _block = homeBlockRepository.findById(id);
        if(_block.get()==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The entity is not found!");
        }
        homeBlockRepository.delete(_block.get());
        return ResponseEntity.ok("Delete Success!");
    }

    @GetMapping("/findblocks")
    public ResponseEntity<?> findBlocks(Pageable pageable){
        return ResponseEntity.ok(homeBlockRepository.findByActive(true,pageable));
    }

    @PostMapping("/savearticle")
    public ResponseEntity<?> saveArticleLink(@Valid @RequestBody ArticleLink articleLink){
        return ResponseEntity.ok(articleLinkRepository.save(articleLink));
    }

    @DeleteMapping("/deletearticle")
    @Transactional
    public ResponseEntity<?> deleteArticleLink(int id){
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
