package com.example.cinema_booking.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class RoomWithGroupedChairsDTO {
    private String roomName;
    private BigDecimal price;
    private Long showTimeId;
    private Set<ColumnChairDTO> chairs;

    @Data
    public static class ColumnChairDTO {
        private String columnChair;
        private Set<String> rowChairs;
        private List<Integer> status;

        public ColumnChairDTO(String columnChair, Set<String> rowChairs, List<Integer> status) {
            this.columnChair = columnChair;
            this.rowChairs = rowChairs;
            this.status = status;
        }
    }

    public static RoomWithGroupedChairsDTO from(Set<ChairDTO> chairDTOs) {
        if (chairDTOs.isEmpty()) {
            throw new IllegalArgumentException("Danh sách ghế không được trống.");
        }

        ChairDTO firstChair = chairDTOs.iterator().next();
        RoomWithGroupedChairsDTO dto = new RoomWithGroupedChairsDTO();
        dto.setRoomName(firstChair.getRoomName());
        dto.setPrice(firstChair.getPrice());
        dto.setShowTimeId(firstChair.getShowTimeId());

        Map<String, List<ChairDTO>> groupedChairs = chairDTOs.stream()
                .collect(Collectors.groupingBy(ChairDTO::getColumnChair));

        Set<ColumnChairDTO> columnChairDTOs = groupedChairs.entrySet().stream()
                .map(entry -> {
                    String columnChair = entry.getKey();
                    Set<String> rowChairs = entry.getValue().stream()
                            .map(ChairDTO::getRowChair)
                            .collect(Collectors.toSet());
                    List<Integer> status = entry.getValue().stream()
                            .map(ChairDTO::getStatus)
                            .toList();
                    return new ColumnChairDTO(columnChair,  rowChairs,  status);
                })
                .collect(Collectors.toSet());

        dto.setChairs(columnChairDTOs);

        return dto;
    }
}