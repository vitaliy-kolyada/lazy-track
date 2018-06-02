package app.board.service;

import app.board.api.StateApiController;
import app.board.model.dto.TableStateDto;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class StateService {
  private final StateApiController controller;
  private List<TableStateDto> tableStateDtos;


  public StateService() {
    this.controller = new StateApiController();
  }

  public void updateTableStateDtoList(UUID projectId) {
    tableStateDtos = controller.getTableStateDtos(projectId);
  }

  public List<String> getStateNames(UUID projectId) {
    updateTableStateDtoList(projectId);
    return tableStateDtos.stream().map(TableStateDto::getName).collect(Collectors.toList());
  }

  public List<TableStateDto> getStates(UUID projectId) {
    updateTableStateDtoList(projectId);
    return tableStateDtos;
  }

  public boolean createState(TableStateDto dto) {
    return controller.createState(dto);
  }

  public boolean editState(TableStateDto dto) {
    return controller.editState(dto);
  }

  public void remove(UUID id) {
    controller.remove(id);
  }

  public TableStateDto getDtoByName(String selectedStateName) {
    return tableStateDtos.stream()
        .filter(x -> x.getName().equals(selectedStateName)).findFirst().get();
  }

  public UUID getStateId(String selectedStateName) {
    return tableStateDtos.stream()
        .filter(x -> x.getName().equals(selectedStateName)).findFirst().get().getStateId();
  }
}
