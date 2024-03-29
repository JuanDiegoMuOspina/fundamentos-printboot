package com.fundamentosplatzi.springboot.fundamentos.repository;

import com.fundamentosplatzi.springboot.fundamentos.dto.UserDto;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    @Query("Select u from User u WHERE u.email=?1")
   Optional<User> finfByUserEmail(String email);
    @Query("Select u from User u WHERE u.name like ?1%")
  List<User> findAndSort(String name, Sort sort);

    //Optional<User> findByName(String email,String name);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name,String email);
    List<User> findByBirthdateBetween(LocalDate begin, LocalDate end);
    List<User> findByNameLikeOrderByIdDesc(String name);

   /**
    @Query("SELECT new com.fundamentosplatzi.springboot.fundamentos.dto.UserDto(u.id,u.name,u.birthdate)"+
    "FROM User u"+
    "where u.birthdate=:parametroFecha"+
    "and u.email=:parametroEmail")
    Optional<UserDto> getAllByBirthdateAndEmail(@Param("parametroFecha") LocalDate date,
                                                @Param("parametroEmail") String email);

                                                **/
}
