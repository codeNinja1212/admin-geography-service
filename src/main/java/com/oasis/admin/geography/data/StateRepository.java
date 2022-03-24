package com.oasis.admin.geography.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oasis.admin.geography.model.State;

public interface StateRepository extends JpaRepository<State, Integer>{

}
