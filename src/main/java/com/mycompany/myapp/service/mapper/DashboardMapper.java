package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Dashboard;
import com.mycompany.myapp.domain.DashboardLayout;
import com.mycompany.myapp.domain.UserProfile;
import com.mycompany.myapp.service.dto.DashboardDTO;
import com.mycompany.myapp.service.dto.DashboardLayoutDTO;
import com.mycompany.myapp.service.dto.UserProfileDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Dashboard} and its DTO {@link DashboardDTO}.
 */
@Mapper(componentModel = "spring")
public interface DashboardMapper extends EntityMapper<DashboardDTO, Dashboard> {
    @Mapping(target = "dashboardLayout", source = "dashboardLayout", qualifiedByName = "dashboardLayoutId")
    @Mapping(target = "userProfile", source = "userProfile", qualifiedByName = "userProfileId")
    DashboardDTO toDto(Dashboard s);

    @Named("dashboardLayoutId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DashboardLayoutDTO toDtoDashboardLayoutId(DashboardLayout dashboardLayout);

    @Named("userProfileId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserProfileDTO toDtoUserProfileId(UserProfile userProfile);
}
