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
    private Set<ColumnChairDTO> chairs; // Sử dụng Set thay vì List

    @Data
    public static class ColumnChairDTO {
        private String columnChair;
        private Set<String> rowChairs; // Sử dụng Set thay vì List

        public ColumnChairDTO(String columnChair, Set<String> rowChairs) {
            this.columnChair = columnChair;
            this.rowChairs = rowChairs;
        }
    }

    // Phương thức để nhóm các ghế theo columnChair
    public static RoomWithGroupedChairsDTO from(Set<ChairDTO> chairDTOs) {
        if (chairDTOs.isEmpty()) {
            throw new IllegalArgumentException("Danh sách ghế không được trống.");
        }

        // Lấy phần tử đầu tiên từ Set
        ChairDTO firstChair = chairDTOs.iterator().next();

        RoomWithGroupedChairsDTO dto = new RoomWithGroupedChairsDTO();
        dto.setRoomName(firstChair.getRoomName());
        dto.setPrice(firstChair.getPrice());
        dto.setShowTimeId(firstChair.getShowTimeId());

        // Nhóm các ghế theo columnChair và sử dụng Set để loại bỏ trùng lặp
        Map<String, Set<String>> groupedChairs = chairDTOs.stream()
                .collect(Collectors.groupingBy(
                        ChairDTO::getColumnChair,
                        Collectors.mapping(ChairDTO::getRowChair, Collectors.toSet()) // Sử dụng Collectors.toSet()
                ));

        // Chuyển đổi Map thành danh sách ColumnChairDTO
        Set<ColumnChairDTO> columnChairDTOs = groupedChairs.entrySet().stream()
                .map(entry -> new ColumnChairDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toSet()); // Sử dụng Set

        dto.setChairs(columnChairDTOs);

        return dto;
    }
}