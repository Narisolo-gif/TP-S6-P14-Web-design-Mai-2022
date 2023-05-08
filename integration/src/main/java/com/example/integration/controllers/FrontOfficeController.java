package com.example.integration.controllers;

import com.example.integration.models.Article;
import com.example.integration.models.Content;
import com.example.integration.service.ArticleService;
import com.example.integration.service.ContentService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FrontOfficeController {
    @Autowired
    private ArticleService service;
    @GetMapping("/")
    public String toFront(ModelMap map){
        List<Article> listArticle = service.listerArticle();
        map.addAttribute("article",listArticle);
        return "pages-blank";
    }
    /*int page=0;
    @Autowired
    ContentService contentService;
    @GetMapping("/fiche")
    public String list(@RequestParam(required = false) Integer op, ModelMap map) throws IllegalAccessException {
        if(op!=null){
            if(op==0){
                this.page++;
            }else {
                this.page--;
                if(this.page<0){
                    this.page=0;
                }
            }
        }
        map.addAttribute("list",contentService.findbyEtat(1));
        return "index";
    }
    @PostMapping("/find")
    public String find(@RequestParam String key,@RequestParam String page,ModelMap map){
        map.addAttribute("list",contentService.getByKey(key,Integer.valueOf(page),5));
        return "index";
    }
    @GetMapping("/")
    public String findfiche(@RequestParam(required = false) Integer op,Model model){
        if(op!=null){
            if(op==0){
                this.page=this.page+4;
            }else {
                this.page=this.page-4;
                if(this.page<0){
                    this.page=0;
                }
            }
        }
        List<Content> contents = contentService.getFiche(1,this.page,4);
        model.addAttribute("list",contents);
        return "index";
    }*/

}
