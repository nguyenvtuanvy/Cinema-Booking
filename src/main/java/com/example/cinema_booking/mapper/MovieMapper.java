package com.example.cinema_booking.mapper;

import com.example.cinema_booking.dto.MovieDTO;
import com.example.cinema_booking.entity.Movie;
import com.example.cinema_booking.entity.Director;
import com.example.cinema_booking.service.director.DirectorService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {

    @Autowired
    private DirectorService directorService;

    @Mapping(target = "director", source = "directorId", qualifiedByName = "idToDirector")
    public abstract Movie toEntity(MovieDTO movieDTO);

    @Mapping(target = "directorId", source = "director.id")
    public abstract MovieDTO toDTO(Movie movie);

    @Named("idToDirector")
    protected Director idToDirector(Long directorId) {
        if (directorId == null) {
            return null;
        }
        return directorService.findById(directorId)
                .orElseThrow(() -> new RuntimeException("Director not found with id: " + directorId));
    }
}