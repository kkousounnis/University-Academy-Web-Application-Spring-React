
package com.spring.boot.coodle.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "courses")
public class Course {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title", nullable = false, length = 255)
    private String title;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "semester", length = 255)
    private ESemester semester;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "assignment", nullable = false, length = 255)
    private String assignment;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "submission_date", length = 255)
    private Date submissionDate;
    
    @JoinTable(name = "students_courses", joinColumns = {
        @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "student_id", referencedColumnName = "user_id", nullable = false)})
    @ManyToMany
    private List<Student> studentList;
    
    @JoinTable(name = "trainers_courses", joinColumns = {
        @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "trainer_id", referencedColumnName = "user_id", nullable = false)})
    @ManyToMany
    private List<Trainer> trainerList;

    public Course() {
    }

    public Course(Integer id, String title, ESemester semester, String assignment, Date submissionDate) {
        this.id = id;
        this.title = title;
        this.semester = semester;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
    }

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

    public ESemester getSemester() {
        return semester;
    }

    public void setSemester(ESemester semester) {
        this.semester = semester;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Course{");
        sb.append("id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", semester=").append(semester);
        sb.append(", assignment=").append(assignment);
        sb.append(", submissionDate=").append(submissionDate);
        sb.append('}');
        return sb.toString();
    }
     
}
