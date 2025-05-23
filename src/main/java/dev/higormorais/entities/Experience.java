package dev.higormorais.entities;


import java.time.LocalDate;
import java.util.*;

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


@Entity
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "company_name")
    private String companyName;

    @Column(nullable = false, columnDefinition = "TEXT")
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
        this.technologiesWorked = technologiesWorked;
    }

    public List<Object> values() {
        return Arrays.asList(companyName, description, beginning, end, technologiesWorked, id);
    }

    public static List<String> attributes() {
        return Arrays.asList("companyName,description,beginning,end,technologiesWorked,id".split(","));
    }

    public Map<String, Object> parametersValue() {
        Map<String, Object> map = new HashMap<>();

        List<String> keys = Experience.attributes();
        List<Object> values = values();

        for(int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }

        return map;
    }

    public boolean endIsAfterBeginning() {

        if(end == null) {
            return true;
        }

        return end.isAfter(beginning);
    }

    public static ExperienceBuilder builder() {
        return ExperienceBuilder.getInstance();
    }

    @Override
    public String toString() {
        return "Experience{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", description='" + description + '\'' +
                ", beginning=" + beginning +
                ", end=" + end +
                ", technologiesWorked=" + technologiesWorked +
                '}';
    }

}
