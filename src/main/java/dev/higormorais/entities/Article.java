package dev.higormorais.entities;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.higormorais.entities.builders.ArticleBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;


@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String summary;

    @Column(nullable = false, name = "url_article")
    private String urlArticle;

    @Column(nullable = false, name = "date_article")
    private LocalDate date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "article_technology",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id"))
    private List<Technology> technologiesCovered = new ArrayList<>();

    // ----------------------------------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUrlArticle() {
        return urlArticle;
    }

    public void setUrlArticle(String urlArticle) {
        this.urlArticle = urlArticle;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Technology> getTechnologiesCovered() {
        return technologiesCovered;
    }

    public void setTechnologiesCovered(List<Technology> technologiesCovered) {
        this.technologiesCovered = technologiesCovered;
    }

    public List<Object> values() {
        return Arrays.asList(title, summary, urlArticle, date, id);
    }

    public static List<String> namesAttributes() {
        return Arrays.asList("title,summary,urlArticle,date,id".split(","));
    }

    public static List<String> namesQuery() {
        return Arrays.asList("newTitle,newSummary,newUrlArticle,newDate,articleId".split(","));
    }

    public static ArticleBuilder builder() {
        return ArticleBuilder.getInstance();
    }

    @Override
    public String toString() {
        return this.id.toString() + " || ".concat(this.title);
    }
}
