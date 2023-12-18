package com.PGR209.Exam.service;

import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.exception.ModelNonNullableFieldException;
import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.model.Part;
import com.PGR209.Exam.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartService {
    private final PartRepository partRepository;

    @Autowired
    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public Part getPartById(Long id) {
        return partRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Part", id));
    }

    public List<Part> getPartPage(int page) {
        //Move to config file?
        int pageSize = 4;

        Pageable pageable = Pageable.ofSize(pageSize).withPage(page);
        return partRepository.findAll(pageable).toList();
    }

    public Part newPart(Part part) {
        Part createdPart;

        if (part.getPartName() == null || part.getPartName().isEmpty()) {
            throw new ModelNonNullableFieldException("Part", "partName");
        }

        createdPart = new Part(
                part.getPartName()
        );

        return partRepository.save(createdPart);
    }

    public void deletePart(Long id) {
        if (partRepository.findById(id).isEmpty()) {
            throw new ModelIdNotFoundException("Part", id);
        }

        partRepository.deleteById(id);
    }

    public Part updatePart(Part part, Long id) {
        Part updatedPart = partRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Part", id));

        if (part.getPartName() != null && !part.getPartName().isEmpty()) {
            updatedPart.setPartName(part.getPartName());
        }

        return partRepository.save(updatedPart);
    }
}
