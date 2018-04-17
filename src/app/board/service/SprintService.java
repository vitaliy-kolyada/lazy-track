package app.board.service;

import app.board.api.SprintApiController;
import app.board.model.dto.CreateSprintRequest;
import app.board.model.dto.EditSprintDto;
import app.board.model.dto.ProjectSelectorDto;
import app.controller.ProjectApiController;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SprintService {

  private List<ProjectSelectorDto> dtoList;
  private List<EditSprintDto> editSprintDtos;


  private ProjectApiController projectApiController;
  private SprintApiController sprintApiController;

  public SprintService() {
    this.projectApiController = new ProjectApiController();
    this.sprintApiController = new SprintApiController();
  }

  private void updateSprintDtoList() {
    editSprintDtos = sprintApiController.getEditSprintDtoList();
  }

  private void updateProjectList() {
    dtoList = projectApiController.getProjectSelectorDtos();
  }

  public List<String> getProjectNameList() {
    updateProjectList();
    return dtoList.stream().map(ProjectSelectorDto::getName).collect(Collectors.toList());
  }

  public UUID getProjectId(String name) {
    return dtoList.stream().filter(x -> x.getName().equals(name)).findFirst().get().getId();
  }

  public String getProjectName(UUID id) {
    return dtoList.stream().filter(x -> x.getId().equals(id)).findFirst().get().getName();
  }

  public boolean canCreateOnSelectedDate(UUID projectId, LocalDate startDate, LocalDate endDate) {
    return sprintApiController.canCreateOnSelectedDate(projectId, startDate, endDate);
  }

  public boolean create(CreateSprintRequest request) {
    return sprintApiController.create(request);
  }

  public boolean update(EditSprintDto sprint) {
    return sprintApiController.update(sprint);
  }

  public void remove(UUID id) {
    sprintApiController.remove(id);
  }

  public List<String> getSprintNameList() {
    updateSprintDtoList();
    return editSprintDtos.stream().map(EditSprintDto::getName).collect(Collectors.toList());
  }

  public EditSprintDto getByName(String name) {
    if (!editSprintDtos.isEmpty()) {
      return editSprintDtos.stream().filter(x -> x.getName().equals(name)).findFirst().get();
    } else return null;
  }
}
