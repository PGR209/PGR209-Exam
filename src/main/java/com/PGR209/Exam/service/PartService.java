package com.PGR209.Exam.service;

import com.PGR209.Exam.exception.ModelIdNotFoundException;
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

    public Optional<Part> newPart(Part part) {
        try {
            return Optional.of(partRepository.save(part));
        } catch (DataIntegrityViolationException error) {
            return Optional.empty();
        }
    }

    public void deletePart(Long id) {
        if (partRepository.findById(id).isEmpty()) {
            throw new ModelIdNotFoundException("Part", id);
        }

        partRepository.deleteById(id);
    }

    public Optional<Part> updatePart(Part part, Long id) {
        Optional<Part> returnPart = partRepository.findById(id);

        if (returnPart.isPresent()) {
            part.setPartId(id);

            returnPart = Optional.of(partRepository.save(part));
        }

        return returnPart;
    }
}
