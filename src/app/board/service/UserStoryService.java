package app.board.service;

import app.board.api.UserStoryApiController;
import app.board.model.dto.EditUserStoryDto;
import app.board.model.dto.ProjectSelectorDto;
import app.controller.ProjectApiController;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserStoryService {

  private List<EditUserStoryDto> userStoryDtoList;
  private List<ProjectSelectorDto> projectSelectorDtos;
  private EditUserStoryDto selectedUserStory;
  private UserStoryApiController userStoryApiController;
  private ProjectApiController projectApiController;

  public UserStoryService() {
    this.projectApiController = new ProjectApiController();
    this.userStoryApiController = new UserStoryApiController();
    this.userStoryDtoList = userStoryApiController.getEditUserStoryDtos();
    this.projectSelectorDtos = projectApiController.getProjectSelectorDtos();
  }

  public void updateUserStorySelector() {
    this.userStoryDtoList = userStoryApiController.getEditUserStoryDtos();
  }

  public List<String> getProjectNameList() {
    updateUserStorySelector();
    return projectSelectorDtos.stream()
        .map(ProjectSelectorDto::getName).collect(Collectors.toList());
  }

  public EditUserStoryDto getUserStoryByName(String userStoryName) {
    if (!userStoryDtoList.isEmpty()) {
      return userStoryDtoList.stream()
          .filter(x -> x.getName().equals(userStoryName))
          .findFirst().get();
    } else {
      return null;
    }
  }

  public List<String> getUserStoryNameList(String projectName) {
    updateUserStorySelector();
    return userStoryDtoList.stream().filter((x) ->
        x.getProjectId().equals(getProjectIdByName(projectName)))
        .map(EditUserStoryDto::getName).collect(Collectors.toList());
  }

  private UUID getProjectIdByName(String projectName) {
    return projectSelectorDtos.stream()
        .filter(x -> x.getName().equals(projectName))
        .map(ProjectSelectorDto::getId).findFirst().get();
  }

  public String getProjectNameById(UUID projectId) {
    if (projectSelectorDtos == null) {
      projectSelectorDtos = projectApiController.getProjectSelectorDtos();
    }
    return projectSelectorDtos.stream()
        .filter(x -> x.getId().equals(projectId))
        .map(ProjectSelectorDto::getName).findFirst().get();
  }

  public List<String> getUserStoryNameList() {
    return userStoryDtoList.stream()
        .map(EditUserStoryDto::getName).collect(Collectors.toList());
  }

  public boolean update(EditUserStoryDto selectedUserStory) {
    return userStoryApiController.editUserStory(selectedUserStory);
  }

  public void remove(UUID id) {
    userStoryApiController.remove(id);
  }

  public UUID getUserStoryIdByName(String userStoryName) {
    return userStoryDtoList.stream().filter(x -> x.getName().equals(userStoryName)).findFirst().get().getId();
  }

  public UUID getProjectIdByUserStoryName(String userStoryName) {
    return userStoryDtoList.stream().filter(x -> x.getName().equals(userStoryName)).findFirst().get().getProjectId();
  }
}
