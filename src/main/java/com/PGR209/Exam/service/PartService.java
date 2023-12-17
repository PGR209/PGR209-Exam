package com.PGR209.Exam.service;

import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.model.Part;
import com.PGR209.Exam.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Optional<Part> getPartById(Long id) {
        return partRepository.findById(id);
    }

    public List<Part> getPartAll() {
        return partRepository.findAll();
    }

    public List<Part> getPartPage(int page) {
        //Move to config file?
        int pageSize = 4;

        Pageable pageable = Pageable.ofSize(pageSize).withPage(page);
        return partRepository.findAll(pageable).toList();
    }

    public Part newPart(Part part) {
        return partRepository.save(part);
    }

    public void deletePart(Long id) {
        partRepository.deleteById(id);
    }

    public Optional<Part> updatePart(Part part, Long id) {
        Optional<Part> returnPart = partRepository.findById(id);

        if (returnPart.isPresent()) {
            part.setId(id);

            returnPart = Optional.of(partRepository.save(part));
        }

        return returnPart;
    }
}
