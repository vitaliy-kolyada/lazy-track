package app.board.service;

import app.board.model.dto.ProjectSelectorDto;
import app.controller.ProjectApiController;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProjectService {
  private ProjectApiController projectApiController;
  private List<ProjectSelectorDto> selectorDtos;

  public ProjectService() {
    this.projectApiController = new ProjectApiController();
    this.selectorDtos = selectorDtos;
  }

  private void updateDtoList() {
    selectorDtos = projectApiController.getProjectSelectorDtos();
  }

  public List<String> getProjectNames() {
    updateDtoList();
    if (selectorDtos.isEmpty()) {
      return Collections.emptyList();
    }
    return selectorDtos.stream().map(ProjectSelectorDto::getName).collect(Collectors.toList());
  }

  public String getNameById(UUID projectId) {
    return selectorDtos.stream().filter(x -> x.getId().equals(projectId)).findFirst().get().getName();
  }
}
