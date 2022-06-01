package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Dashboard} entity.
 */
public class DashboardDTO implements Serializable {

    private String id;

    private DashboardLayoutDTO dashboardLayout;

    private UserProfileDTO userProfile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DashboardLayoutDTO getDashboardLayout() {
        return dashboardLayout;
    }

    public void setDashboardLayout(DashboardLayoutDTO dashboardLayout) {
        this.dashboardLayout = dashboardLayout;
    }

    public UserProfileDTO getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfileDTO userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DashboardDTO)) {
            return false;
        }

        DashboardDTO dashboardDTO = (DashboardDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dashboardDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DashboardDTO{" +
            "id='" + getId() + "'" +
            ", dashboardLayout=" + getDashboardLayout() +
            ", userProfile=" + getUserProfile() +
            "}";
    }
}
