package com.PGR209.Exam.service;

import com.PGR209.Exam.configuration.ApplicationConfiguration;
import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.exception.ModelNonNullableFieldException;
import com.PGR209.Exam.exception.ModelValueNotAllowed;
import com.PGR209.Exam.model.Part;
import com.PGR209.Exam.model.Subassembly;
import com.PGR209.Exam.repository.PartRepository;
import com.PGR209.Exam.repository.SubassemblyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubassemblyService {
    private final SubassemblyRepository subassemblyRepository;
    private final PartRepository partRepository;
    private final ApplicationConfiguration applicationConfiguration;

    @Autowired
    public SubassemblyService(SubassemblyRepository subassemblyRepository, PartRepository partRepository, ApplicationConfiguration applicationConfiguration) {
        this.subassemblyRepository = subassemblyRepository;
        this.partRepository = partRepository;
        this.applicationConfiguration = applicationConfiguration;
    }

    public Subassembly getSubassemblyById(Long id) {
        return subassemblyRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Subassembly", id));
    }

    public List<Subassembly> getSubassemblyPage(int page) {
        Pageable pageable = Pageable.ofSize(applicationConfiguration.getPageSize()).withPage(page);
        return subassemblyRepository.findAll(pageable).toList();
    }

    public Subassembly newSubassembly(Subassembly subassembly) {
        Subassembly createdSubassembly;
        List<Part> subassemblyParts = new ArrayList<>();

        if (subassembly.getSubassemblyName() == null || subassembly.getSubassemblyName().isEmpty()) {
            throw new ModelNonNullableFieldException("Subassembly", "subassemblyName");
        }

        for (Part part : subassembly.getSubassemblyParts()) {
            if (subassemblyParts.stream().noneMatch(object -> object.getPartId().equals(part.getPartId()))) {
                subassemblyParts.add(partRepository.findById(part.getPartId())
                        .orElseThrow(() -> new ModelValueNotAllowed("Part", "partId")));
            }
        }

        createdSubassembly = new Subassembly(
                subassembly.getSubassemblyName(),
                subassemblyParts
        );

        return subassemblyRepository.save(createdSubassembly);
    }

    public void deleteSubassembly(Long id) {
        if (subassemblyRepository.findById(id).isEmpty()) {
            throw new ModelIdNotFoundException("Subassembly", id);
        }

        subassemblyRepository.deleteById(id);
    }

    public Subassembly updateSubassembly(Subassembly subassembly, Long id) {
        Subassembly updatedSubassembly = subassemblyRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Subassembly", id));

        if (subassembly.getSubassemblyName() != null && !subassembly.getSubassemblyName().isEmpty()) {
            updatedSubassembly.setSubassemblyName(subassembly.getSubassemblyName());
        }

        for (Part part : subassembly.getSubassemblyParts()) {
            if (part.getPartId() == null) {
                throw new ModelNonNullableFieldException("subassemblyParts", "partId");
            } else if (part.getPartId() < 1L) {
                throw new ModelValueNotAllowed("subassemblyParts", "partId");
            }

            if (updatedSubassembly.getSubassemblyParts().stream().noneMatch(object -> object.getPartId().equals(part.getPartId()))) {
                updatedSubassembly.getSubassemblyParts().add(partRepository.findById(part.getPartId())
                        .orElseThrow(() -> new ModelIdNotFoundException("Part", part.getPartId())));
            }
        }

        return subassemblyRepository.save(updatedSubassembly);
    }
}
