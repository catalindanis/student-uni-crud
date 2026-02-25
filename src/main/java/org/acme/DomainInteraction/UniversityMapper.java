package org.acme.DomainInteraction;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.Domain.University;
import org.acme.Domain.UniversityDTO;

@ApplicationScoped
public class UniversityMapper {
        public UniversityDTO toDTO(University university) {
            UniversityDTO dto = new UniversityDTO();
            dto.setName(university.getName());
            dto.setLocation(university.getLocation());
            return dto;
        }

        public University toEntity(UniversityDTO dto) {
            University university = new University();
            university.setName(dto.getName());
            university.setLocation(dto.getLocation());
            return university;
        }
}
