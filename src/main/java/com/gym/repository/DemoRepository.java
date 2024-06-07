package com.gym.repository;

import com.gym.model.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemoRepository extends JpaRepository<Demo, Integer> {

    @Query("SELECT d FROM Demo d " +
            "WHERE (:id IS NULL OR d.id = :id) " +
            "AND (:fullName IS NULL OR d.fullName = :fullName) " +
            "AND (:email IS NULL OR d.email = :email) " +
            "AND (:selectedDate IS NULL OR d.selectedDate = :selectedDate) " +
            "AND (:selectedTime IS NULL OR d.selectedTime = :selectedTime) " +
            "AND (:selectedDemo IS NULL OR d.selectedDemo = :selectedDemo)")
    List<Demo> findByIdOrFullNameOrEmailOrSelectedDateOrSelectedTimeOrSelectedDemo(Integer id, String fullName, String email, String selectedDate, String selectedTime, String selectedDemo);
}
