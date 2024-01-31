package dev.higormorais.entities;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.higormorais.entities.builders.ExperienceBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;


@Entity
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "company_name")
    private String companyName;

    @Column(nullable = false, name = "description_experience")
    private String description;

    @Column(nullable = false)
    private LocalDate beginning;

    @Column(name = "date_end")
    private LocalDate end;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "experience_technology",
            joinColumns = @JoinColumn(name = "experience_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id"))
    private List<Technology> technologiesWorked = new ArrayList<>();

    // ----------------------------------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getBeginning() {
        return beginning;
    }

    public void setBeginning(LocalDate beginning) {
        this.beginning = beginning;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public List<Technology> getTechnologiesWorked() {
        return technologiesWorked;
    }

    public void setTechnologiesWorked(List<Technology> technologiesWorked) {
        this.technologiesWorked.addAll(technologiesWorked);
    }

    public List<Object> values() {
        return Arrays.asList(companyName, description, beginning, end, id);
    }

    public static List<String> attributes() {
        return Arrays.asList("companyName,description,beginning,end,id".split(","));
    }

    public static List<String> attributesQuery() {
        return Arrays.asList("newCompanyName,newDescription,newBeginning,newEnd,experienceId".split(","));
    }

    public static ExperienceBuilder builder() {
        return ExperienceBuilder.getInstance();
    }

}
