/*
 * ThemeController.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
package edu.tamu.app.controller;

import static edu.tamu.weaver.response.ApiStatus.SUCCESS;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import edu.tamu.weaver.response.ApiResponse;
import edu.tamu.weaver.wro.model.CoreTheme;
import edu.tamu.weaver.wro.model.repo.CoreThemeRepo;
import edu.tamu.weaver.wro.service.RepoThemeManager;


/**
 *
 */
@RestController
@RequestMapping("/theme")
public class ThemeController {

    @Autowired
    private CoreThemeRepo coreThemeRepo;

    @Autowired
    private RepoThemeManager themeManagerService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     *
     * @return
     */
    @RequestMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse getAll() {
        return new ApiResponse(SUCCESS, coreThemeRepo.findAll());
    }

    /**
     *
     * @param data
     * @return
     * @throws IOException
     */
    @RequestMapping("/update-property")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse updateProperty(@RequestBody JsonNode dataNode) throws IOException {
        Long themeId = dataNode.get("themeId").asLong();
        Long propertyId = dataNode.get("propertyId").asLong();
        String value = dataNode.get("value").asText();
        themeManagerService.updateThemeProperty(themeId, propertyId, value);
        simpMessagingTemplate.convertAndSend("/channel/theme", new ApiResponse(SUCCESS, coreThemeRepo.findAll()));
        return new ApiResponse(SUCCESS, "Theme updated");
    }

    /**
     *
     * @param data
     * @return
     * @throws IOException
     */
    @RequestMapping("/add-theme")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse addTheme(@RequestBody CoreTheme newTheme) throws IOException {
        coreThemeRepo.create(newTheme.getName());
        simpMessagingTemplate.convertAndSend("/channel/theme", new ApiResponse(SUCCESS, coreThemeRepo.findAll()));
        return new ApiResponse(SUCCESS, "Theme added");
    }

    /**
     *
     * @param data
     * @return
     * @throws IOException
     */
    @RequestMapping("/remove-theme")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse removeTheme(@RequestBody CoreTheme themeToRemove) throws IOException {
        coreThemeRepo.delete(themeToRemove);
        simpMessagingTemplate.convertAndSend("/channel/theme", new ApiResponse(SUCCESS, coreThemeRepo.findAll()));
        return new ApiResponse(SUCCESS, "Theme removed");
    }

    /**
     *
     * @param data
     * @return
     * @throws IOException
     */

    @RequestMapping("/activate-theme")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse activateTheme(@RequestBody CoreTheme themeToActivate) throws IOException {
        themeManagerService.setCurrentTheme(themeToActivate);
        simpMessagingTemplate.convertAndSend("/channel/theme", new ApiResponse(SUCCESS, coreThemeRepo.findAll()));
        return new ApiResponse(SUCCESS, "Theme activated");
    }
}
