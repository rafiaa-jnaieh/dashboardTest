package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A UserProfile.
 */
@Document(collection = "user_profile")
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("priority")
    private Integer priority;

    @DBRef
    @Field("dashboard")
    @JsonIgnoreProperties(value = { "dashboardItems", "dashboardLayout", "userProfile" }, allowSetters = true)
    private Set<Dashboard> dashboards = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public UserProfile id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public UserProfile name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public UserProfile priority(Integer priority) {
        this.setPriority(priority);
        return this;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Set<Dashboard> getDashboards() {
        return this.dashboards;
    }

    public void setDashboards(Set<Dashboard> dashboards) {
        if (this.dashboards != null) {
            this.dashboards.forEach(i -> i.setUserProfile(null));
        }
        if (dashboards != null) {
            dashboards.forEach(i -> i.setUserProfile(this));
        }
        this.dashboards = dashboards;
    }

    public UserProfile dashboards(Set<Dashboard> dashboards) {
        this.setDashboards(dashboards);
        return this;
    }

    public UserProfile addDashboard(Dashboard dashboard) {
        this.dashboards.add(dashboard);
        dashboard.setUserProfile(this);
        return this;
    }

    public UserProfile removeDashboard(Dashboard dashboard) {
        this.dashboards.remove(dashboard);
        dashboard.setUserProfile(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserProfile)) {
            return false;
        }
        return id != null && id.equals(((UserProfile) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserProfile{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", priority=" + getPriority() +
            "}";
    }
}
