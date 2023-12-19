package com.PGR209.Exam.service;

import com.PGR209.Exam.configuration.ApplicationConfiguration;
import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.exception.ModelNonNullableFieldException;
import com.PGR209.Exam.model.Part;
import com.PGR209.Exam.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {
    private final PartRepository partRepository;
    private final ApplicationConfiguration applicationConfiguration;

    @Autowired
    public PartService(PartRepository partRepository, ApplicationConfiguration applicationConfiguration) {
        this.partRepository = partRepository;
        this.applicationConfiguration = applicationConfiguration;
    }

    public Part getPartById(Long id) {
        return partRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Part", id));
    }

    public List<Part> getPartPage(int page) {
        Pageable pageable = Pageable.ofSize(applicationConfiguration.getPageSize()).withPage(page);
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
