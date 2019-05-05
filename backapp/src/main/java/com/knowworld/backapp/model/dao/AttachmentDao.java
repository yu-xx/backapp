package com.knowworld.backapp.model.dao;

import com.knowworld.backapp.common.AttachmentType;
import com.knowworld.backapp.model.domain.Attachment;
import com.knowworld.backapp.model.domain.Member;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentDao extends PagingAndSortingRepository<Attachment, Long> {
    Attachment findByFilePath(String filePath);

    List<Attachment> findByFilePathIsNotAndMemberAndType(String filePath, Member member, AttachmentType type);

}
