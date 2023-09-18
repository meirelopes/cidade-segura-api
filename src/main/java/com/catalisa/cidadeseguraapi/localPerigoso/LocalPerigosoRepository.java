package com.catalisa.cidadeseguraapi.localPerigoso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalPerigosoRepository extends JpaRepository<LocalPerigoso, Long>{

}
