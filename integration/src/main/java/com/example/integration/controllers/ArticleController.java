package com.example.integration.controllers;

import com.example.integration.models.Admin;
import com.example.integration.models.Article;
import com.example.integration.models.Categorie;
import com.example.integration.service.AdminService;
import com.example.integration.service.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/back")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private AdminService adminService;
    @GetMapping("/saveArticle")
    public String tosaveArticle(ModelMap map){
        return "back/saveArticle";
    }
    @PostMapping("/save")
    public String saveArticle(@RequestParam(value = "visual",required = false) MultipartFile visual, @RequestParam("titre") String titre, @RequestParam("contenu") String contenu, @RequestParam("resume") String resume, ModelMap map) throws IOException {
        Article article = new Article();
        article.setResume(resume);article.setTitre(titre);article.setContenu(contenu);
        if(visual!=null) {
            article.setVisual(visual);
        }
        articleService.saveArticle(article);
        return tosaveArticle(map);
    }
    @GetMapping("/")
    public String toLog(ModelMap map){
        return "back/pages-login";
    }
    @PostMapping("/loger")
    public String loger(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request, ModelMap map) throws IllegalAccessException {
        Admin admin = new Admin();admin.setUsername(username);admin.setPassword(password);
        Admin admin1 = adminService.logAdmin(admin);
        String res="";
        if (admin1==null){
            String error = "username or password not found";
            res="redirect:/back?logerror="+error;
        }
        else{
            HttpSession session = request.getSession(true);
            session.setAttribute("admin",admin1);
            res=tosaveArticle(map);
        }
        return res;
    }
    @GetMapping("/toUpdate")
    public String toUpdateArticle(ModelMap map){
        List<Article> articleList = articleService.listerArticle();
        map.addAttribute("article",articleList);
        return "back/Modification";
    }
    @PostMapping("/update")
    public String updaterArticle(@ModelAttribute Article article,ModelMap map){
        articleService.UpdateArticle(article);
        return toUpdateArticle(map);
    }
}
