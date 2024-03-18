package dev.higormorais.entities;


import java.util.*;

import dev.higormorais.entities.builders.CourseBuilder;
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
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "name_course")
    private String name;

    @Column(name = "url_image")
    private String urlImage;

    @Column(nullable = false, name = "url_certificate")
    private String urlCertificate;

    @Column(nullable = false, name = "importance_level")
    private int importanceLevel;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "course_technology",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id"))
    private List<Technology> technologies = new ArrayList<>();

    // ----------------------------------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getUrlCertificate() {
        return urlCertificate;
    }

    public void setUrlCertificate(String urlCertificate) {
        this.urlCertificate = urlCertificate;
    }

    public int getImportanceLevel() {
        return importanceLevel;
    }

    public void setImportanceLevel(int importanceLevel) {
        this.importanceLevel = importanceLevel;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies.addAll(technologies);
    }

    public List<Object> values() {
        return Arrays.asList(name, urlImage, urlCertificate, importanceLevel, id);
    }

    public static List<String> attributes() {
        return Arrays.asList("name,urlImage,urlCertificate,importanceLevel,id".split(","));
    }

    public Map<String, Object> parametersValue() {
        Map<String, Object> map = new HashMap<>();

        List<String> keys = Course.attributes();
        List<Object> values = values();

        for(int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }

        return map;
    }

    public static CourseBuilder builder() {
        return CourseBuilder.getInstance();
    }

}
