package com.project.professor.allocation.entity;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.Objects;

public class Allocation {

    private Long id;

    private DayOfWeek dayOfWeek;

    private Date startHour;

    private Date endHour;

    public Allocation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Date getStartHour() {
        return startHour;
    }

    public void setStartHour(Date startHour) {
        this.startHour = startHour;
    }

    public Date getEndHour() {
        return endHour;
    }

    public void setEndHour(Date endHour) {
        this.endHour = endHour;
    }

    @Override
    public String toString() {
        return "Allocation{" +
                "id=" + id +
                ", dayOfWeek=" + dayOfWeek +
                ", startHour=" + startHour +
                ", endHour=" + endHour +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Allocation that = (Allocation) o;
        return Objects.equals(id, that.id) &&
                dayOfWeek == that.dayOfWeek &&
                Objects.equals(startHour, that.startHour) &&
                Objects.equals(endHour, that.endHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dayOfWeek, startHour, endHour);
    }
}
