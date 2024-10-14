package com.example.PrimerParcial.repository;

import com.example.PrimerParcial.entities.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository  <E extends Base, ID extends Serializable> extends JpaRepository<E, ID> {
}
