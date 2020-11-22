package com.arcuman.borto.Repository;

import com.arcuman.borto.models.Comment;
import com.arcuman.borto.models.Mark;
import com.arcuman.borto.models.TypeMark;
import com.arcuman.borto.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {
  List<Mark> findAllByDocumentIdAndTypeMark(Long id, TypeMark typeMark);
}
