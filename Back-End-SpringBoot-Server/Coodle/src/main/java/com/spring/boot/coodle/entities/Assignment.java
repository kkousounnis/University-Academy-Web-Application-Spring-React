package com.spring.boot.coodle.entities;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "assignments")
public class Assignment {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "submission_date", length = 255)
    private Date submissionDate;
    
    @Column(name = "is_submitted", columnDefinition = "boolean default false")
    private Boolean isSubmitted;

    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "assignment", nullable = false, length = 255)
    private String assignmentSummary;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "course_id")
    private Course course;

    public Assignment() {
    }

    public Assignment(Integer id, Date submissionDate, Boolean isSubmitted, String assignmentSummary, Course course) {
        this.id = id;
        this.submissionDate = submissionDate;
        this.isSubmitted = isSubmitted;
        this.assignmentSummary = assignmentSummary;
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }
    
    public Boolean getIsSubmitted() {
        return isSubmitted;
    }

    public void setIsSubmitted(Boolean isSubmitted) {
        this.isSubmitted = isSubmitted;
    }

    public String getAssignmentSummary() {
        return assignmentSummary;
    }

    public void setAssignmentSummary(String assignmentSummary) {
        this.assignmentSummary = assignmentSummary;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Assignment{");
        sb.append("id=").append(id);
        sb.append(", submissionDate=").append(submissionDate);
        sb.append('}');
        return sb.toString();
    }

    
    
    

}
