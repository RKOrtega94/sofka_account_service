package com.example.account.modules.movement.domain;

import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

public class MovementsReportSpecification {
    public static <T> Specification<T> withDates(String dates) {
        List<String> datesList = List.of(dates.split(","));

        if (datesList.isEmpty()) throw new IllegalArgumentException("Dates list is empty");
        if (datesList.size() != 2) throw new IllegalArgumentException("Dates list must have two dates");

        Instant startDate = LocalDate.parse(datesList.getFirst()).atStartOfDay(ZoneOffset.UTC).toInstant();
        Instant endDate = LocalDate.parse(datesList.getLast()).atStartOfDay(ZoneOffset.UTC).toInstant();

        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("date"), startDate, endDate);
    }
}
