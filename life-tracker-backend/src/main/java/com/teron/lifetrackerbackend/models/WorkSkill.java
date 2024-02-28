package com.teron.lifetrackerbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "work_skills")
public class WorkSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private WorkSkill parent;

    private String comment;

    private String name;

    private int value;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<WorkSkill> children = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public WorkSkill getParent() {
        return parent;
    }

    public void setParent(WorkSkill parent) {
        this.parent = parent;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<WorkSkill> getChildren() {
        return children;
    }

    public void setChildren(List<WorkSkill> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "WorkSkill{" +
                "id=" + id +
                ", parent=" + (parent != null ? parent.getId() : null) +
                ", comment='" + comment + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", children=" + children +
                '}';
    }
}
