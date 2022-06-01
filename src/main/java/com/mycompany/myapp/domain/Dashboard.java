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
 * A Dashboard.
 */
@Document(collection = "dashboard")
public class Dashboard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @DBRef
    @Field("dashboardItem")
    @JsonIgnoreProperties(value = { "dashboard" }, allowSetters = true)
    private Set<DashboardItem> dashboardItems = new HashSet<>();

    @DBRef
    @Field("dashboardLayout")
    @JsonIgnoreProperties(value = { "dashboards" }, allowSetters = true)
    private DashboardLayout dashboardLayout;

    @DBRef
    @Field("userProfile")
    @JsonIgnoreProperties(value = { "dashboards" }, allowSetters = true)
    private UserProfile userProfile;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Dashboard id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<DashboardItem> getDashboardItems() {
        return this.dashboardItems;
    }

    public void setDashboardItems(Set<DashboardItem> dashboardItems) {
        if (this.dashboardItems != null) {
            this.dashboardItems.forEach(i -> i.setDashboard(null));
        }
        if (dashboardItems != null) {
            dashboardItems.forEach(i -> i.setDashboard(this));
        }
        this.dashboardItems = dashboardItems;
    }

    public Dashboard dashboardItems(Set<DashboardItem> dashboardItems) {
        this.setDashboardItems(dashboardItems);
        return this;
    }

    public Dashboard addDashboardItem(DashboardItem dashboardItem) {
        this.dashboardItems.add(dashboardItem);
        dashboardItem.setDashboard(this);
        return this;
    }

    public Dashboard removeDashboardItem(DashboardItem dashboardItem) {
        this.dashboardItems.remove(dashboardItem);
        dashboardItem.setDashboard(null);
        return this;
    }

    public DashboardLayout getDashboardLayout() {
        return this.dashboardLayout;
    }

    public void setDashboardLayout(DashboardLayout dashboardLayout) {
        this.dashboardLayout = dashboardLayout;
    }

    public Dashboard dashboardLayout(DashboardLayout dashboardLayout) {
        this.setDashboardLayout(dashboardLayout);
        return this;
    }

    public UserProfile getUserProfile() {
        return this.userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Dashboard userProfile(UserProfile userProfile) {
        this.setUserProfile(userProfile);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dashboard)) {
            return false;
        }
        return id != null && id.equals(((Dashboard) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Dashboard{" +
            "id=" + getId() +
            "}";
    }
}
