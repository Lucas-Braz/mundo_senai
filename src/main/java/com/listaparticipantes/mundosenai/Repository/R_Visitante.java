package com.listaparticipantes.mundosenai.Repository;

import com.listaparticipantes.mundosenai.Model.M_Visitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface R_Visitante extends JpaRepository<M_Visitante,Long> {
}
