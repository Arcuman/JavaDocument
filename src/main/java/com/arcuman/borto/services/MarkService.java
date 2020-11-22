package com.arcuman.borto.services;

import com.arcuman.borto.dto.CommentDTO;
import com.arcuman.borto.dto.MarkDTO;
import com.arcuman.borto.models.Mark;
import com.arcuman.borto.models.TypeMark;

import java.util.List;

public interface MarkService {
  List<MarkDTO> getAllFromDocumentByTypeMark(Long documentId, TypeMark typeMark);

  void addNewMark(MarkDTO markDTO);

  void deleteMarkById(Long id);
}
